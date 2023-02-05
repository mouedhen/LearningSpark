package com.virtualpairprogrammers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> inputData = new ArrayList<>();
        inputData.add(35);
        inputData.add(12);
        inputData.add(90);
        inputData.add(20);

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setAppName("statingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<Integer> originalIntegers = sc.parallelize(inputData);

        // Reduce on RDD
        Integer result = originalIntegers.reduce(Integer::sum);
        System.out.println("Reduce Result:" + result);

        // Map Operations on RDD
        JavaRDD<Double> sqrtRdd = originalIntegers.map(Math::sqrt);

        // System.out::println is not serializable
        // For multi CPU computer the code bellow will throw NotSerializableException
        // sqrtRdd.foreach(System.out::println);
        // to fix the issue:
        // NB._ collect() will return a standard Java collection not an RDD
        sqrtRdd.collect().forEach(System.out::println);

        // Count the number of elements
        System.out.println("count():" + sqrtRdd.count());

        // Count the number of elements using map/reduce
        JavaRDD<Long> singleIntegerRdd = sqrtRdd.map(value -> 1L);
        long count = singleIntegerRdd.reduce(Long::sum);
        System.out.println("Count Result:" + count);

        JavaRDD<IntegerWithSquareRoot> iwsRdd = originalIntegers.map(IntegerWithSquareRoot::new);
        iwsRdd.collect().forEach(System.out::println);

        sc.close();
    }
}
