package pl.spark.synonyms

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    val file = args(0)
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val wordDataFile = sc.textFile(file, 2).cache()
    val frequency = wordDataFile.flatMap(line => line.split("\\W+"))
    							.map(word => (word.toLowerCase, 1))
    							.reduceByKey((a, b) => a + b).map { case (word, count) => (count, word) } 
    							.sortByKey(false)
    							.collect()
    frequency.take(10).foreach(println)
  }
}