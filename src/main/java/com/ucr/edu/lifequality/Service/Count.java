package com.ucr.edu.lifequality.Service;

import com.vividsolutions.jts.geom.Envelope;
import org.apache.spark.api.java.JavaRDD;
import org.datasyslab.geospark.spatialOperator.RangeQuery;
import org.datasyslab.geospark.spatialRDD.PointRDD;
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
    public int getHosCount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(hosPointRDD,rangeQueryWindow,false,false);
        return test.collect().size();
    }
    public int getPOICount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(poiPointRDD, rangeQueryWindow, false, false);
        return test.collect().size();
    }
    public float ranking(Envelope rangeQueryWindow, float weight1, float weight2, float weight3, float weight4) throws Exception {
        int hosCount = this.getHosCount(rangeQueryWindow);
        int poiCount = this.getPOICount(rangeQueryWindow);
        return (hosCount*weight1 + poiCount*weight2);
    }
}
