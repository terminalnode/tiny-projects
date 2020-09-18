import scala.collection.mutable

object Main extends App {
  // ------------- //
  // ARRAY BUFFERS //
  // ------------- //
  // ArrayBuffers are mutable, having it as val means we can't change the
  // reference but we can change it's contents.
  // Can be initialized as empty or with starting elements
  val numAb = mutable.ArrayBuffer(1, 2)
  numAb += 3
  numAb -= 1
  numAb += 6 += 6 += 6 // Freaky
  numAb ++= List(9, 9, 9)
  numAb --= List(6, 6, 6)
  println(numAb)
  println()

  // Other methods for ArrayBuffer include:
  // append(n), append(n1, n2), appendAll(Seq(n1, n2)), clear()
  // insert(pos, n), insertAll(pos, Seq(n1, n2)), prepend/prependAll
  // remove, trimStart, trimEnd

  // ----- //
  // LISTS //
  // ----- //
  // Lists are immutable linked lists. Adding or removing elements creates a new list.
  // Lists are great for prepending values, that's very quick.
  // Traversing the list is extremely slow, and appending to the list requires traversing it.
  // When appending and prepending, keep the : on the list side of the operator.
  // Kind of like in haskell i guess.
  val listA = List(1, 2, 3)
  val listB = 0 +: listA             // prepend 0 to listA
  val listC = List(-1, 0) ++: listA  // prependAll List(-1, 0) to a
  val listD = listA :+ 4             // appending to the list like a bad boy

  // List A is unmodified
  println(listA)

  // List B, C and D are based on list A, but distinct
  println(listB)
  println(listC)
  println(listD)

  // Hey, we can con a list into existance as well.
  val conList = 1 :: 2 :: 3 :: Nil // Lists end with a Nil, this is equivalent to List(1,2,3)
  println(conList)

  println()

  // Loopie-woopie wie wie
  for (num <- listC) println(num + 100)
  println()

  // ------- //
  // VECTORS //
  // ------- //
  // Vectors are also immutable, but indexed.
  // They're similar to ArrayLists in Java I think. Slow to add to, blazing fast to read from.
  // The operators are pretty much identical to lists.
  val victorVector = Vector("Viktor", "Victor", "Victoire", "Wiktor")
  val victoriaVector = Vector("Victoria", "Viktoria")
  val unisexVictor1 = victorVector ++ victoriaVector  // appending elements
  val unisexVictor2 = victorVector :++ victoriaVector // prepending elements
  // Prepending or appending doesn't really seem to make a difference.
  // I suppose it's either prepending victorVector to victoriaVector or appending victoriaVector to victorVector.
  // I.e. it doesn't really make a difference at all with vectors.
  println(unisexVictor1)
  println(unisexVictor2)
}
