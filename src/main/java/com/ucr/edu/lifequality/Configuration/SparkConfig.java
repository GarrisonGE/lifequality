package com.ucr.edu.lifequality.Configuration;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoSerializer;
import org.datasyslab.geospark.enums.IndexType;
import org.datasyslab.geospark.spatialRDD.PointRDD;
import org.datasyslab.geosparkviz.core.Serde.GeoSparkVizKryoRegistrator;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.DependsOn;

import static org.apache.spark.sql.functions.col;
@Configuration
public class SparkConfig {

    private JavaSparkContext sc;
    public SparkConfig(){
        Logger.getLogger("org").setLevel(Level.WARN);
        Logger.getLogger("akka").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setAppName("GeoSparkRunnableExample").setMaster("local[*]");
        conf.set("spark.serializer", KryoSerializer.class.getName());

        this.sc = new JavaSparkContext(conf);
    }

    @Bean(name="hos")
    public PointRDD getHospitalRDD() throws Exception {
        JavaRDD<String> hosData = sc.textFile("../data/Hospitals.csv");
        JavaRDD<Point> hosPoint = hosData.map(x -> {
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

            return geometryFactory.createPoint(new Coordinate(new Double(x.split(",")[0]),new Double(x.split(",")[1])));
        });
        PointRDD hosPointRDD = new PointRDD(hosPoint);
        hosPointRDD.buildIndex(IndexType.RTREE,false);

        return hosPointRDD;
    }

}
