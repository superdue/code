package chapter10

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Animal {
    val name: String
    def words: String = ""
    def say = println("%s: %s".format(name, words))
  }

  trait Endothermy
  trait Hypotermy

  trait Mammal extends Animal with Endothermy

  class Dog extends Mammal {
    val name = "Bethoven"
    override val words = "Haw-Haw"
  }

  class Cat extends Mammal {
    val name = "Pixel"
    override val words = "Meow!"
    override def say = {
      print("Cat: ")
      super.say
    }
  }

  val d = new Dog()                               //> d  : chapter10.exercise07.Dog = chapter10.exercise07$$anonfun$main$1$Dog$1@1
                                                  //| d807ca8
  val c = new Cat()                               //> c  : chapter10.exercise07.Cat = chapter10.exercise07$$anonfun$main$1$Cat$1@1
                                                  //| a84da23

  d.say                                           //> Bethoven: Haw-Haw
  c.say                                           //> Cat: Pixel: Meow!
}