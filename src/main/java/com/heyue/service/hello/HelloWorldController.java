package com.heyue.service.hello;

import com.heyue.service.city.CityService;
import com.heyue.service.city.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private CityService cityService;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody List<City> getAllCity() {
        List<City> cities = cityService.findAll();
        return cities;
    }

}
