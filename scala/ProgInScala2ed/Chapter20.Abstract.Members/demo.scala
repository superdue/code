trait Abstract {
  type t
  def transform(x: T): T
  val initial: T
  var current: T
}

class Concrete extends Abstract {
  type T = String
  def tranform(x: String) = x + x
  val initial = "hi"
  var current = initial
}

