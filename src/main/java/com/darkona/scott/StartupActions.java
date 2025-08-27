package com.darkona.scott;

import io.github.darkona.logged.Logged;
import io.github.darkona.logged.api.LogDecorator;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component

public class StartupActions {

    private final DemoService demoService;

    private static final Logger log = LoggerFactory.getLogger(StartupActions.class);
    private final LogDecorator deco;

    public StartupActions(DemoService demoService, LogDecorator deco) {this.demoService = demoService;
        this.deco = deco;
    }

    @EventListener(ApplicationReadyEvent.class)
    @WithSpan("MySpan")
    @Logged
    public void onReady(){

        log.info("MDC MAP = {}", org.slf4j.MDC.getCopyOfContextMap());
        log.warn(deco.red("------- Scott Starting --------"));
        log.info(deco.pink(demoService.salute()));
        demoService.sum(2,3);
        demoService.divide(4500, 25);
        try {
            demoService.explode();
        }catch(Exception e){
            // ignore
        }
        log.info(deco.green("------- Scott Started --------"));
    }
}
