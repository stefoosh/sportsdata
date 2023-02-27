package sh.stefoosh.sportsdata.application.controller;

//import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_CONSTANTS;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_MODEL;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_REPOSITORY;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_WEB;

@SpringBootApplication(scanBasePackages = {
        SH_STEFOOSH_SPORTSDATA_WEB,
        SH_STEFOOSH_SPORTSDATA_CONSTANTS,
        SH_STEFOOSH_SPORTSDATA_REPOSITORY,
        SH_STEFOOSH_SPORTSDATA_MODEL,
})
@RestController
public class RootController {
//    @GetMapping("/")
//    public String root() {
//        return "Hella Werld";
//    }
}
