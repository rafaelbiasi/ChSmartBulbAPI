package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;

public class SceneBulbCommand implements BulbCommand {
    private final SceneEffect sceneEffect;

    public SceneBulbCommand(SceneEffect sceneEffect) {
        this.sceneEffect = sceneEffect;
    }

    @Override
    public byte[] getCommandBytes() {
        throw new UnsupportedOperationException("Scene not implemented yet. WIP");
    }
}
