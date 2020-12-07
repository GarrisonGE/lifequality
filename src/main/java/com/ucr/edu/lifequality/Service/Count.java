package com.ucr.edu.lifequality.Service;

import com.vividsolutions.jts.geom.Envelope;
import org.apache.spark.api.java.JavaRDD;
import org.datasyslab.geospark.spatialOperator.RangeQuery;
import org.datasyslab.geospark.spatialRDD.PointRDD;
import org.datasyslab.geospark.spatialRDD.PolygonRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Count {
    //get hospital point rdd
    @Autowired
    @Qualifier(value="hos")
    PointRDD hosPointRDD;
    // get poi point rdd
    @Autowired
    @Qualifier(value="poi")
    PointRDD poiPointRDD;
    //get park polygon rdd
    @Autowired
    @Qualifier(value = "park")
    PolygonRDD parkPolygonRDD;
    public int getHosCount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(hosPointRDD,rangeQueryWindow,false,true);
        return test.collect().size();
    }
    public int getPOICount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(poiPointRDD, rangeQueryWindow, false, true);
        return test.collect().size();
    }
    public int getParkCount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(parkPolygonRDD, rangeQueryWindow, false, true);
        return test.collect().size();
    }
    public float ranking(int c_hos,int c_poi, int c_park, float c_air, float w_hos, float w_poi, float w_park, float w_airquality) throws Exception {

        return (c_hos*w_hos + c_poi*w_poi + c_park*w_park + c_air*w_airquality);
    }
}
