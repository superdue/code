package api

object TaxFeeImplicits {
  class TaxHelper(factor: Double) {
    def percent_of(c: BigDecimal) = factor * c.doubleValue / 100
  }
  implicit def Double2TaxHelper(d: Double): TaxHelper = new TaxHelper(d)
}
