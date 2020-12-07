package com.ucr.edu.lifequality.Controller;


import com.ucr.edu.lifequality.Model.Location;
import com.ucr.edu.lifequality.Model.Rank;
import com.ucr.edu.lifequality.Service.Count;
import com.vividsolutions.jts.geom.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@CrossOrigin
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
        Envelope range = new Envelope(ul.getLongitude(), ul.getLatitude(), lr.getLongitude(), lr.getLatitude());

        return count.getHosCount(range);
    }
    @RequestMapping("/getPOIs")
    public int getPOIs(@RequestParam Location ul,@RequestParam Location lr) throws Exception{
        Envelope range = new Envelope(ul.getLongitude(), ul.getLatitude(), lr.getLongitude(), lr.getLatitude());

        return count.getPOICount(range);
    }
    @RequestMapping("/getParks")
    public int getParks(@RequestParam Location ul, @RequestParam Location lr) throws Exception {
        Envelope range = new Envelope(ul.getLongitude(), ul.getLatitude(), lr.getLongitude(), lr.getLatitude());
        return count.getParkCount(range);
    }
    @RequestMapping("/rank")
    public ArrayList<Rank> getRank(@RequestParam Location ul,
                                   @RequestParam Location lr,
                                   @RequestParam(defaultValue = "1.5") float w_hos,
                                   @RequestParam(defaultValue = "2.0") float w_poi,
                                   @RequestParam(defaultValue = "0.5") float w_park,
                                   @RequestParam(defaultValue = "1") float w_airquality) throws Exception {
        ArrayList<Rank> result = new ArrayList<Rank>();
        double interval_lg = (lr.getLongitude()- ul.getLongitude())/3;
        double interval_la = (lr.getLatitude() - ul.getLatitude())/3;


        for(int i = 0; i <= 2; i++){
            for(int j = 0; j<= 2; j++){
                Rank rank = new Rank();
                //get 9 sub range
                double ul_lg = ul.getLongitude() + (j * interval_lg);
                double ul_la = ul.getLatitude() + (i * interval_la);
                double lr_lg = ul_lg + interval_lg;
                double lr_la = ul_la + interval_la;


                Location subUL = new Location(ul_lg,ul_la);
                Location subLR = new Location(lr_lg,lr_la);

                rank.setUl(subUL);
                rank.setLr(subLR);
                Envelope range = new Envelope(ul_lg, lr_lg, ul_la, lr_la);

                int c_hos = count.getHosCount(range);
                int c_poi = count.getPOICount(range);
                int c_park = new Random().nextInt(30);
                float c_airquality = new Random().nextInt(50);

                rank.setC_hos(c_hos);
                rank.setC_poi(c_poi);
                rank.setC_park(c_park);
                rank.setC_airquality(c_airquality);
                rank.setRank(count.ranking(c_hos,c_poi,c_park,c_airquality,w_hos,w_poi,w_park,w_airquality));

                result.add(rank);

            }
        }

        return result;
    }
}
