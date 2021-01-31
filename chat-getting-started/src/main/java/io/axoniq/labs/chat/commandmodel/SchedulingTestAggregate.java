package io.axoniq.labs.chat.commandmodel;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import io.axoniq.labs.chat.coreapi.ScheduledFromSagaEvent;
import io.axoniq.labs.chat.coreapi.StartSchedulingTestCommand;
import io.axoniq.labs.chat.coreapi.StartSchedulingTestEvent;

@Aggregate
public class SchedulingTestAggregate {

	@AggregateIdentifier
	private String id;

	public SchedulingTestAggregate() {
	}

	@CommandHandler
	public SchedulingTestAggregate(StartSchedulingTestCommand command) {
		System.out.println("aggregate started");
		apply(new StartSchedulingTestEvent(command.getId()));
	}

	@EventSourcingHandler
	public void on(StartSchedulingTestEvent event) {
		this.id = event.getId();
	}

	@EventHandler
	public void on(ScheduledFromSagaEvent event) {
		System.out.println("Scheduling from saga was successful" + event.getId());
	}
}
