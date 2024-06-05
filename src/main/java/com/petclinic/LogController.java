package com.petclinic;

import com.petclinic.aspect.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log")
    public void log() {
        logger.debug("this is a debug message");
        logger.info("this is an info");
        logger.warn("this is a warning");
        logger.error("this is an error");
    }
}
