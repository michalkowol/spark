package pl.spark.synonyms

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
object SimpleApp {
  def showMostPopularWords(wordDataFile: RDD[String]) = {
    val frequency = wordDataFile.flatMap(line => line.split("\\W+"))
      .map(word => (word.toLowerCase, 1))
      .reduceByKey((a, b) => a + b).map { case (word, count) => (count, word) }
      .sortByKey(false)
      .collect()

    println("Most popular words")
    frequency.take(50).foreach(println)
  }
  def main(args: Array[String]) {
    val file = args(0)
    val wordnetDataFile = args(1)
    val sentence = args(2)
    // /home/senu/dev/spark/WordNet-3.0/dict

    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    sc.addJar("/home/senu/dev/spark/proj2/spark/lib/jaws-bin.jar")
    val wordDataFile = sc.textFile(file, 2).cache()

    val words = wordDataFile.flatMap(line => line.split("\\W+"))
    val totalWordCount = words.count()
    val wordCountMap = words
      .map(word => (word.toLowerCase, 1))
      .reduceByKey((a, b) => a + b)
      .collectAsMap()

    println(s"Total word count $totalWordCount")

    val synonyms = new WordnetSynonyms(wordnetDataFile)

   // showMostPopularWords(wordDataFile)

    val threshold = 0.001

    sentence.split("\\W+").map(_.toLowerCase).map {
      word =>
        val wordCount = wordCountMap.getOrElse(word, 0)
        val frequency = wordCount.toFloat / totalWordCount
        if (frequency < threshold) {
          val synStr = synonyms.synonyms(word)
          "%s[%8.7f %s] ".format(word, frequency, synStr.mkString("|"))
        } else {
          "%s ".format(word)
        }
    } foreach { x=> print(x)  }

  }
}