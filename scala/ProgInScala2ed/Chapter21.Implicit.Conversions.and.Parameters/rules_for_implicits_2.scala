// Scope Rule: An inserted implicit conversion must be in scope as a single identifier, or be associated with the source or target type of the conversion.

object Dollar {
  implicit def dollarToEuro(x: Dollar): Euro = ...
}

class Dollar { ... }