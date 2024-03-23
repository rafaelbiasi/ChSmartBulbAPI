package br.com.rafaelbiasi.chsmartbulbled.bulb;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothDevice;
import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BulbClient;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Bulbs {

    private final BulbClient bluetoothClient;

    public Bulbs(BulbClient bluetoothClient) {
        this.bluetoothClient = bluetoothClient;
    }

    public List<BulbDevice> deviceList() {
        List<BluetoothDevice> bluetoothDevices = bluetoothClient.discoverDevices();
        return bluetoothDevices.stream()
                .map(bd -> new BulbDevice(bluetoothClient, bd.name(), bd.address()))
                .collect(Collectors.toList());
    }

    public BulbDevice connectDevice(List<BulbDevice> bulbDevices, String bulbNameAddress) {
        BulbDevice dev = bulbDevices.stream()
                .filter(device -> bulbNameAddress.equalsIgnoreCase(device.getDeviceName())
                        || bulbNameAddress.equalsIgnoreCase(device.getAddress()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Bulb " + bulbNameAddress + " not found"));
        dev.connectSPP();
        return dev;
    }

    public BulbDeviceGroup connectDevices(List<BulbDevice> bulbDevices, String... bulbNamesAddresses) {
        List<BulbDevice> devices = bulbDevices.stream()
                .filter(device -> Arrays.stream(bulbNamesAddresses).anyMatch(name -> name.equalsIgnoreCase(device.getDeviceName())
                        || name.equalsIgnoreCase(device.getAddress())))
                .collect(Collectors.toList());
        devices.forEach(BulbDevice::connectSPP);
        return new BulbDeviceGroup(devices);
    }
}
