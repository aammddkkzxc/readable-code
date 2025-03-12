package cleancode.studycafe.practiceday7mission.io;

import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPass;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPassType;

import java.util.List;

public interface InputHandler {

    AccessPassType selectAccessPassType();

    AccessPass selectAccessPass(List<AccessPass> passes);

    boolean decideToUseLockerPass();

}
