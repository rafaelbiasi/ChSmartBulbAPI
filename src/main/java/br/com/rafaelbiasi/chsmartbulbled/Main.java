package br.com.rafaelbiasi.chsmartbulbled;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        String arg = args[0].toLowerCase();
        String[] mArgs = Arrays.copyOfRange(args, 1, args.length);
        switch (arg) {
            case "console" -> MainConsole.main(mArgs);
            case "example" -> MainExamples.main(mArgs);
            case "ui" -> MainUI.main(mArgs);
            case "cli" -> MainCLI.main(mArgs);
            default -> log.info("Invalid command");
        }
    }
}
