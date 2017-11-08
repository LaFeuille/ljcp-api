package org.lafeuille.ljcp.core.event;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
@ConditionalOnClass(Faker.class)
@ConditionalOnProperty("ljcp.event.filler.enabled")
public class EventFiller implements ApplicationListener<ApplicationReadyEvent> {

    private final EventGen eventGen;

    private final EventRepository eventRepository;

    private final Faker faker;

    public EventFiller(EventGen eventGen, EventRepository eventRepository, Faker faker) {
        this.eventGen = eventGen;
        this.eventRepository = eventRepository;
        this.faker = faker;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LongStream.range(0, faker.number().randomNumber(3, false))
                .mapToObj(i -> eventGen.get())
                .forEach(eventRepository::save);
    }
}
