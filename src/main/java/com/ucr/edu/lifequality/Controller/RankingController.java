package com.ucr.edu.lifequality.Controller;


import com.ucr.edu.lifequality.Service.Count;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.datasyslab.geospark.spatialOperator.RangeQuery;
import org.datasyslab.geospark.spatialRDD.PointRDD;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingController {

    @Autowired
    Count count;

    @RequestMapping("/")
    public String getPage(){
        return "Welcome to the ranking!";
    }

    @RequestMapping("/getHospitals")
    public int getHospitals() throws Exception {
        Envelope range = new Envelope(-90.01,-80.01,30.01,40.01);

        return count.getHosCount(range);





    }
}
