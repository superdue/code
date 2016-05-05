package chapter18

object demo {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  class Document {
    def setTitle(title: String): this.type = {
      this
    }
  }
  
  class Book extends Document {
    def addChapter(chapter: String) = {
      this
    }
  }
  
  val book = new Book()                           //> book  : chapter18.demo.Book = chapter18.demo$$anonfun$main$1$Book$1@19e3118a
                                                  //| 
  book.setTitle("a").addChapter("hello")          //> res0: chapter18.demo.Book = chapter18.demo$$anonfun$main$1$Book$1@19e3118a
}