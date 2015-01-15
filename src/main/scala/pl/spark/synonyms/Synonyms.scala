package pl.spark.synonyms

trait Synonyms {
  def synonyms(word: String): Seq[String]
}