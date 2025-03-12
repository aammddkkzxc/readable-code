package cleancode.studycafe.practice.io;

import cleancode.studycafe.practice.model.pass.access.AccessPassType;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.Pass;

import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askAccessPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showAccessPassListForSelection(List<AccessPass> passes) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            AccessPass pass = passes.get(index);
            System.out.println(String.format("%s. ", index + 1) + display(pass));
        }
    }

    public void askLockerPass(LockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            display(lockerPass)
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    public void showPassOrderSummary(AccessPass selectedPass, LockerPass lockerPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + display(selectedPass));

        if (lockerPass.isInUse()) {
            System.out.println("사물함: " + display(lockerPass));
        }

        double discountRate = selectedPass.getDiscountRate();
        int discountPrice = (int) (selectedPass.getPrice() * discountRate);
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = selectedPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    private String display(Pass pass) {
        AccessPassType type = pass.getAccessPassType();
        int duration = pass.getDuration();
        int price = pass.getPrice();

        if (type == AccessPassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (type == AccessPassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (type == AccessPassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }


}
