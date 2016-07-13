abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// 1
val v = Var("x")
val op = BinOp("+", Number(1), v)
// 2
println(v.name)
println(op.left)
// 3
println(op)
println(op.right == Var("x"))
// 4
println(op.copy(operator = "-"))

println("*" * 30)

// 1. Wildcard patterns
// 2. Constant patterns
// 3. Variable patterns
// 4. Constructor patterns
// 5. Sequence patterns
// 6. Tuple patterns
// 7. Type patterns

val expr = Var("x")

expr match {
  case BinOp(op, left, right) => println(expr + " is a binary operation")
  case _ => println("aha")
}