package sh.stefoosh.sportsdata.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
