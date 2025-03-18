package cleancode.studycafe.tobe.model.pass.seat;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("좌석 패스와 라커 패스에 각각 설정된 좌석 패스 타입과 기간이 같음을 판별할 수 있다")
    @Test
    void isSameDurationType_same() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafeSeatPassType.FIXED, 4, 10000);

        //when
        boolean result = seatPass.isSameDurationType(lockerPass);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("좌석 패스와 라커 패스에 각각 설정된 좌석 패스 타입과 기간이 다름을 판별할 수 있다")
    @Test
    void isSameDurationType_different() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.HOURLY, 2, 4000, 0.0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafeSeatPassType.FIXED, 4, 10000);

        //when
        boolean result = seatPass.isSameDurationType(lockerPass);

        //then
        assertThat(result).isFalse();
    }

}