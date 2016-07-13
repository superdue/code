package chapter03

object excercise2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet-
  
  // 1. 编写一段代码，将a设置为一个n个随机整数的数组，要求随机数介于0（包含）和n（不包含）之间
  {
    // 解法一
    val n = 100
    val a = for (elem <- 0 until 100) yield scala.util.Random.nextInt(n)
  }
  {
    // 解法二
    val n = 100
    val a = 0 until n map { _ => scala.util.Random.nextInt(n) }
  }
  
  // 2. 编写一个循环，将整数数组中相邻的元素置换。例如，Array(1,2,3,4,5)经过置换后变为Array(2,1,4,3,5)
  {
    val a = Array(1,2,3,4,5)
    for (i <- 0 until (a.length, 2) if i < a.length-1) { val t = a(i); a(i) = a(i+1); a(i+1)=t }
  }
  
  // 3. 重复前一个练习，不过这一次生成一个新的值交换过的数组。用for/yield
  {
    val a = Array(1,2,3,4,5)
    val b = for (i <- 0 until (a.length)) yield { if (i % 2 == 0 && i == a.length - 1) a(i) else if (i % 2 == 0) a(i+1) else a(i-1) }
  }
  
  // 4. 给定一个整型数组，产出一个新的数组，包含元（源）数组中的所有正值，以原有顺序排列，之后的元素是所有零或负值，以原有顺序排列
  {
    // 解法一
    val a = Array(3,-1,2,0,1)
    import scala.collection.mutable.ArrayBuffer
    val b = ArrayBuffer[Int]()
    for (i <- 0 until a.length if a(i) > 0) b += i
    for (i <- 0 until a.length if a(i) <= 0) b += i
    val c = for(i <- 0 until b.length) yield a(b(i))
  }
  {
    // 解法二
    val a = Array(3,-1,2,0,1)
    val c = a.filter(_ > 0) ++ a.filter(_ <= 0)
  }
  
  // 5. 如何计算Array[Double]的平均值
  {
    val a = Array[Double](3.14, 2.13)
    val mean = a.sum / a.length
  }
  
  // 6. 如何重新组织Array[Int]的元素将它们以反序排列？对于ArrayBuffer[Int]你又会怎么做呢？
  {
    // 1)
    val a = Array(3,2,1)
    val a_1 = for (i <- (0 until a.length).reverse) yield a(i)
    // 2)
    val a_2 = a.reverse
  }
  
  // 7. 编写一段代码，产出数组中的所有值，去掉重复项
  {
    val a = Array(3,3,3,2,2,1)
    val a_ = a.distinct
  }
  
  // 8. 重新编写3.4节结尾的示例，收集负值元素的下标，反序，去掉最后一个下标，然后对每一个下标调用a.remove(i)。比较这样做的效率和3.4节中另外两种方法的效率。
  {
    import scala.collection.mutable.ArrayBuffer
    def fun(a: ArrayBuffer[Int]) = {
      (for (i <- 0 until a.size if a(i) < 0) yield i).reverse.dropRight(1).foreach(a.remove(_))
    }
    val a = ArrayBuffer[Int]()
    a += (-1,-2,-3,2,3)
    fun(a)
  }
  
  // 9. 创建一个由java.util.TimeZone.getAvaiableIDs返回的时区集合，判断条件是它们在美洲。去掉"America/"前缀并排序
  {
    // 解法一
    val a = java.util.TimeZone.getAvailableIDs
    val b = for (i <- 0 until a.length if a(i).startsWith("America/")) yield a(i).drop(8)
  }
  {
    val a = java.util.TimeZone.getAvailableIDs
    val b = a.filter(_.startsWith("America/")).map(_.drop("America/".size)).sorted
  }
  
  // 10. 引入java.awt.datatransfer._并构建一个类型为SystemFlavorMap类型的对象： val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]然后以DataFlavor.imageFlavor为参数调用getNavivesForFlavor方法，以Scala缓冲保存返回值。
  //     (为什么用这样一个晦涩难懂的类？因为在Java标准库中很难找得到使用java.util.List的代码)
  {
    import java.awt.datatransfer._
    import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.mutable.Buffer
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    val res: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  }
}