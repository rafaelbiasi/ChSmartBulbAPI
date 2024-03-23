package br.com.rafaelbiasi.chsmartbulbled.cli;

import org.apache.commons.cli.CommandLine;

import java.util.Optional;

public class CommandOptions {
    private final CommandLine cmd;

    public CommandOptions(CommandLine cmd) {
        this.cmd = cmd;
    }

    public boolean shouldSetColor() {
        return (cmd.hasOption("name") || cmd.hasOption("address"))
                && (cmd.hasOption("red")
                || cmd.hasOption("green")
                || cmd.hasOption("red")
                || cmd.hasOption("blue")
                || cmd.hasOption("white")
                || cmd.hasOption("fullWhite")
                || cmd.hasOption("color")
                || cmd.hasOption("off"));
    }

    public boolean shouldRename() {
        return (cmd.hasOption("name") || cmd.hasOption("address"))
                && cmd.hasOption("rename");
    }

    public String[] getNamesOrAddresses() {
        String[] namesAddresses;
        if (cmd.hasOption("name")) {
            namesAddresses = cmd.getOptionValue("name").split(",");
        } else if (cmd.hasOption("address")) {
            namesAddresses = cmd.getOptionValue("address").split(",");
        } else {
            namesAddresses = new String[0];
        }
        if (namesAddresses.length == 0) {
            throw new IllegalArgumentException("Invalid command line options");
        }
        return namesAddresses;
    }

    public String[] getNewNames() {
        String[] newNames = cmd.getOptionValue("rename").split(",");
        if (newNames.length == 0) {
            throw new IllegalArgumentException("Invalid command line options");
        }
        return newNames;
    }

    public Optional<Integer> getRed() {
        return cmd.hasOption("red")
                ? Optional.of(Integer.parseInt(cmd.getOptionValue("red", "255")))
                : Optional.empty();
    }

    public Optional<Integer> getGreen() {
        return cmd.hasOption("green")
                ? Optional.of(Integer.parseInt(cmd.getOptionValue("green", "255")))
                : Optional.empty();
    }

    public Optional<Integer> getBlue() {
        return cmd.hasOption("blue")
                ? Optional.of(Integer.parseInt(cmd.getOptionValue("blue", "255")))
                : Optional.empty();
    }

    public Optional<Integer> getWhite() {
        return cmd.hasOption("white")
                ? Optional.of(Integer.parseInt(cmd.getOptionValue("white", "255")))
                : Optional.empty();
    }

    public Optional<String> getRGBW() {
        return cmd.hasOption("color")
                ? Optional.of(cmd.getOptionValue("color", "255"))
                : Optional.empty();
    }

    public boolean hasFullWhite() {
        return cmd.hasOption("fullWhite");

    }

    public boolean hasOff() {
        return cmd.hasOption("off");
    }
}