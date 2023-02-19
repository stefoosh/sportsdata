package sh.stefoosh.sportsdata.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

@SpringBootTest("service.apiAuthHeaderKey=Hello")
public class MyServiceTest {

	@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
	@SpringBootApplication
	static class TestConfiguration {
	}

	@Autowired
	private MyService myService;

	@Test
	public void contextLoads() {
		assertThat(myService.message()).isNotNull();
	}
}
