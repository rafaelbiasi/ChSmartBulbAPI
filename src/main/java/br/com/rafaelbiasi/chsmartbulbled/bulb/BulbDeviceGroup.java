package br.com.rafaelbiasi.chsmartbulbled.bulb;

import br.com.rafaelbiasi.chsmartbulbled.command.BulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.ColorBulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.ModeBulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.command.SceneBulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.customeffect.CustomEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.ModeEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Speed;
import br.com.rafaelbiasi.chsmartbulbled.parameter.delay.Delay;

import java.util.List;
import java.util.NoSuchElementException;

public class BulbDeviceGroup {

    private final List<BulbDevice> bulbDevices;

    public BulbDeviceGroup(List<BulbDevice> bulbDevices) {
        this.bulbDevices = bulbDevices;
    }

    public BulbDeviceGroup color(Color color) {
        BulbCommand bulbCommand = new ColorBulbCommand(color);
        bulbDevices.forEach(bulbDevice -> bulbDevice.setCommand(bulbCommand));
        return this;
    }

    public BulbDeviceGroup customEffect(CustomEffect customEffect, Delay delay) {
        bulbDevices.forEach(bulbDevice -> bulbDevice.customEffect(customEffect, delay));
        return this;
    }

    public BulbDeviceGroup scene(SceneEffect sceneEffect) {
        BulbCommand bulbCommand = new SceneBulbCommand(sceneEffect);
        bulbDevices.forEach(bulbDevice -> bulbDevice.setCommand(bulbCommand));
        return this;
    }

    public BulbDeviceGroup mode(ModeEffect modeEffect, Speed speed) {
        BulbCommand bulbCommand = new ModeBulbCommand(modeEffect, speed);
        bulbDevices.forEach(bulbDevice -> bulbDevice.setCommand(bulbCommand));
        return this;
    }

    public BulbDeviceGroup commit() {
        for (BulbDevice bulbDevice : bulbDevices) {
            bulbDevice.commit();
        }
        return this;
    }

    public BulbDeviceGroup startCustomEffect() {
        bulbDevices.forEach(BulbDevice::startCustomEffect);
        return this;
    }

    public BulbDeviceGroup connectSPP() {
        bulbDevices.forEach(BulbDevice::connectSPP);
        return this;
    }

    public BulbDeviceGroup disconnect() {
        bulbDevices.forEach(BulbDevice::disconnect);
        return this;
    }

    public BulbDevice getDevice(String bulbNameAddress) {
        return bulbDevices.stream()
                .filter(device -> bulbNameAddress.equalsIgnoreCase(device.getDeviceName())
                        || bulbNameAddress.equalsIgnoreCase(device.getAddress()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Bulb " + bulbNameAddress + " not found"));
    }

    public void setCommand(BulbCommand bulbCommand) {
        bulbDevices.forEach(bulbDevice -> bulbDevice.setCommand(bulbCommand));
    }
}
