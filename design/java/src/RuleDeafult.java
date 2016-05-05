public class RuleDeafult implements Rule {
    private final int i;

    public RuleDeafult(int i) {
        this.i = i;
    }

    @Override
    public boolean rule() {
        return true;
    }

    @Override
    public String out() {
        return Integer.toString(i);
    }
}
