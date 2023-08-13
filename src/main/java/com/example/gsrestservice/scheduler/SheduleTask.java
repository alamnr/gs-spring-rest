package com.example.gsrestservice.scheduler;

import com.example.gsrestservice.consumingrest.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SheduleTask {

    private static final Logger log = LoggerFactory.getLogger(SheduleTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private  RestTemplate restTemplate;



    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){

        log.info("The time is now {}", dateFormat.format(new Date()));
        ResponseEntity<Quote> entity = restTemplate.exchange("http://localhost:8080/api/random", HttpMethod.GET,null, Quote.class);
        log.info("Id: {} , Quote: {}",entity.getBody().quote().id(),entity.getBody().quote().value());
        //log.info("Response: {}", entity.getBody().toString());
    }
}
