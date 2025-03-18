package cleancode.studycafe.tobe.model.pass.seat;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassesTest {

    @DisplayName("특정 StudyCafeSeatPassType에 해당하는 모든 StudyCafeSeatPass들을 전부 조회해 온다")
    @Test
    void findPassByType_success() {
        // given
        SeatPassProvider provider = new SeatPassFileReader();
        StudyCafeSeatPasses passes = provider.getSeatPasses();
        StudyCafeSeatPassType type = StudyCafeSeatPassType.HOURLY;

        int[] durations = {2, 4, 6, 8, 10, 12};
        int[] prices = {4000, 6500, 9000, 11000, 12000, 13000};
        double[] discountRates = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        // when
        List<StudyCafeSeatPass> foundPasses = passes.findPassBy(type);

        // then
        assertAll(
                () -> assertThat(foundPasses).hasSize(6),
                () -> {
                    for (int i = 0; i < foundPasses.size(); i++) {
                        StudyCafeSeatPass pass = foundPasses.get(i);
                        assertThat(pass.getDuration()).isEqualTo(durations[i]);
                        assertThat(pass.getPrice()).isEqualTo(prices[i]);
                        assertThat(pass.getDiscountRate()).isEqualTo(discountRates[i]);
                    }
                }
        );

    }

}