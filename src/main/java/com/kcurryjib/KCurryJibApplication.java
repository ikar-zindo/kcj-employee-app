package com.kcurryjib;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KCurryJibApplication {

   private static final Logger LOGGER = LoggerFactory.getLogger(KCurryJibApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KCurryJibApplication.class, args);
	}

   @PostConstruct
   public void logApplicationStarted() { // logging START APPLICATION
      LOGGER.info("============================ START APPLICATION ============================");
   }
}
