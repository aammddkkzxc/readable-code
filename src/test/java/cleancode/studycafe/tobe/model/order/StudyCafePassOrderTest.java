package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @DisplayName("주문 내역을 바탕으로 총 가격을 계산 한다. (좌석 패스 주문 내역 있는 경우)")
    @Test
    void getTotalPrice_lockerPassExistCase() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafeSeatPassType.FIXED, 4, 10000);
        StudyCafePassOrder studyCafePassOrder = new StudyCafePassOrder(seatPass, lockerPass);

        //when
        int totalPrice = studyCafePassOrder.getTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(235000);
    }

    @DisplayName("주문 내역을 바탕으로 총 가격을 계산한다. (좌석 패스 주문 내역 없는 경우)")
    @Test
    void getTotalPrice_lockerPassNonExistCase() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.HOURLY, 2, 4000, 0.0);
        StudyCafeLockerPass lockerPass = null;
        StudyCafePassOrder studyCafePassOrder = new StudyCafePassOrder(seatPass, lockerPass);

        //when
        int totalPrice = studyCafePassOrder.getTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(4000);
    }

}