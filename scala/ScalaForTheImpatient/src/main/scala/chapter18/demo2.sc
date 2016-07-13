package chapter18

object demo2 {
  object Title
  
  class Document {
    private var useNextArgAs: Any = null
    def set(obj: Title.type): this.type = { useNextArgAs = obj; this }
  }
  
}