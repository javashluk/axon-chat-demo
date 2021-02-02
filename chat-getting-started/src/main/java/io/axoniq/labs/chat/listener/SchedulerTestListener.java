package io.axoniq.labs.chat.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import io.axoniq.labs.chat.coreapi.ScheduledFromSagaEvent;

@Component
public class SchedulerTestListener {

	@EventHandler
	public void on(ScheduledFromSagaEvent event) {
		System.out.println("Scheduling from saga was successful" + event.getId());
	}

}
