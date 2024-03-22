package br.com.rafaelbiasi.chsmartbulbled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        String arg = args[0].toLowerCase();
        switch (arg) {
            case "console" -> MainConsole.main(args);
            case "test" -> MainTest.main(args);
            case "ui" -> MainUI.main(args);
            case "cli", "" -> MainCLI.main(args);
            default -> log.info("Invalid command");
        }
    }
}
