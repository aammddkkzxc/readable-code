package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPassType;

public interface StudyCafePass {

    StudyCafeSeatPassType getPassType();

    int getDuration();

    int getPrice();

}
