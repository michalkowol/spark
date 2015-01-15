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
    val sentence = args(1)

    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val wordDataFile = sc.textFile(file, 2).cache()

    val words = wordDataFile.flatMap(line => line.split("\\W+"))
    val totalWordCount = words.count()
    val wordCountMap = words
      .map(word => (word.toLowerCase, 1))
      .reduceByKey((a, b) => a + b)
      .collectAsMap()

    println(s"Total word count $totalWordCount")

   // showMostPopularWords(wordDataFile)

    sentence.split("\\W+").map(_.toLowerCase).map {
      word =>  "%s[%s] ".format(word, wordCountMap.getOrElse(word, 0))
    } foreach { x=> print(x)  }

  }
}