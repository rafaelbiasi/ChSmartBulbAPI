package br.com.rafaelbiasi.chsmartbulbled;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothBulbClient;
import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BulbClient;
import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDeviceGroup;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Bulbs;
import br.com.rafaelbiasi.chsmartbulbled.customeffect.RBGRotationEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.delay.FixedDelay;
import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

import java.util.List;

public class MainExamples {

    public static void main(String[] args) {
        main5(args);
    }

    public static void main1(String[] args) {
        BulbClient bulbClient = new BluetoothBulbClient();
        Bulbs bulbs = new Bulbs(bulbClient);
        List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
        BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "Left", "Right"); //Select and connect group of devices

        selectBulbs.setCommand(() -> StringUtil.hexToBytes("01fe0000538310000000ff0050000000")); //Send custom command
        BulbDevice left = selectBulbs.getDevice("Left"); //Get specific device
        BulbDevice right = selectBulbs.getDevice("Right"); //Get specific device

        right.commit();
        left.commit();
    }

    public static void main2(String[] args) {
        BulbClient bluetoothClient = new BluetoothBulbClient();
        Bulbs bulbs = new Bulbs(bluetoothClient);
        List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
        BulbDevice left = bulbs.connectDevice(bulbDevices, "Left"); //Select and connect single device

        left.color(Color.color().red()); //Set color red
        left.commit(); // Send command to bulb

        left.customEffect(new RBGRotationEffect(), FixedDelay.ms(139f)); //Custom effect

        left.startCustomEffect(); //Run effect
    }


    public static void main3(String[] args) {
        BulbClient bluetoothClient = new BluetoothBulbClient();
        Bulbs bulbs = new Bulbs(bluetoothClient);
        List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
        BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "Left", "Right"); //Select and connect group of devices

        BulbDevice left = selectBulbs.getDevice("Left"); //Get specific device
        BulbDevice right = selectBulbs.getDevice("Right"); //Get specific device

        left.customEffect(new RBGRotationEffect(), FixedDelay.ms(138.88888f)); //Custom effect
        right.customEffect(new RBGRotationEffect(), FixedDelay.ms(139f)); //Custom effect

        selectBulbs.startCustomEffect(); //Run effect in all devices
    }

    public static void main4(String[] args) {
        BulbClient bluetoothClient = new BluetoothBulbClient();
        Bulbs bulbs = new Bulbs(bluetoothClient);
        List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
        BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "Left", "Right"); //Select and connect group of devices

        selectBulbs.color(Color.color().fullWhite()); //Set full white
        selectBulbs.commit(); // Send command to bulb
    }

    public static void main5(String[] args) {
        BulbClient bluetoothClient = new BluetoothBulbClient();
        Bulbs bulbs = new Bulbs(bluetoothClient);
        List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
        BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "F44EFD6CDC90", "F44EFDE5BE27"); //Select and connect group of devices

        BulbDevice left = selectBulbs.getDevice("F44EFD6CDC90"); //Get specific device
        BulbDevice right = selectBulbs.getDevice("F44EFDE5BE27"); //Get specific device

        left.rename("34Left");
//        right.rename("Right");

        selectBulbs.commit();
        selectBulbs.disconnect();
    }
}
