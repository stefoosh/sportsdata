package sh.stefoosh.sportsdata.application;

import org.springframework.boot.SpringApplication;
import sh.stefoosh.sportsdata.application.controller.RootController;

public final class Application {

    private Application() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(RootController.class, args);
    }
}
