// Metals LSP actually won't complain if we don't import Map,
// because the IMMUTABLE map is in scope by default. However
// with the immutable map we can't do the += operations
import collection.mutable.Map

// Same as with Map, the immutable set class is included by default
// but the trashy mutable set class has to be imported.
import collection.mutable.Set

object Main extends App {
  val countries = Map(
    "SE" -> "SWARJE",
    "PL" -> "Poland",
    "FR" -> "France",
  )
  countries += ("SU" -> "Soviet Union")
  countries ++= Map("JP" -> "Japan", "FI" -> "Finland")
  countries -= "SU" // Soviet Union is dissolved
  countries("SE") = "Sweden" // update entry with proper english name
  println(countries)

  // We can loop a map as well
  for ((key, value) <- countries) println(s"${value} is a country with country code ${key}")
  println()

  // Now lets do sets
  // There are other kinds of sets as well, such as SortedSet or LinkedHashSet.
  val uniqueNumbers = Set[Int](1, 2, 1, 2, 3)
  uniqueNumbers += 5 // would not work without mutable set import
  uniqueNumbers ++= Vector(1, 2, 4)
  println(uniqueNumbers)
  uniqueNumbers -= 1
  uniqueNumbers --= List(2, 3)
  println(uniqueNumbers)
  // There are tons of operations you can do with sets as well.
  // xs contains x, xs subsetOf ys, xs intersect ys, xs union ys and so on and so on.

  // Sets are also iterable.
  for (num <- uniqueNumbers) println(s"Look at my pretty number ${num}!")
  println()
}
