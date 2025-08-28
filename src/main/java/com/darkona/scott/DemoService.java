package com.darkona.scott;

import io.github.darkona.logged.Logged;
import io.github.darkona.logged.utils.Colorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;

@Service
public class DemoService {

    private final Logger log = LoggerFactory.getLogger(DemoService.class);


    @Logged
    public int sum(int a, int b) {
        log.info("MDC MAP = {}", org.slf4j.MDC.getCopyOfContextMap());
        return a + b;
    }

    @Logged
    public void explode() {
        throw new RuntimeException("BOOM: something went wrong");
    }

    @Logged
    public void esperar()
    throws InterruptedException {
        Thread.sleep(1500);
    }
    @Logged
    private String salute2() {
        return "-> salute2" + salute3();
    }


    @Logged
    public String salute() {
        return "O-> salute1" + salute2();
    }

    @Logged
    private String salute3() {
        return "-> salute 3" + salute4();
    }

    @Logged
    private String salute4(){
        return "-> Salute 4: Hello";
    }

    @Logged(redactArgValues = {"denominator"})
    public double divide(double numerator, double denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Can't divide by zero");
        }
        return numerator / denominator;
    }

    //This log should only be sent to the LOKI appender (see logback-spring.xml and application-local.yaml)
    // So we add the marker "LOKI"
    @Logged(markers = "LOKI", args = false)
    public void IAmInvisible(Map<String, String> attr) {
        log.info("There wasn't any automatic log from DemoService::IAmInvisible before this one in the console by logged. But you should be able to see it in Grafana");
        attr.entrySet().stream()
            .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Integer::parseInt)))
            .forEach(e -> log.info("{} = {}", e.getKey(), Colorizer.customBg(0, 0, 0, e.getValue())));
    }
}
