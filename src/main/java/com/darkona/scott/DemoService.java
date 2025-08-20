package com.darkona.scott;

import io.github.darkona.logged.Logged;
import io.github.darkona.logged.internals.LogDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final Logger log = LoggerFactory.getLogger(DemoService.class);
    private final LogDecorator deco;

    public DemoService(LogDecorator deco) {this.deco = deco;}

    @Logged
    public String salute() {
        return salute("World");
    }

    @Logged
    private String salute(String subject) {
        return String.format(internalCall(), subject);
    }

    @Logged
    public int sum(int a, int b) {
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
    private String internalCall() {
        return "Hello there";
    }

    @Logged(redactArgValues = {"denominator"})
    public double divide(double numerator, double denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Can't divide by zero");
        }
        return numerator / denominator;
    }
}
