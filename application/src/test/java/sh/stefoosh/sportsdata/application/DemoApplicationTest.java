package sh.stefoosh.sportsdata.application;

import static org.assertj.core.api.Assertions.assertThat;

import sh.stefoosh.sportsdata.service.MyService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTest {

	@Autowired
	private MyService myService;

	@Test
	public void contextLoads() {
		assertThat(myService.message()).isNotNull();
	}

}
