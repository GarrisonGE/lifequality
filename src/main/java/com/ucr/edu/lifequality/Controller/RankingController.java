package com.ucr.edu.lifequality.Controller;


import com.ucr.edu.lifequality.Model.Location;
import com.ucr.edu.lifequality.Model.Rank;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RankingController {

    @Autowired
    Count count;

    @RequestMapping("/")
    public String getPage(){
        return "Welcome to the ranking!";
    }

    @RequestMapping("/getHospitals")
    public int getHospitals(@RequestParam Location ul,@RequestParam Location lr) throws Exception {
        Envelope range = new Envelope(ul.getLatitude(), ul.getLongitude(), lr.getLatitude(), lr.getLongitude());

        return count.getHosCount(range);
    }
    @RequestMapping("/getPOIs")
    public int getPOIs(@RequestParam Location ul,@RequestParam Location lr) throws Exception{
        Envelope range = new Envelope(ul.getLatitude(), ul.getLongitude(), lr.getLatitude(), lr.getLongitude());

        return count.getPOICount(range);
    }
    @RequestMapping("/rank")
    public ArrayList<Rank> getRank(@RequestParam Location ul,
                                   @RequestParam Location lr,
                                   @RequestParam(defaultValue = "3.6") float weight1,
                                   @RequestParam(defaultValue = "2.0") float weight2,
                                   @RequestParam(defaultValue = "0") float weight3,
                                   @RequestParam(defaultValue = "0") float weight4) throws Exception {
        ArrayList<Rank> result = new ArrayList<Rank>();
        double interval_la = (lr.getLatitude() - ul.getLatitude())/5;
        double interval_lg = (lr.getLongitude()- ul.getLongitude())/5;

        for(int i = 0; i <= 4; i++){
            for(int j = 0; j<= 4; j++){
                Rank rank = new Rank();
                double ul_la = ul.getLatitude() + (i * interval_la);
                double ul_lg = ul.getLongitude() + (j * interval_lg);
                double lr_la = ul_la + interval_la;
                double lr_lg = ul_lg + interval_lg;
                Location subUL = new Location(ul_la,ul_lg);
                Location subLR = new Location(lr_la,lr_lg);
                rank.setUl(subUL);
                rank.setLr(subLR);
                Envelope range = new Envelope(ul_la, ul_lg, lr_la, lr_lg);
                rank.setRank(count.ranking(range,weight1,weight2,weight3,weight4));
                result.add(rank);

            }
        }


        return result;
    }
}
