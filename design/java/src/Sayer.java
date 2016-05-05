public class Sayer {
    public Sayer(int i){
        Rule rule1 = new Rule1(i);
        Rule rule2 = new Rule2(i);
        Rule rule3 = new Rule3(i);
        Rule rule4 = new Rule4(i);
        Rule ruleDefault = new RuleDeafult(i);

        Rule ruleOne = RuleCombiner.or(new Rule [] {rule1, rule2, rule3});
        Rule ruleTwo = RuleCombiner.or(new Rule [] {
                        RuleCombiner.and(new Rule[] {rule1, rule2, rule3}),
                        RuleCombiner.and(new Rule[] {rule1, rule2}),
                        RuleCombiner.and(new Rule[] {rule1, rule3}),
                        RuleCombiner.and(new Rule[] {rule2, rule3})
                        }
                );
        Rule ruleThree = RuleCombiner.or(new Rule [] {rule4});
        Rule realRule  = RuleCombiner.or(new Rule[]{ruleThree, ruleTwo, ruleOne, ruleDefault});
        realRule.rule();
        System.out.println(realRule.out());
    }

    public static void main(String[] args) {
        for (int i = 1; i < 40; ++i) {
            new Sayer(i);
        }
    }
}
