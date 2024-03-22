package br.com.rafaelbiasi.chsmartbulbled.command;

public class RenameBulbCommand implements BulbCommand {
    private final String newName;

    public RenameBulbCommand(String newName) {
        this.newName = newName;
    }

    @Override
    public byte[] getCommandBytes() {
        throw new UnsupportedOperationException("Rename not implemented yet. WIP");
    }
}
