package br.com.rafaelbiasi.chsmartbulbled;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothClient;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Bulbs;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Color;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Device;
import br.com.rafaelbiasi.chsmartbulbled.bulb.DeviceGroup;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        BluetoothClient bluetoothClient = new BluetoothClient();
        Bulbs bulbs = new Bulbs(bluetoothClient);
        List<Device> bulbDevices = bulbs.deviceList();
        Device selectBulb = bulbs.selectBulb(bulbDevices, "Direta");
        selectBulb.pair();
        selectBulb.connectSPP();
        DeviceGroup selectBulbs = bulbs.selectGroupBulbs(bulbDevices, "Direta", "Esquerda");

        selectBulb.color(Color.color().red(255).green(0).blue(0));
        selectBulb.commit();
        selectBulb.rename("Direita1");
        selectBulb.commit();
        selectBulb.rename("Direita");
        selectBulb.commit();

        selectBulbs.color(Color.color().hue(10).brightness(128));
        selectBulbs.commit();


    }
}
