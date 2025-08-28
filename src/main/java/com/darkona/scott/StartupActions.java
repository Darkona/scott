package com.darkona.scott;

import io.github.darkona.logged.Logged;
import io.github.darkona.logged.api.LogDecorator;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component

public class StartupActions {

    private final DemoService demoService;

    private static final Logger log = LoggerFactory.getLogger(StartupActions.class);
    private final LogDecorator deco;

    public StartupActions(DemoService demoService, LogDecorator deco) {
        this.demoService = demoService;
        this.deco = deco;
    }

    @EventListener(ApplicationReadyEvent.class)
    @WithSpan("MySpan")
    @Logged
    public void onReady() {

        log.info("MDC MAP = {}", org.slf4j.MDC.getCopyOfContextMap());
        log.warn(deco.red("------- Scott Starting --------"));
        log.info(deco.pink(demoService.salute()));
        demoService.sum(2, 3);
        demoService.divide(4500, 25);
        try {
            demoService.explode();
        } catch (Exception e) {
            // ignore
        }

        demoService.IAmInvisible(Map.of(
                "1", deco.green("Bulbasaur"),
                "2", deco.green("Ivysaur"),
                "3", deco.green("Venusaur"),
                "4", deco.cyan("Squirtle"),
                "5", deco.cyan("Wartortle"),
                "6", deco.cyan("Blastoise"),
                "7", deco.orange("Charmander"),
                "8", deco.orange("Charmeleon"),
                "9", deco.orange("Charizard")

        ));
        log.info(deco.green("------- Scott Finished Startup Actions --------"));
    }
}