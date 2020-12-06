package com.ucr.edu.lifequality.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
@Configuration
public class SparkConfig {
    @Bean
    public SparkSession getSparkSession(){
        SparkConf conf = new SparkConf().setAppName("test").setMaster("local"); //without this, get error: A master URL must be set in your configuration

        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .config(conf) //without using conf, still get error: A master URL must be set in your configuration
                .getOrCreate();
        return spark;
    }
}
