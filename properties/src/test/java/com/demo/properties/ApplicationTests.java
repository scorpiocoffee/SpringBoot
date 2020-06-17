package com.demo.properties;

import com.demo.properties.service.BlogProperties;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

    private static final Log log = LogFactory.getLog(BlogProperties.class);

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void test1() throws Exception {
        assertEquals("Scorpio", blogProperties.getName());
        assertEquals("Learn Spring Boot", blogProperties.getTitle());
        assertEquals("Scorpio is doing the lession named \"Learn Spring Boot\"", blogProperties.getDesc());

        log.info("Random number output is: ");
        log.info("Random characters: " + blogProperties.getValue());
        log.info("Random int number: " + blogProperties.getNumber());
        log.info("Random long number: " + blogProperties.getBignumber());
        log.info("Random number smaller than 10: " + blogProperties.getTest1());
        log.info("Random number between 10 to 20: " + blogProperties.getTest2());
    }

}
