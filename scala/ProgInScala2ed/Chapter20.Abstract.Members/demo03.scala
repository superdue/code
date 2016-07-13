trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  override def toString = numer + "/" + denom


val x = 2
new RationalTrait {
  val numerArg = 1 * x
  val denomArg = 2 * x
}

new {
  val numerArg = 1 * x
  val denomArg = 2 * x
} with RationalTrait

object twoThirds extends {
  val numerArg = 2
  val denomArg = 3
} with RationalTrait