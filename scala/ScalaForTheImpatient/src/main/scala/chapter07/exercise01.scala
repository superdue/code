package chapter07

package com {
  object Foo0 {
    def echo = "echo"
  }
}

package com.horstmann.impatient {
  class Foo1 {
    override def toString = "foo1"
  }
}

package com {
  package horstmann {
    package impatient {
      class Foo2 {
        override def toString = Foo0.echo + "foo2"
      }
    }
  }
}

object exercise01 {
  

}