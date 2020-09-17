// This is trash, this won't work:
// trait Animal(name: String)

abstract class Animal(name: String, species: String) {
  def introduction(): Unit = println(s"${name} is a ${species}, say hi to ${name}!")
  def introductionButReal(): Unit = println(s"${name} is a ${species}, say hi to ${name}!")
}

class Cat(name: String) extends Animal(name, "cat")

object Main extends App {
  val kazu = new Cat("Kazu") {
    override def introduction(): Unit = println("MEOW! Scratchy-scratch!")
  }

  kazu.introduction()
  kazu.introductionButReal()
}
