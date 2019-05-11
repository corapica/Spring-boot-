package tn.biat.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by gkatzioura on 10/18/17.
 */
@Component
@Scope("prototype")
public class MyThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);

    @Override
    public void run() {
   
    	LOGGER.info("Called from thread");
     }
  
}