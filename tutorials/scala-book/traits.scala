// Trait as interface
trait TailWagger {
  def startTail(): Unit
  def stopTail(): Unit
}

// Another trait as interface
trait Speaker {
  def speak(): String
}

// Trais as abstract class
trait Walker {
  def walk(): Unit
  def walkSlowly(): Unit = println("*dog is walking slowly*")
  def badWalk: Unit = println("Ha! You fool! The name of this method is a LIE!")
}

// Wild non-dog trait for mixing in on the fly
trait Gamer {
  def game(): Unit = println("It's a meee, Mario!!!")
}

class Dog extends TailWagger with Speaker with Walker {
  // If you don't define the TailWagger methods Scala's gonna be MAD
  var tailIsWagging = false
  def startTail(): Unit = tailIsWagging = true
  def stopTail(): Unit = tailIsWagging = false

  // Same goes for speaker methods
  def speak(): String = "Hello, I am a dog."

  // And the abstract method in the walker trait. Also override the bad walk.
  def walk(): Unit = println("*dog is walking at normal doggie pace*")
  override def badWalk: Unit = println("*dog is walking badly*")

  // And if you don't define toString it won't print pretty
  override def toString(): String = s"Dog with tail that's ${if (tailIsWagging) "wagging" else "not wagging"}"
}

object Main extends App {
  val doggie = new Dog() with Gamer
  println(doggie)
  doggie.startTail()
  println(doggie)
  doggie.stopTail()
  println(doggie)
  println(s"Doggie says: '${doggie.speak}'")
  doggie.walkSlowly
  doggie.walk
  doggie.badWalk
  doggie.game
}
