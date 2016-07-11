package org.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class MyService {

    private final Log log = LogFactory.getLog(MyService.class);

    public void processMessage(final Map<String, Object> msg) {
        log.info("========================");
        log.info(msg);
        log.info("========================");
    }

}
