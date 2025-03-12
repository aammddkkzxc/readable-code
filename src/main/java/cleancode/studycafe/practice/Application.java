package cleancode.studycafe.practice;

import cleancode.studycafe.practice.config.MachineConfig;
import cleancode.studycafe.practice.io.ConsoleInputHandler;
import cleancode.studycafe.practice.io.ConsoleOutputHandler;
import cleancode.studycafe.practice.io.PassDataReader;

public class Application {

    public static void main(String[] args) {
        MachineConfig machineConfig = new MachineConfig(
                new PassDataReader(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );
        PassMachine passMachine = new PassMachine(machineConfig);

        passMachine.run();
    }

}
