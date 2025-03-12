package cleancode.studycafe.practiceday7mission.io;

import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPass;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPasses;
import cleancode.studycafe.practiceday7mission.model.pass.locker.LockerPass;
import cleancode.studycafe.practiceday7mission.model.pass.locker.LockerPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PassDataReader {

    public AccessPasses readAllAccessPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            List<AccessPass> accessPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                String accessPassType = values[0];
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                AccessPass accessPass = AccessPass.of(accessPassType, duration, price, discountRate);
                accessPasses.add(accessPass);
            }

            return AccessPasses.of(accessPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public LockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            List<LockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                String accessPassType = values[0];
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                LockerPass lockerPass = LockerPass.of(accessPassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return LockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}