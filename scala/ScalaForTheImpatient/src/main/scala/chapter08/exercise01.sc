package chapter08

object exercise01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
    override def toString = "Balance = %f".format(balance)
  }

  class CheckedAccount(initialBalance: Double, val comission: Double = 1.0) extends BankAccount(initialBalance) {
    override def deposit(amount: Double) = super.deposit(amount - comission);
    override def withdraw(amount: Double) = super.withdraw(amount + comission);
  }

  val b = new BankAccount(100.0)                  //> b  : chapter08.exercise01.BankAccount = Balance = 100.000000

  b.deposit(100)                                  //> res0: Double = 200.0
  b                                               //> res1: chapter08.exercise01.BankAccount = Balance = 200.000000

  b.withdraw(100)                                 //> res2: Double = 100.0
  b                                               //> res3: chapter08.exercise01.BankAccount = Balance = 100.000000

  val a = new CheckedAccount(100.0, 2.0)          //> a  : chapter08.exercise01.CheckedAccount = Balance = 100.000000

  a.deposit(100)                                  //> res4: Double = 198.0
  a                                               //> res5: chapter08.exercise01.CheckedAccount = Balance = 198.000000

  a.withdraw(100)                                 //> res6: Double = 96.0
  a                                               //> res7: chapter08.exercise01.CheckedAccount = Balance = 96.000000
}