package pl.spark.synonyms

import org.scalatest.{Matchers, FlatSpec}

class WordnetSynonymsSpec extends FlatSpec with Matchers {

  "A WordnetSynonyms" should "return list of synonyms for given word" in {
    // given
    val synonymsMaker = new WordnetSynonyms

    // when
    val synonyms = synonymsMaker.synonyms("drunk")

    // then
    synonyms should contain allOf ("ripped", "intoxicated")
  }
}
