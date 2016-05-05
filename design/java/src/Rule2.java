public class Rule2 implements Rule{
    int i;

    Rule2(int i) {
        this.i = i;
    }
    @Override
    public boolean rule() {
        if (i % 5 == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String out() {
        return "buzz";
    }
}
