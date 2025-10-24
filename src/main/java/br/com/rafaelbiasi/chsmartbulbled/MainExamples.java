package br.com.rafaelbiasi.chsmartbulbled;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothBulbClient;
import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BulbClient;
import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDeviceGroup;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Bulbs;
import br.com.rafaelbiasi.chsmartbulbled.customeffect.RBGRotationEffect;
import br.com.rafaelbiasi.chsmartbulbled.customeffect.TimeOfDayScheduleEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Speed;
import br.com.rafaelbiasi.chsmartbulbled.parameter.delay.FixedDelay;
import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

import java.util.List;

public class MainExamples {

	public static void main(String[] args) throws Exception {
//		main5(args);
		main4(args);
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
		BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "Left", "Right"); //Select and connect group of devices

		BulbDevice left = selectBulbs.getDevice("Left"); //Get specific device
		BulbDevice right = selectBulbs.getDevice("Right"); //Get specific device

		TimeOfDayScheduleEffect customEffect = TimeOfDayScheduleEffect.builder()
				.smoothEasing(true)
				// 08:00–09:00 despertar suave (aquecendo -> transição fria)
				.add("08:00", 220, 110, 20)
				.add("08:30", 0, 170, 160)
				.add("09:00", 0, 190, 170)
				.add("09:30", 0, 200, 190)
				.add("10:00", 0, 200, 210)
				.add("10:30", 0, 170, 220)
				.add("11:00", 0, 200, 180)
				.add("11:30", 0, 170, 230)
				.add("12:00", 0, 200, 200)
				.add("12:30", 0, 190, 170)
				.add("13:00", 0, 200, 160)
				.add("13:30", 0, 210, 190)
				.add("14:00", 0, 180, 220)
				.add("14:30", 0, 190, 210)
				.add("15:00", 0, 170, 200)
				.add("15:30", 0, 200, 210)
				.add("16:00", 0, 170, 220)
				.add("16:30", 0, 180, 200)
				.add("17:00", 0, 190, 180)
				.add("17:30", 0, 200, 190)
				.add("18:00", 0, 160, 140)
				.add("18:30", 0, 140, 180)
				.add("19:00", 20, 80, 200)
				.add("19:30", 40, 0, 200)
				.add("20:00", 80, 0, 180)
				.add("20:30", 100, 0, 160)
				.add("21:00", 80, 0, 140)
				.add("21:30", 60, 0, 120)
				.add("22:00", 40, 0, 100)
				.add("22:30", 20, 0, 80)
				.add("23:00", 10, 0, 60)
				.add("23:30", 100, 20, 0)
				.add("00:00", 90, 15, 0)
				.add("00:30", 80, 10, 0)
				.add("01:00", 70, 8, 0)
				.add("01:30", 60, 5, 0)
				.add("02:00", 50, 4, 0)
				.add("02:30", 40, 3, 0)
				.add("03:00", 30, 2, 0)
				.build();

		left.customEffect(customEffect, FixedDelay.ms(1000)); //Custom effect
		right.customEffect(customEffect, FixedDelay.ms(1000)); //Custom effect

		selectBulbs.startCustomEffect(); //Run effect
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
//		BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "F44EFD6CDC90", "F44EFDE5BE27"); //Select and connect group of devices

//		selectBulbs.color(Color.color().red());
		selectBulbs.color(Color.color().off());
//		selectBulbs.sceneEffect(SceneEffect.CANDLELIGHT, Color.color().redIntensity(255).greenIntensity(64), Speed.speed(255));

		selectBulbs.commit(); // Send command to bulb
	}

	public static void main5(String[] args) {
		BulbClient bluetoothClient = new BluetoothBulbClient();
		Bulbs bulbs = new Bulbs(bluetoothClient);
		List<BulbDevice> bulbDevices = bulbs.deviceList(); // Discovery devices
		BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "F44EFD6CDC90", "F44EFDE5BE27"); //Select and connect group of devices

		BulbDevice left = selectBulbs.getDevice("F44EFD6CDC90"); //Get specific device
		BulbDevice right = selectBulbs.getDevice("F44EFDE5BE27"); //Get specific device

		left.rename("Left");
//        right.rename("Right");

		selectBulbs.commit();
		selectBulbs.disconnect();
	}
}
