package ch02

object Question10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 10. 编写函数计算 x 的 n 次方，其中 n 是整数，使用如下的递归定义：
  // · pow(x, n) = pow(y, 2)，如果 n 是正偶数的话，这里 y = pow(x, n/2)。
  // · pow(x, n) = x * pow(x, n-1)，如果 n 是正奇数的话。
  // · pow(x, 0) = 1。
  // · pow(x, n) = 1 / pow(x, -n)，如果 n 是负数的话。
  // 不得使用 return 语句。
}