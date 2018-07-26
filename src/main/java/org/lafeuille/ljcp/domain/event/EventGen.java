package org.lafeuille.ljcp.domain.event;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@ConditionalOnClass(Faker.class)
public class EventGen implements Supplier<Event> {

    private final Faker faker;

    public EventGen(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Event get() {
        var event = new Event();
        event.setTitle(
                faker.lorem().sentence());
        event.setDescription(
                faker.lorem().paragraph());
        event.setStartDate(
                faker.date().future(100, TimeUnit.DAYS)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        event.setStartTime(
                faker.date().future(14, TimeUnit.HOURS, Date.from(Instant.ofEpochSecond(0).plus(8, ChronoUnit.HOURS)))
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
        return event;
    }
}
