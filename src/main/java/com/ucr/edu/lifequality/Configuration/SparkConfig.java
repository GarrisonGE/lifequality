package com.ucr.edu.lifequality.Configuration;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoSerializer;
import org.datasyslab.geosparkviz.core.Serde.GeoSparkVizKryoRegistrator;
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
    public JavaSparkContext getSC(){
//        SparkConf conf = new SparkConf().setAppName("test").setMaster("local"); //without this, get error: A master URL must be set in your configuration
        Logger.getLogger("org").setLevel(Level.WARN);
        Logger.getLogger("akka").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setAppName("GeoSparkRunnableExample").setMaster("local[*]");
        conf.set("spark.serializer", KryoSerializer.class.getName());

        JavaSparkContext sc = new JavaSparkContext(conf);
        return sc;
    }
}
