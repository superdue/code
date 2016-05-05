def x = null
println x?.metaClass
if (x?.metaClass) {
  println "yes"
} else {
  println "no"
}
