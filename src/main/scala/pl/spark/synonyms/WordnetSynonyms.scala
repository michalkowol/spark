package pl.spark.synonyms

import edu.smu.tspell.wordnet._
import scala.collection.JavaConversions._
import scala.util.control.NonFatal

class WordnetSynonyms(path: String) extends Synonyms {

  val database = WordNetDatabase.getFileInstance()

  def synonyms(word: String): Seq[String] = {
    try {
      val wordNetDatabase = createWordNetDatabase
      val synsets = wordNetDatabase.getSynsets(word)
      val mostPopularSynset = mostPopularOne(synsets, word)
      val synonyms = mostPopularSynset.getWordForms
      synonyms
    } catch {
      case NonFatal(e) => Seq(word)
    }
  }

  private def mostPopularOne(synsets: Seq[Synset], word: String): Synset = {
    synsets.maxBy(s => s.getTagCount(word))
  }

  private def createWordNetDatabase: WordNetDatabase = {
    System.setProperty("wordnet.database.dir", path)
    val database = WordNetDatabase.getFileInstance()
    database
  }
}

object WordnetApp extends App {
  val synonyms = new WordnetSynonyms("/Users/kowolm/tmp/spark/wordnet/dict").synonyms("go")
  println(synonyms)
}