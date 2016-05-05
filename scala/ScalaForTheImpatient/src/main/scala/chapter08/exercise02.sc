package chapter08

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class BankAccount(initialBalance: Double) {
    private var _balance = initialBalance
    def deposit(amount: Double) = { _balance += amount; _balance }
    def withdraw(amount: Double) = { _balance -= amount; _balance }
    def balance(): Double = _balance
    override def toString = "Balance = %f".format(balance)
  }

  class SavingsAccount(
    initialBalance: Double,
    val interestRateYear: Double = 0.10,
    val freeTransactions: Int = 3,
    val comission: Double = 1.0) extends BankAccount(initialBalance) {

    var transactionsInMonth: Int = 0

    def isFreeTransaction() = transactionsInMonth <= freeTransactions

    override def deposit(amount: Double) = {
      transactionsInMonth += 1
      super.deposit(amount - (if (isFreeTransaction()) 0 else comission))

    }
    override def withdraw(amount: Double) = {
      transactionsInMonth += 1
      super.withdraw(amount + (if (isFreeTransaction()) 0 else comission))
    }

    def earnMonthlyInterest = {
      transactionsInMonth = 0
      super.deposit(balance() * interestRateYear / 12)
    }
  }

  val b = new BankAccount(100.0)                  //> b  : chapter08.exercise02.BankAccount = Balance = 100.000000

  println("BankAccount")                          //> BankAccount

  b.deposit(100)                                  //> res0: Double = 200.0
  b                                               //> res1: chapter08.exercise02.BankAccount = Balance = 200.000000

  b.withdraw(100)                                 //> res2: Double = 100.0
  b                                               //> res3: chapter08.exercise02.BankAccount = Balance = 100.000000

  "SavingAccount"                                 //> res4: String("SavingAccount") = SavingAccount

  val a = new SavingsAccount(100.0, 0.10, 2, 2.0) //> a  : chapter08.exercise02.SavingsAccount = Balance = 100.000000

  a.deposit(100)                                  //> res5: Double = 200.0
  a                                               //> res6: chapter08.exercise02.SavingsAccount = Balance = 200.000000

  a.withdraw(100)                                 //> res7: Double = 100.0
  a                                               //> res8: chapter08.exercise02.SavingsAccount = Balance = 100.000000

  a.deposit(100)                                  //> res9: Double = 198.0
  a                                               //> res10: chapter08.exercise02.SavingsAccount = Balance = 198.000000

  a.withdraw(100)                                 //> res11: Double = 96.0
  a                                               //> res12: chapter08.exercise02.SavingsAccount = Balance = 96.000000

  a.earnMonthlyInterest                           //> res13: Double = 96.8
  "Monthly Interest: " + a                        //> res14: String = Monthly Interest: Balance = 96.800000

  a.deposit(100)                                  //> res15: Double = 196.8
  a                                               //> res16: chapter08.exercise02.SavingsAccount = Balance = 196.800000

  a.withdraw(100)                                 //> res17: Double = 96.80000000000001
  a                                               //> res18: chapter08.exercise02.SavingsAccount = Balance = 96.800000
}