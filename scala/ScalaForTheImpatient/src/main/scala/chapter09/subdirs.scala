package chapter09

object subdirs {
  
  import java.io.File
  
  def subdirs(dir: File) :Iterator[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs _)
  }
  
  def main(args: Array[String]): Unit = {
    
  }

}