package br.com.rafaelbiasi.chsmartbulbled.cli;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothBulbClient;
import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Bulbs;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

import java.util.List;

public class BulbManager {
    private final Bulbs bulbs;

    public BulbManager(BluetoothBulbClient client) {
        this.bulbs = new Bulbs(client);
    }

    public void setColor(CommandOptions options) {
        Color color = Color.color();
        options.getRed().ifPresent(color::redIntensity);
        options.getGreen().ifPresent(color::greenIntensity);
        options.getBlue().ifPresent(color::blueIntensity);
        options.getWhite().ifPresent(color::white);
        options.getRGBW().ifPresent(color::rgb);
        if (options.hasFullWhite()) color.fullWhite();
        if (options.hasOff()) color.off();
        String[] namesOrAddresses = options.getNamesOrAddresses();
        List<BulbDevice> bulbDevices = bulbs.deviceList();
        bulbs.connectDevices(bulbDevices, namesOrAddresses).color(color).commit().disconnect();
    }

    public void renameDevice(CommandOptions options) {
        String[] namesOrAddresses = options.getNamesOrAddresses();
        String[] newNames = options.getNewNames();
        List<BulbDevice> bulbDevices = bulbs.deviceList();
        for (int i = 0; i < namesOrAddresses.length; i++) {
            bulbs.connectDevice(bulbDevices, namesOrAddresses[i]).rename(newNames[i]).commit().disconnect();
        }
    }
}
