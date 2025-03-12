package cleancode.studycafe.practiceday7mission.model.pass;

import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPassType;

public interface Pass {

    AccessPassType getAccessPassType();

    int getDuration();

    int getPrice();

}
