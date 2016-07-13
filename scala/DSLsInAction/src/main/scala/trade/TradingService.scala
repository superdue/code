package api

trait TradingService extends TaxFeeCalculationComponent with TaxFeeRulesComponent {
  type T <: Trade

  def taxes(trade: T): Map[TaxFee, BigDecimal] = taxFeeCalculator.calculateTaxFee(trade)

  def totalTaxFee(trade: T): BigDecimal = {
    taxes(trade).foldLeft(BigDecimal(0))(_ + _._2)
  }

  def cashValue(trade: T): BigDecimal
}

object EquityTradingService extends TradingService {
  type T = EquityTrade

  val taxFeeCalculator = new TaxFeeCalculatorImpl
  val taxFeeRules = new TaxFeeRulesImpl

  override def cashValue(trade: T): BigDecimal = {
    trade.principal + totalTaxFee(trade)
  }
}

object FixedIncomeTradingService extends TradingService with AccruedInterestCalculationComponent {
  type T = FixedIncomeTrade

  val taxFeeCalculator = new TaxFeeCalculatorImpl
  val accruedInterestCalculator = new AccruedInterestCalculatorImpl
  val taxFeeRules = new TaxFeeRulesImpl

  def accruedInterest(trade: T): BigDecimal = {
    accruedInterestCalculator.calculateAccruedInterest(trade)
  }

  override def cashValue(trade: FixedIncomeTrade): BigDecimal = {
    trade.principal + accruedInterest(trade) + totalTaxFee(trade)
  }
}
