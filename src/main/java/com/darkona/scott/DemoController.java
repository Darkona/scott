package com.darkona.scott;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    private final DemoService demo;

    public DemoController(DemoService demo) {
        this.demo = demo;
    }

    @GetMapping("/sumar")
    public int sumar(@RequestParam int a, @RequestParam int b) {
        return demo.sum(a, b);
    }

    @GetMapping("/dividir")
    public double dividir(@RequestParam double a, @RequestParam double b) {
        return demo.divide(a, b);
    }

    @GetMapping("/esperar")
    public String esperar() throws InterruptedException {
        demo.esperar();
        return "Ok";
    }

    @GetMapping("/explotar")
    public void explotar() {
        demo.explode();
    }
}
