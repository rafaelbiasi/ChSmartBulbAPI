package br.com.rafaelbiasi.chsmartbulbled.bluetooth;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.command.BulbCommand;

import javax.microedition.io.StreamConnection;
import java.util.List;

public interface BluetoothClient {

    List<BluetoothDevice> discoverDevices();

    StreamConnection connectSPP(BulbDevice device);

    void sendSPPCommand(BulbCommand bulbCommand, BulbDevice device);

}