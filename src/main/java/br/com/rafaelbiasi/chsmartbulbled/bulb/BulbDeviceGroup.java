package br.com.rafaelbiasi.chsmartbulbled.bulb;

import br.com.rafaelbiasi.chsmartbulbled.command.BulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.ChangeColorBulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.ModeBulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.SceneBulbCommand;

import java.util.List;

public class BulbGroupDevices implements Device {

    private final List<BulbDevice> bulbDevices;

    public BulbGroupDevices(List<BulbDevice> bulbDevices) {
        this.bulbDevices = bulbDevices;
    }

    @Override
    public BulbGroupDevices color(Color color) {
        BulbCommand bulbCommand = new ChangeColorBulbCommand(color);
        for (BulbDevice bulbDevice : bulbDevices) {
            bulbDevice.setCommand(bulbCommand);
        }
        return this;
    }

    @Override
    public BulbGroupDevices scene(Scene scene) {
        BulbCommand bulbCommand = new SceneBulbCommand(scene);
        for (BulbDevice bulbDevice : bulbDevices) {
            bulbDevice.setCommand(bulbCommand);
        }
        return this;
    }

    @Override
    public BulbGroupDevices mode(Mode mode, Speed speed) {
        BulbCommand bulbCommand = new ModeBulbCommand(mode, speed);
        for (BulbDevice bulbDevice : bulbDevices) {
            bulbDevice.setCommand(bulbCommand);
        }
        return this;
    }

    @Override
    public BulbGroupDevices commit() {
        for (BulbDevice bulbDevice : bulbDevices) {
            bulbDevice.commit();
        }
        return this;
    }
}
