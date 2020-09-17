object MatchFunction extends App {
  println(variantOne(0))
  println(variantTwo(0))
  println(variantOne(""))
  println(variantTwo(""))
  println(variantOne(1))
  println(variantTwo(1))

  def variantOne(a: Any): Boolean = {
    if (a == 0 | a == "") {
      return false
    } else {
      return true
    }
  }

  def variantTwo(a: Any): Boolean = a match {
    case 0 | "" => false
    case _ => true
  }
}
