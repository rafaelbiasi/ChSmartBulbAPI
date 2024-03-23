package br.com.rafaelbiasi.chsmartbulbled;

import br.com.rafaelbiasi.chsmartbulbled.bluetooth.BluetoothBulbClient;
import br.com.rafaelbiasi.chsmartbulbled.cli.BulbManager;
import br.com.rafaelbiasi.chsmartbulbled.cli.CommandOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

@Slf4j
public class MainCLI {

    private static final Options options = getOptions();
    private static final BulbManager bulbManager = new BulbManager(new BluetoothBulbClient());

    public static void main(String[] args) {
        try {
            CommandOptions cmdOptions = parseOptions(args);
            if (cmdOptions.shouldSetColor()) {
                bulbManager.setColor(cmdOptions);
            }
            if (cmdOptions.shouldRename()) {
                bulbManager.renameDevice(cmdOptions);
            }
        } catch (ParseException e) {
            log.error("Parse error: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static CommandOptions parseOptions(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        return new CommandOptions(cmd);
    }

    private static Options getOptions() {
        final Options options = new Options();
        options.addOption(new Option("a", "address", true, "Device address."));
        options.addOption(new Option("n", "name", true, "Device name."));
        options.addOption(new Option("e", "rename", true, "Rename device."));
        options.addOption(new Option("r", "red", true, "Set red color intensity."));
        options.addOption(new Option("g", "green", true, "Set green color intensity."));
        options.addOption(new Option("b", "blue", true, "Set blue color intensity."));
        options.addOption(new Option("w", "white", true, "Set white color intensity."));
        options.addOption(new Option("f", "fullWhite", false, "Set full white."));
        options.addOption(new Option("c", "color", true, "Change bulb color. Format: #RRGGBBWW"));
        options.addOption(new Option("o", "off", false, "Turn off bulb."));
        options.addOption(new Option("s", "scene", false, "Set scene. (Not implemented yet).)"));
        options.addOption(new Option("m", "mode", false, "Set mode. (Not implemented yet).)"));
        return options;
    }
}
