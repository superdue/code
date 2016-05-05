package chapter10

object exercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // <https://github.com/alanktwong/scala-for-impatient/blob/master/core/src/main/scala/scala/impatient/Exercises10.scala>
  /**
   * (10.6)
   * Java: java.awt.Container extends java.awt.Component
   *       javax.swing.JComponent extends java.awt.Container (unintuitive: violates "is a")
   *       javax.swing.JPanel extends javax.swing.JComponent
   * Preferable: java.awt.Container inherits from java.awt.Component
   *             javax.swing.JComponent inherits from java.awt.Component
   *             javax.swing.JButton inherits from javax.swing.JComponent
   *             javax.swing.JContainer inherits from java.awt.Container and javax.swing.JComponent
   *             javax.swing.JPanel extends javax.swing.JContainer
   * The preferable design is not possible in Java due to the absence of mixins. A working version
   * of multiple inheritance was needed. But it is possible in Scala!
   *
   * Let Container, Component, JContainer & JComponent be traits. Component is the
   * root trait of all 4. Container extends Component. JComponent extends Component. Then
   * since the Swing library is a rewrite of AWT, JContainer extends JComponent with Container.
   * Let JButton be a class that extends JComponent and JPanel be a class that extends JContainer.
   */
}