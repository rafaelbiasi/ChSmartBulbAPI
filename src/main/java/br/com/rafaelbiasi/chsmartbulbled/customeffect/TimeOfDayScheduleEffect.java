package br.com.rafaelbiasi.chsmartbulbled.customeffect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.TreeMap;

/**
 * Efeito que define a cor por horário com transição suave (interpolação + smoothstep).
 *
 * - Aceita agenda (schedule) em pontos (HH:mm -> RGB ou RGBWY).
 * - Interpola continuamente entre os pontos conforme o horário atual.
 * - Lida com a virada de dia (ex.: último ponto 23:45 e primeiro ponto 08:00).
 */
public class TimeOfDayScheduleEffect implements CustomEffect {

    // Chave: minutos desde 00:00 (0..1439). Valor: vetor RGBWY (sempre 5 posições).
    private final TreeMap<Integer, int[]> scheduleMinutes = new TreeMap<>();
    private final boolean smoothEasing; // aplica smoothstep no fator de interpolação

    public TimeOfDayScheduleEffect(Map<LocalTime, int[]> schedule, boolean smoothEasing) {
        if (schedule == null || schedule.isEmpty()) {
            throw new IllegalArgumentException("Schedule não pode ser vazio.");
        }
        // Normaliza entradas para minutos e vetor de 5 posições (RGBWY)
        for (Map.Entry<LocalTime, int[]> e : schedule.entrySet()) {
            int key = toMinutes(e.getKey());
            scheduleMinutes.put(key, normalizeToRGBWY(e.getValue()));
        }
        this.smoothEasing = smoothEasing;
    }

    @Override
    public Color apply(BulbDevice bulbDevice, Long frame, LocalDateTime localDateTime) {
        LocalTime now = localDateTime.toLocalTime().plus(0, ChronoUnit.HOURS);
        int nowMin = toMinutes(now);

        if (scheduleMinutes.size() == 1) {
            int[] only = scheduleMinutes.firstEntry().getValue();
            return Color.color().rgb(only[0], only[1], only[2], only[3], only[4]);
        }

        // Encontrar ponto anterior (prev) e próximo (next), com wrap-around
        Integer prevKey = scheduleMinutes.floorKey(nowMin);
        if (prevKey == null) prevKey = scheduleMinutes.lastKey();

        Integer nextKey = scheduleMinutes.higherKey(nowMin);
        if (nextKey == null) nextKey = scheduleMinutes.firstKey() + 1440; // próximo é do "dia seguinte"

        int[] prevRGBWY = scheduleMinutes.get(prevKey);
        int[] nextRGBWY = scheduleMinutes.get(nextKey % 1440);

        // Distância total e tempo decorrido (considerando wrap-around)
        int span = ((nextKey - prevKey) + 1440) % 1440;
        if (span == 0) { // pontos iguais por alguma razão — retorna prev
            return Color.color().rgb(prevRGBWY[0], prevRGBWY[1], prevRGBWY[2], prevRGBWY[3], prevRGBWY[4]);
        }
        int elapsed = ((nowMin - prevKey) + 1440) % 1440;

        float t = elapsed / (float) span;
        if (smoothEasing) {
            // Smoothstep (C1) para transição mais "orgânica"
            t = t * t * (3f - 2f * t);
        }

        int[] out = lerp(prevRGBWY, nextRGBWY, t);
        return Color.color().rgb(out[0], out[1], out[2], out[3], out[4]);
    }

    // ---------- Utilidades ----------

    private static int toMinutes(LocalTime t) {
        return t.getHour() * 60 + t.getMinute();
    }

    // Garante vetor de tamanho 5 (RGBWY), preenchendo faltantes com 0
    private static int[] normalizeToRGBWY(int[] v) {
        if (v == null || v.length == 0) {
            return new int[]{0, 0, 0, 0, 0};
        }
        int[] out = new int[]{0, 0, 0, 0, 0};
        int len = Math.min(v.length, 5);
        System.arraycopy(v, 0, out, 0, len);
        return out;
    }

    private static int[] lerp(int[] a, int[] b, float t) {
        int[] out = new int[5];
        for (int i = 0; i < 5; i++) {
            float v = a[i] + (b[i] - a[i]) * t;
            // clamp para 0..255
            int iv = Math.max(0, Math.min(255, Math.round(v)));
            out[i] = iv;
        }
        return out;
    }

    // ---------- Builder ----------

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final TreeMap<LocalTime, int[]> schedule = new TreeMap<>();
        private boolean smoothEasing = true;

        /**
         * Define se aplica easing (smoothstep) na interpolação.
         */
        public Builder smoothEasing(boolean enabled) {
            this.smoothEasing = enabled;
            return this;
        }

        /**
         * Adiciona ponto com RGB.
         */
        public Builder add(LocalTime time, int r, int g, int b) {
            schedule.put(time, new int[]{r, g, b});
            return this;
        }

        /**
         * Adiciona ponto com RGB a partir de string HH:mm.
         */
        public Builder add(String hhmm, int r, int g, int b) {
            return add(LocalTime.parse(hhmm), r, g, b);
        }

        /**
         * Adiciona ponto com RGBWY.
         */
        public Builder add(LocalTime time, int r, int g, int b, int w, int y) {
            schedule.put(time, new int[]{r, g, b, w, y});
            return this;
        }

        /**
         * Adiciona ponto com RGBWY a partir de string HH:mm.
         */
        public Builder add(String hhmm, int r, int g, int b, int w, int y) {
            return add(LocalTime.parse(hhmm), r, g, b, w, y);
        }

        public TimeOfDayScheduleEffect build() {
            return new TimeOfDayScheduleEffect(schedule, smoothEasing);
        }
    }
}