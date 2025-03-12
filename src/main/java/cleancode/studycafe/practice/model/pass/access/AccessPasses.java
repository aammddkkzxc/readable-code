package cleancode.studycafe.practice.model.pass.access;

import java.util.List;

public class AccessPasses {

    private final List<AccessPass> passes;

    public AccessPasses(List<AccessPass> passes) {
        this.passes = passes;
    }

    public static AccessPasses of(List<AccessPass> passes) {
        return new AccessPasses(passes);
    }

    public List<AccessPass> findPassesBy(AccessPassType type) {
        return passes.stream()
                .filter(pass -> pass.isSameTypeWith(type))
                .toList();
    }
}
