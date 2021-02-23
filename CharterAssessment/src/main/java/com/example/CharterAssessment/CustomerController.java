package com.example.CharterAssessment;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class CustomerController {
    boolean withinThree;

    @GetMapping("/api/CustomerJson")
    public JSONArray data() throws Exception {
        // Create a neat value object to hold the URL
        URL url = new URL("https://jsonplaceholder.typicode.com/users");
        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        // This line makes the request
        InputStream responseStream = connection.getInputStream();
        StringWriter responseString = new StringWriter();
        IOUtils.copy(responseStream, responseString, StandardCharsets.UTF_8);
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(responseString.toString());
        ArrayList<JSONObject> objectList = new ArrayList<>();
        DecimalFormat numberFormat = new DecimalFormat("#.00");




        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            double price = createRandomPrice();
            Date randomDate = createRandomDate();
            double over100 = price - 100;
            double rewards = 0;
            jsonObject.put("price", price);
            jsonObject.put("date",  randomDate);
            if(over100 > 0){
                rewards += (over100 * 2);
            }
            if(price > 100){
                rewards+= 50;
            }
            if(price > 50 && price < 100){
                price = price - 50;
                rewards += price;
            }
            if(price == 50){
                rewards += 50;
            }
            if(withinThree == true)
                jsonObject.put("rewards", rewards);
            else
                jsonObject.put("rewards", "This Date is Not Within Three Months");
        }

        //return (JSONArray) new JSONParser().parse(new FileReader("../MOCK_DATA.json"));
        return jsonArray;
    }

    private double createRandomPrice(){
        int min = 10;
        int max = 1500;
        double price = Math.random() * (max-min + 1) + min;
        return Math.round(price);
    }

    private Date createRandomDate() {
        Date endDate = new Date();
        final long fourMonths = (long) (1.051 * Math.pow(10, 10));
        final long threeMonths = (long) (7.884 * Math.pow(10, 9));

        long endMill = endDate.getTime();
        long startMill = endDate.getTime() - fourMonths;
        long threeMonthsAgo = endDate.getTime() - threeMonths;


        long randomTime = ThreadLocalRandom.current().nextLong(startMill, endMill);

        Date randomDate = new Date(randomTime);

        if(randomTime <= endMill && randomTime >= threeMonthsAgo)
            withinThree = true;
        else
            withinThree = false;

        return randomDate;

    }

}

