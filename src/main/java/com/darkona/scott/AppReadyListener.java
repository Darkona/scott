package com.darkona.scott;

import io.github.darkona.logged.internals.LogDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadyListener {

    private final DemoService demoService;

    private static final Logger log = LoggerFactory.getLogger(AppReadyListener.class);
    private final LogDecorator deco;

    public AppReadyListener(DemoService demoService, LogDecorator deco) {this.demoService = demoService;
        this.deco = deco;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        log.info(demoService.salute());
        demoService.sum(2,3);
        demoService.divide(10000000, 200000);
        try {
            demoService.explode();
        }catch(Exception e){
            // ignore
        }
        log.info("\n{}", deco.bannerize("Hello World!", 50));
    }
}
