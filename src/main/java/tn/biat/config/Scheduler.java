package tn.biat.config;

import tn.biat.service.AsynchronousService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Component
public class Scheduler {

    @Autowired
    private AsynchronousService checkAsyncService;

    @Scheduled(fixedDelay = 10000)
	public void checkTheScedule() throws IOException {
		System.out.println("llllllllllllllllll");

        System.out.println(checkAsyncService.param1);
       System.out.println(checkAsyncService.scrappe());
       return;
    	
    }

}