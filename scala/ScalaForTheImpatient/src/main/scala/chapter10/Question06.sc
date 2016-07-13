package ch10

object Question06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 6. 在 Java AWT 类库中，我们有一个Container类，一个可以用于各种组件的Component子类。举例来说，Button是一个Component，但Panel是Container。这是一个运转中的组合模式。Swing有JComponent和JContainer，但如果你仔细看的话，你会发现一些奇怪的细节。尽管把其他组件添加到比如JButton中毫无意义，JComponent依然扩展自Container。Swing的设计者们理想情况下应该会更倾向于10-4中的设计。
  //    但在Java中那是不可能的。请解释这是为什么。Scala中如何用特质来设计这样的效果呢？
  //
  //                           Component  <--------------
  //     In fact,                  ^                    |
  //     JComponent extends        |                 Container
  //     Container                 |                    |
  //                          JComponent   <------------|
  //                               ^                    |
  //                               |                JContainer
  //                            JButton
  //
  //
  //
}