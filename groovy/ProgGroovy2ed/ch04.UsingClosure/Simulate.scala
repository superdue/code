class Equipment[T <: () => Unit](calc: T) {
	def simulate = {
		println("Running simulation")
		calc()
	}
}
val eq1 = new Equipment(() => println("Calculator 1") )
val aCalculator = () => println("Calculator 2")
val eq2 = new Equipment(aCalculator)
val eq3 = new Equipment(aCalculator)

eq1.simulate
eq2.simulate
eq3.simulate