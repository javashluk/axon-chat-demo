package io.axoniq.labs.chat.saga;

import java.time.Instant;

import org.axonframework.axonserver.connector.event.axon.AxonServerEventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import io.axoniq.labs.chat.coreapi.EndTestEvent;
import io.axoniq.labs.chat.coreapi.ScheduledFromSagaEvent;
import io.axoniq.labs.chat.coreapi.StartSchedulingTestEvent;

@Saga
public class SchedulingTestSaga {

	private ScheduleToken scheduleToken;
	private transient AxonServerEventScheduler eventScheduler;

	@Autowired
	public void setEventScheduler(AxonServerEventScheduler eventScheduler) {
		this.eventScheduler = eventScheduler;
	}

	@StartSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(StartSchedulingTestEvent event) {
		scheduleToken = eventScheduler.schedule(Instant.now().plusMillis(1000), new ScheduledFromSagaEvent(event.getId()));
		System.out.println(scheduleToken);
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(EndTestEvent event) {
		eventScheduler.cancelSchedule(scheduleToken);
	}

}
