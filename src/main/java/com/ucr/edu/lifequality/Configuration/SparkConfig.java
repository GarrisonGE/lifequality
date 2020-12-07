package com.ucr.edu.lifequality.Configuration;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoSerializer;
import org.datasyslab.geospark.enums.FileDataSplitter;
import org.datasyslab.geospark.enums.IndexType;
import org.datasyslab.geospark.spatialRDD.PointRDD;
import org.datasyslab.geospark.spatialRDD.PolygonRDD;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.spark.SparkConf;

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
    @Bean(name="poi")
    public PointRDD getPOIRDD() throws Exception {
        JavaRDD<String> poiData= sc.textFile("../data/poi.csv");
        JavaRDD<Point> poiPoint = poiData.map(x -> {
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

            return geometryFactory.createPoint(new Coordinate(new Double(x.split(",")[1]),new Double(x.split(",")[0])));
        });
        PointRDD poiPointRDD = new PointRDD(poiPoint);
        poiPointRDD.buildIndex(IndexType.RTREE,false);
        return poiPointRDD;
    }
    @Bean(name="park")
    public PolygonRDD getParkRDD() throws Exception {
        PolygonRDD parkPolygonRDD = new PolygonRDD(sc, "../data/parks.csv",0, -1, FileDataSplitter.WKT,  true);
        parkPolygonRDD.buildIndex(IndexType.RTREE,false);
        return parkPolygonRDD;
    }

}
