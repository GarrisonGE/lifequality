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
    @Autowired
    @Qualifier(value="hos")
    PointRDD hosPointRDD;
    public int getHosCount(Envelope rangeQueryWindow) throws Exception {
        JavaRDD test = RangeQuery.SpatialRangeQuery(hosPointRDD,rangeQueryWindow,false,false);
        return test.collect().size();
    }
}
