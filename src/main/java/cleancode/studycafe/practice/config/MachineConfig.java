package cleancode.studycafe.practice.config;

import cleancode.studycafe.practice.io.*;

public class MachineConfig {

    private final PassDataReader passDataReader;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public MachineConfig(PassDataReader passDataReader, ConsoleInputHandler inputHandler, ConsoleOutputHandler outputHandler) {
        this.passDataReader = passDataReader;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public PassDataReader getPassDataReader() {
        return passDataReader;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }
}
