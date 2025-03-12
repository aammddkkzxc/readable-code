package cleancode.studycafe.practice.io;

import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.access.AccessPassType;

import java.util.List;

public interface InputHandler {

    AccessPassType selectAccessPassType();

    AccessPass selectAccessPass(List<AccessPass> passes);

    boolean decideToUseLockerPass();

}
