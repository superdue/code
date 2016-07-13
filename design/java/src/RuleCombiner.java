public class RuleCombiner {
    static Rule or(final Rule[] rules) {
        return new Rule() {
            String a = "";
            @Override
            public boolean rule() {
                for (Rule rule : rules) {
                    if (rule.rule()) {
                        a += rule.out();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String out() {
                return a;
            }
        };
    }

    static Rule and(final Rule[] rules) {
        return new Rule() {
            String a = "";
            @Override
            public boolean rule() {
                for (Rule rule : rules) {
                    if (!rule.rule()) {
                        return false;
                    }
                    a += rule.out();
                }
                return true;
            }

            @Override
            public String out() {
                return a;
            }
        };
    }
}
