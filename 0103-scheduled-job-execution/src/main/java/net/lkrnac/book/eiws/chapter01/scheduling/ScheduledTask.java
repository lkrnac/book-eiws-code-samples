package net.lkrnac.book.eiws.chapter01.scheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

	@Scheduled(fixedRate = 1000)
	public void call(){
		System.out.println(dateFormat.format(new Date()) + " - job kicked off");
	}
}
