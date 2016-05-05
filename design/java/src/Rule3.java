public class Rule3 implements Rule{
    int i;

    Rule3(int i) {
        this.i = i;
    }
    @Override
    public boolean rule() {
        return i % 7 == 0;
    }

    @Override
    public String out() {
        return "whizz";
    }
}
