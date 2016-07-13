package api

sealed abstract class TaxFee(id: String)
case object TradeTax extends TaxFee("Trade Tax")
case object Commission extends TaxFee("Commission")
case object Surcharge extends TaxFee("Surcharge")
case object VAT extends TaxFee("VAT")

trait TaxFeeCalculator {
  def calculateTaxFee(trade: Trade): Map[TaxFee, BigDecimal]
}
trait TaxFeeCalculationComponent { this: TaxFeeRulesComponent =>
  val taxFeeCalculator: TaxFeeCalculator

  class TaxFeeCalculatorImpl extends TaxFeeCalculator {
    override def calculateTaxFee(trade: Trade): Map[TaxFee, BigDecimal] = {
      import taxFeeRules._
      val taxfees =
        forTrade(trade) map { taxFee =>
          (taxFee, calculatedAs(trade)(taxFee))
        }
      Map(taxfees: _*)
    }
  }
}

trait AccruedInterestCalculator {
  def calculateAccruedInterest(trade: FixedIncomeTrade): BigDecimal
}

/**
  * 计算"应计利息",固定收益型证券一般都含有应计利息,而且固定收益型交易的现金价值应该将此利息计算在内.
  */
trait AccruedInterestCalculationComponent {
  val accruedInterestCalculator: AccruedInterestCalculator

  class AccruedInterestCalculatorImpl extends AccruedInterestCalculator {
    override def calculateAccruedInterest(trade: FixedIncomeTrade): BigDecimal = {
      //... will contain detailed domain logic
      //... stubbed here
      //
      100
    }
  }
}
