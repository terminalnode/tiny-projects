package simpletest

class Person(var name: String)

object Hello extends App {
  val p = new Person("Alexander Rundberg")
  println(s"Hello ${p.name}")
}
