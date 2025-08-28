package com.darkona.scott;

import io.github.darkona.logged.internals.Utf8Installer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScottApplication {

    public static void main(String[] args) {
        Utf8Installer.install();
//        System.out.println(Bannerizer.bannerize(Colorizer.rainbowify("Scott Application Starting"),80));
//        System.out.println(Colorizer.custom(Green.FOREST_GREEN,"Environment Variables: "));
//        System.out.println(Colorizer.custom(Yellow.GOLD, Bannerizer.ornament(80)));
//        for(var entry : System.getenv().entrySet()) {
//            System.out.println(Colorizer.blue(entry.getKey()) + "=" + Colorizer.orange(entry.getValue()));
//        }
//        System.out.println(Transformer.fill("=", 50));
//        System.out.println(Colorizer.custom(Yellow.GOLD, Bannerizer.ornament(80)));
        SpringApplication.run(ScottApplication.class, args);
    }

}
