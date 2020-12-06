package com.ucr.edu.lifequality.Controller;


import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

@RestController
public class RankingController {
    @Autowired
    SparkSession spark;
    @RequestMapping("/")
    public String getPage(){
        return "Welcome to the ranking!";
    }
    @RequestMapping("/test")
    public String test(){
        return "test!";
    }
    @RequestMapping("/show")
    public String gethospital(){
        Dataset<Row> df_hos = spark.read() //DataFrame
                .option("header", "true")
                .csv("../data/Hospitals.csv"); // default path: file:/Users/pangzeyu/Desktop/CS226/project/spark-java-sql/
        return df_hos.collectAsList().toString(); // output the first 20 lines
    }
}
