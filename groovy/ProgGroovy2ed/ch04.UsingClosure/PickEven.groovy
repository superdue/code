// pickEven()方法是一个`高阶函数`，即以函数为参数，或返回一个函数作为结果的函数。
// 在Groovy中，我们称这种匿名代码块为`闭包`（Closure），Groovy团队使用了该术语的一个不太严格的定义。
// http://groovy.codehaus.org/Closures+-+Formal+Definition
def pickEven(n, block) {
	for (int i = 2; i <= n; i += 2) {
		block(i)
	}
}

pickEven(10) { println it }

total = 0
pickEven(10) { total += it }
println "Sum of even numbers from 1 to 10 is ${total}"

product = 1
pickEven(10) { product *= it }
println "Product of even numbers from 1 to 10 is ${product}"

// 闭包是一个函数，这里变量都绑定到了一个上下文或环境中，这个函数就在其中执行。