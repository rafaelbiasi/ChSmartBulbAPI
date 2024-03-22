package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.ModeEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Speed;

public class ModeBulbCommand implements BulbCommand {
    private final ModeEffect modeEffect;
    private final Speed speed;

    public ModeBulbCommand(ModeEffect modeEffect, Speed speed) {
        this.modeEffect = modeEffect;
        this.speed = speed;
    }

    @Override
    public byte[] getCommandBytes() {
        throw new UnsupportedOperationException("Mode not implemented yet. WIP");
    }
}
