package pl.spark.synonyms

import net.sf.extjwnl.data.IndexWord

import scala.collection.JavaConversions.asScalaBuffer
import net.sf.extjwnl.dictionary.Dictionary

class WordnetSynonyms extends Synonyms {

  private val dictornary = Dictionary.getDefaultResourceInstance

  override def synonyms(word: String): Seq[String] = {
    for {
      wordFromDictinary <- dictionaryLookup(word)
      synset <- wordFromDictinary.getSenses
      word <- synset.getWords
    } yield word.getLemma
  }

  private def dictionaryLookup(word: String): Seq[IndexWord] = dictornary.lookupAllIndexWords(word).getIndexWordArray
}
