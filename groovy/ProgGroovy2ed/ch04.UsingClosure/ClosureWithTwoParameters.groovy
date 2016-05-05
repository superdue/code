def tellFortune(closure) {
	closure new Date("09/20/2012"), "Your day is filled with ceremony"
}
// () 可以省略， Date可以加
tellFortune() { Date date, fortune ->
	println "Fortune for ${date} is '${fortune}'"
}

// 如果为参数选择了表现力好的名字，通常可以避免定义类型，后面会看到，在元编程中，
// 我们可以使用闭包来覆盖或者替换方法，而在那种情况下，类型信息对于确保实现的正确性非常重要