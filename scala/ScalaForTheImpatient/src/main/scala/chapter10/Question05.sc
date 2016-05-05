package ch10

object Question05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 5. JavaBeans 规范里有一种提法叫 属性变更监听器（property change listener），这是 bean 用来通知其属性变更的标准方式。PropertyChangeSupport 类对于任何想要支持属性变更监听器的bean而言是个便捷的超类。但可惜已有其他超类的类——比如JComponent——必须重新实现相应的方法。将PropertyChangeSupport重新实现为一个特质，然后把它混入到java.awt.Point类中。
}