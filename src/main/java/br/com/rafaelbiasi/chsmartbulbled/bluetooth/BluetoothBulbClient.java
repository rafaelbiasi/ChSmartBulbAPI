package br.com.rafaelbiasi.chsmartbulbled.bluetooth;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.command.BulbCommand;
import br.com.rafaelbiasi.chsmartbulbled.parameter.delay.FixedDelay;
import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BluetoothBulbClient implements BulbClient {

    private static final FixedDelay DELAY = FixedDelay.minMs();

    @Override
    public List<BluetoothDevice> discoverDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();

        try {
            final Object inquiryCompletedEvent = new Object();
            DiscoveryListener listener = new DiscoveryListener() {
                @Override
                public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                    try {
                        String name = btDevice.getFriendlyName(false);
                        String string = btDevice.getBluetoothAddress();
                        log.info("Discovered device '{}' with name '{}'", string, name);
                        devices.add(new BluetoothDevice(name, string));
                    } catch (Exception e) {
                        throw new RuntimeException("Error discover", e);
                    }
                }

                @Override
                public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                }

                @Override
                public void serviceSearchCompleted(int transID, int respCode) {
                }

                @Override
                public void inquiryCompleted(int discType) {
                    synchronized (inquiryCompletedEvent) {
                        inquiryCompletedEvent.notifyAll();
                    }
                }
            };

            synchronized (inquiryCompletedEvent) {
                boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
                if (started) {
                    // Aguarda o término da busca
                    inquiryCompletedEvent.wait();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inquiry", e);
        }

        return devices;
    }

    @Override
    public StreamConnection connectSPP(BulbDevice device) {
        try {
            String sppService = "2";
            String url = "btspp://" + device.getAddress() + ":" + sppService + ";authenticate=false;encrypt=false;master=false";
            return (StreamConnection) Connector.open(url);
        } catch (IOException e) {
            throw new RuntimeException("Error connect SPP: " + device.getDeviceName(), e);
        }
    }

    @Override
    public void sendSPPCommand(BulbCommand bulbCommand, BulbDevice device) {
        try {
            if (bulbCommand == null) return;
            OutputStream outputStream = device.getOutputStream();
            byte[] commandBytes = bulbCommand.getCommandBytes();
            log.debug("device = '{}', data = '{}', commandBytes = [{}]",
                    device.getDeviceName(),
                    bulbCommand,
                    StringUtil.bytesToHex(commandBytes));
            outputStream.write(commandBytes);
            outputStream.flush();
            DELAY.delay();
        } catch (Exception e) {
            throw new RuntimeException("Error send SPP command: " + device.getDeviceName(), e);
        }
    }

}