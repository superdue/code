public class Rule1 implements Rule{
    int i;

    Rule1(int i) {
        this.i = i;
    }
    @Override
    public boolean rule() {
        return i % 3 == 0;
    }

    @Override
    public String out() {
        return "fizz";
    }
}
