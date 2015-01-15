```bash
cp ../../coca-sample/*.txt data/corpus/      
rm data/corpus/lexicon-coca.txt 
```

```bash
spark-submit --jars `readlink -f lib/jaws-bin.jar` --class pl.spark.synonyms.SimpleApp --master local\[2\] target/scala-2.10/spark-hackaton-app_2.10-1.0.jar   'data/corpus/*.txt'  /home/senu/dev/spark/WordNet-3.0/dict 'They resumed their marriage for a brief time in Toulouse in July 1807, and Louis was born, premature, two weeks short of nine months later.'
```

