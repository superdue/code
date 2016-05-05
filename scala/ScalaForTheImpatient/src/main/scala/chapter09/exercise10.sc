package chapter09

object exercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import collection.mutable._
  import java.io._

  class Person(val name: String) extends Serializable {
    private val friends = new ArrayBuffer[Person]
    // some kind of DSL :-)
    def friend(f: Person) = friends += f
    override def toString = "%s {%s}".format(name, friends.map(_.name).mkString(", "))
  }

  object Person {
    def apply(name: String) = new Person(name)
  }

  // test code
  val anna = Person("Anna")                       //> anna  : chapter09.exercise10.Person = Anna {}
  val boris = Person("Boris")                     //> boris  : chapter09.exercise10.Person = Boris {}
  val clair = Person("Clair")                     //> clair  : chapter09.exercise10.Person = Clair {}

  anna friend boris                               //> res0: scala.collection.mutable.ArrayBuffer[chapter09.exercise10.Person] = Ar
                                                  //| rayBuffer(Boris {})
  boris friend anna                               //> res1: scala.collection.mutable.ArrayBuffer[chapter09.exercise10.Person] = Ar
                                                  //| rayBuffer(Anna {Boris})
  anna friend clair                               //> res2: scala.collection.mutable.ArrayBuffer[chapter09.exercise10.Person] = Ar
                                                  //| rayBuffer(Boris {Anna}, Clair {})
  clair friend boris                              //> res3: scala.collection.mutable.ArrayBuffer[chapter09.exercise10.Person] = Ar
                                                  //| rayBuffer(Boris {Anna})

  val all = Array(anna, boris, clair)             //> all  : Array[chapter09.exercise10.Person] = Array(Anna {Boris, Clair}, Boris
                                                  //|  {Anna}, Clair {Boris})

  "Original objects: " + all.mkString(", ")       //> res4: String = Original objects: Anna {Boris, Clair}, Boris {Anna}, Clair {B
                                                  //| oris}

  val out = new ObjectOutputStream(new FileOutputStream("10.out"))
                                                  //> out  : java.io.ObjectOutputStream = java.io.ObjectOutputStream@c75e4fc
  out.writeObject(all)
  out.close()

  val in = new ObjectInputStream(new FileInputStream("10.out"))
                                                  //> in  : java.io.ObjectInputStream = java.io.ObjectInputStream@5d3ad33d
  val res = in.readObject().asInstanceOf[Array[Person]]
                                                  //> res  : Array[chapter09.exercise10.Person] = Array(Anna {Boris, Clair}, Boris
                                                  //|  {Anna}, Clair {Boris})
  in.close()

  "Restored objects: " + res.mkString(", ")       //> res5: String = Restored objects: Anna {Boris, Clair}, Boris {Anna}, Clair {
                                                  //| Boris}
}