package net.lkrnac.book.eiws.chapter01.config;

import net.lkrnac.book.eiws.chapter01.async.Application;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {
	@Test
	public void contextLoads() {
	}
}
