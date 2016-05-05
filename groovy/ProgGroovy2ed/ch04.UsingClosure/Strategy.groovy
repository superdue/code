def totalSelectValues(n, closure) {
	total = 0
	for (i in 1..n) {
		if (closure(i)) { total += i }
	}
	total
}
print "Total of even numbers from 1 to 10 is "
println totalSelectValues(10) { it % 2 == 0 }

def isOdd = { it % 2 != 0 }
print "Total of odd numbers from 1 to 10 is "
println totalSelectValues(10, isOdd)

// 顺便插一句，不费吹灰之力，这个例子就实现了策略模式。