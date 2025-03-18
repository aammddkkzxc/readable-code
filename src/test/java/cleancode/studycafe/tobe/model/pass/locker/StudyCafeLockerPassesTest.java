package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPassType;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeLockerPassesTest {

    @DisplayName("특정 StudyCafeSeatPass에 따라 주문 가능한 StudyCafeLockerPass를 조회해 온다")
    @Test
    void findLockerPassBy_lockerPassAvailable() {
        //given
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.FIXED, 4, 250000, 0.1);

        StudyCafeSeatPassType studyCafeSeatPassType = StudyCafeSeatPassType.FIXED;
        int duration = 4;
        int price = 10000;

        //when
        Optional<StudyCafeLockerPass> foundLockerPass = allLockerPasses.findLockerPassBy(studyCafeSeatPass);

        //then
        assertAll(
                () -> assertThat(foundLockerPass).isPresent(),
                () -> assertThat(foundLockerPass.get().getPassType()).isEqualTo(studyCafeSeatPassType),
                () -> assertThat(foundLockerPass.get().getDuration()).isEqualTo(duration),
                () -> assertThat(foundLockerPass.get().getPrice()).isEqualTo(price)
        );
    }

    @DisplayName("특정 StudyCafeSeatPass에 따라 주문 가능한 StudyCafeLockerPass가 없으면 조회해 오지 않는다")
    @Test
    void findLockerPassBy_lockerPassUnAvailable() {
        //given
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafeSeatPassType.HOURLY, 2, 4000, 0.0);

        //when
        Optional<StudyCafeLockerPass> foundLockerPass = allLockerPasses.findLockerPassBy(studyCafeSeatPass);

        //then
        assertAll(
                () -> assertThat(foundLockerPass).isEmpty()
        );
    }

}