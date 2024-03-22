package br.com.rafaelbiasi.chsmartbulbled.bulb;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BulbClient;
import br.com.rafaelbiasi.chsmartbulbled.command.*;
import br.com.rafaelbiasi.chsmartbulbled.customeffect.CustomEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.ModeEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Speed;
import br.com.rafaelbiasi.chsmartbulbled.parameter.delay.Delay;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BulbDevice {

    private final String deviceName;
    private final String address;
    private final BulbClient bluetoothClient;
    private BulbCommand bulbCommand;
    private StreamConnection connection;
    private OutputStream outputStream;
    private InputStream inputStream;
    private CustomEffect customEffect;
    private Speed speed;
    private Delay delay;
    private boolean customEffectRunning;

    public BulbDevice(BulbClient bluetoothClient, String deviceName, String address) {
        this.bluetoothClient = bluetoothClient;
        this.deviceName = deviceName;
        this.address = address;
    }

    public BulbDevice color(Color color) {
        bulbCommand = new ColorBulbCommand(color);
        return this;
    }

    public BulbDevice sceneEffect(SceneEffect sceneEffect) {
        bulbCommand = new SceneBulbCommand(sceneEffect);
        return this;
    }

    public BulbDevice modeEffect(ModeEffect modeEffect, Speed speed) {
        bulbCommand = new ModeBulbCommand(modeEffect, speed);
        return this;
    }

    public BulbDevice customEffect(CustomEffect customEffect, Delay delay) {
        this.customEffect = customEffect;
        this.delay = delay;
        return this;
    }

    public BulbDevice rename(String newName) {
        bulbCommand = new RenameBulbCommand(newName);
        return this;
    }

    public BulbDevice startCustomEffect() {
        customEffectRunning = true;
        Thread.ofPlatform().start(() -> {
            long frame = 0;
            while (customEffectRunning) {
                delay.prepareDelay();
                this.color(customEffect.apply(this, frame++));
                this.commit();
                delay.waitDelay(frame);
            }
        });
        return this;
    }

    public BulbDevice stopCustomEffect() {
        customEffectRunning = false;
        return this;
    }

    public BulbDevice commit() {
        bluetoothClient.sendSPPCommand(bulbCommand, this);
        bulbCommand = null;
        return this;
    }

    public BulbDevice connectSPP() {
        try {
            if (this.connection == null && this.outputStream == null) {
                this.connection = bluetoothClient.connectSPP(this);
                outputStream = connection.openOutputStream();
                inputStream = connection.openInputStream();
                initializeBulb();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error connect SPP: " + this.getDeviceName(), e);
        }
        return this;
    }

    private void initializeBulb() {
        setCommand(new InitializationBulbCommand());
        commit();
    }

    public BulbDevice disconnect() {
        if (connection != null) {
            try {
                connection.close();
                outputStream.close();
                inputStream.close();
                connection = null;
                outputStream = null;
                inputStream = null;
            } catch (IOException e) {
                connection = null;
                outputStream = null;
                inputStream = null;
                throw new RuntimeException("Error disconnect SPP: " + this.getDeviceName(), e);
            }
        }
        return this;
    }

    public void setCommand(BulbCommand bulbCommand) {
        this.bulbCommand = bulbCommand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getAddress() {
        return address;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public StreamConnection getConnection() {
        return connection;
    }
}
