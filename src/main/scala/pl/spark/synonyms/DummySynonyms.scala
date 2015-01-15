package pl.spark.synonyms

class DummySynonyms extends Synonyms {
  private val words = Map(
    "crimson" -> Seq("ruby", "deep red"),
    "home" -> Seq("dwelling", "home", "domicile", "abode", "habitation"),
    "problem" -> Seq("trouble", "difficulty")
  )
  
  def synonyms(word: String): Seq[String] = words.get(word) match {
    case Some(synonymsList) => synonymsList
    case None => Seq.empty
  }
}