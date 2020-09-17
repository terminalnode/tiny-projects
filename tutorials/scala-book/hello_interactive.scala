import scala.io.StdIn.readLine

object HelloInteractive extends App {
  print("Enter your first name: ")
  val firstName = readLine()
  print("Enter your last name:  ")
  val lastName = readLine()
  println(s"\nYour name is ${firstName} ${lastName}!")
}
