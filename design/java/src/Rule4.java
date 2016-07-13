public class Rule4 implements Rule {
    private final int i;

    public Rule4(int i) {
        this.i = i;
    }

    @Override
    public boolean rule() {
        if (i > 10 && Integer.toString(i).contains("3")) {
            return true;
        }
        return false;
    }

    @Override
    public String out() {
        return "fizzbuzzwhizz";
    }
}
