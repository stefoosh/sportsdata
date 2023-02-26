package sh.stefoosh.sportsdata.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public String root() {
        // TODO: log request parameters and response
        return "Hella Werld";
    }
}
