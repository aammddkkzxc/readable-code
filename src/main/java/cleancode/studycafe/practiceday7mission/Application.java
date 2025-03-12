package cleancode.studycafe.practiceday7mission;

import cleancode.studycafe.practiceday7mission.config.MachineConfig;
import cleancode.studycafe.practiceday7mission.io.ConsoleInputHandler;
import cleancode.studycafe.practiceday7mission.io.ConsoleOutputHandler;
import cleancode.studycafe.practiceday7mission.io.PassDataReader;

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
