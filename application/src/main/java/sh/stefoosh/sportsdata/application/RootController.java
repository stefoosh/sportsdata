package sh.stefoosh.sportsdata.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static sh.stefoosh.sportsdata.constants.Package.*;

@SpringBootApplication(scanBasePackages = {
        SH_STEFOOSH_SPORTSDATA_WEB,
        SH_STEFOOSH_SPORTSDATA_CONSTANTS,
        SH_STEFOOSH_SPORTSDATA_REPOSITORY,
        SH_STEFOOSH_SPORTSDATA_MODEL,
})
@RestController public class RootController {
    @GetMapping("/")
    public String root() {
        // TODO: log request parameters and response
        return "Hella Werld";
    }

    public static void main(String[] args) {
        SpringApplication.run(RootController.class, args);
    }
}
