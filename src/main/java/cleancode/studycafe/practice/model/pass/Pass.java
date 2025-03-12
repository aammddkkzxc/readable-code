package cleancode.studycafe.practice.model.pass;

import cleancode.studycafe.practice.model.pass.access.AccessPassType;

public interface Pass {

    AccessPassType getAccessPassType();

    int getDuration();

    int getPrice();

}
