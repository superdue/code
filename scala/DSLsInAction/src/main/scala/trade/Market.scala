package api

trait Market

object Markets {
  case object NYSE extends Market
  case object TOKYO extends Market
  case object HKG extends Market
  case object SGP extends Market
}
