package cleancode.minesweeper.tobe;

import java.util.Optional;

public class OptionalPractice {
    public static void main(String[] args) {
        Optional<Integer> a = Optional.ofNullable(5);
//        Optional<Integer> a = Optional.ofNullable(null);

        Integer result = a.orElse(print());
        System.out.println(result);

        System.out.println("===================");

        Optional<Integer> b = Optional.ofNullable(5);
//        Optional<Integer> b = Optional.ofNullable(null);

        Integer result2 = b.orElseGet(() -> print());
        System.out.println(result2);
    }

    private static Integer print() {
        System.out.println("hi");
        return 1;
    }
}
