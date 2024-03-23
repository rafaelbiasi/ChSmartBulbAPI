# CH Smart Bulb API (WIP)

:bulb: BL08A :red_square::green_square::blue_square:  
![Chsmartbulb.jpg](Chsmartbulb.jpg)

## Usage:

Single Bulb:

```java
BulbClient bulbClient = new BlueCoveBluetoothClient();
Bulbs bulbs = new Bulbs(bulbClient);
List<BulbDevice> bulbDevices = bulbs.deviceList(); //Discovery devices
BulbDevice right = bulbs.connectDevice(bulbDevices, "RightBulb"); //Select and connect single device

right.color(Color.color().red()); //Set color red
right.commit(); //Send command to bulb

right.customEffect(new RBGRotationEffect(),FixedDelay.ms(139f)); //Custom effect

right.startCustomEffect(); //Run effect
```

Multipe Bulb:

```java
BulbClient bulbClient = new BlueCoveBluetoothClient();
Bulbs bulbs = new Bulbs(bulbClient);
List<BulbDevice> bulbDevices = bulbs.deviceList(); //Discovery devices
BulbDeviceGroup selectBulbs = bulbs.connectDevices(bulbDevices, "RightBulb", "LeftBulb"); //Select and connect group of devices

BulbDevice left = selectBulbs.getDevice("LeftBulb"); //Get specific device
BulbDevice right = selectBulbs.getDevice("RightBulb"); //Get specific device

right.color(Color.color().red()); //Set color red
right.commit(); //Send command to bulb

left.customEffect(new RBGRotationEffect(),FixedDelay.ms(138.88888f)); //Custom effect
right.customEffect(new RBGRotationEffect(),FixedDelay.ms(139f)); //Custom effect

selectBulbs.startCustomEffect(); //Run effect in all devices
```
More examples in [MainExamples.java](src%2Fmain%2Fjava%2Fbr%2Fcom%2Frafaelbiasi%2Fchsmartbulbled%2FMainExamples.java)  

---

### Reverse engineering:

---

### Implemented:

---

#### Start/initialization:

**Data**: `3031323334353637` (01234567)

[InitializationBulbCommand.java](src%2Fmain%2Fjava%2Fbr%2Fcom%2Frafaelbiasi%2Fchsmartbulbled%2Fcommand%2FInitializationBulbCommand.java)

---

#### Set Color/Effect:

**Data**: `01fe000053831000aabbcc0050dd0000`  
**Splitted**: `01fe-0000-53-83-10-00-aabbcc-00-50-dd-0000`

**Prefix**: `01fe`  
**Unknow**: `0000`  
**Prefix op**: `53`  
**Color op**: `83`  
**Data size**: `10` (16)  
**Unknow**: `00` (Data size? Never used?)
**Color**: `aabbcc` (aa = Green, bb = Blue, cc = Red)  
**Unknow**: `00`  
**Effect op**: `50` (50 = fixed color, others = WIP)  
**White Intensity**: `dd`  
**Unknow**: `0000`  
[ColorBulbCommand.java](src%2Fmain%2Fjava%2Fbr%2Fcom%2Frafaelbiasi%2Fchsmartbulbled%2Fcommand%2FColorBulbCommand.java)

---

#### Rename:

**Data**: `01fe000053804c000000000000000000034465766963654254000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000`  
**Splitted**: `01fe-0000-53-80-4c-000000000000000000-03-4465766963654254-000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000`

**Prefix**: `01fe`  
**Unknow**: `0000`  
**Prefix op**: `53`  
**Rename op**: `80`  
**Data size**: `4c` (76)  
**Unknow**: `00` (Data size? Never used?)
**Unknow**: `0000000000000000`  
**Prefix name BT**: `03`  
**Name**: `4465766963654254` (DeviceBT)  
**Remaining name empty
**: `000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000`

[RenameBulbCommand.java](src%2Fmain%2Fjava%2Fbr%2Fcom%2Frafaelbiasi%2Fchsmartbulbled%2Fcommand%2FRenameBulbCommand.java)

---

### Not implemented yet / WIP:

---

#### Modes and Scenes

---

_**Anyone interested in contributing to the project is welcome to do so.**_

I would like to extend my sincerest gratitude to [**_pfalcon_**](https://github.com/pfalcon) for the project [*
*_Chsmartbulb-led-bulb-speaker_**](https://github.com/pfalcon/Chsmartbulb-led-bulb-speaker) and [**_samsam2310_
**](https://github.com/samsam2310) for the [**_Bluetooth-Chsmartbulb-Python-API_
**](https://github.com/samsam2310/Bluetooth-Chsmartbulb-Python-API). Both projects have been a tremendous source of
inspiration for my own work, providing valuable insights and direction. Thank you for your contributions to the
community and for inspiring fellow developers like myself.

**WIP** = Work in progress.
