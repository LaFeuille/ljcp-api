package org.lafeuille.ljcp.infra.event;

import org.junit.jupiter.api.Test;
import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class EventJsonTest {

    @Autowired
    private JacksonTester<Event> json;

    @Test
    void event_write() throws IOException {
        var clock = Clock.fixed(Instant.EPOCH, ZoneId.of("GMT"));
        var event = new Event(clock);
        event.setTitle("My birthday");
        event.setDescription("Party for my birthday");
        event.setStartDate(LocalDate.of(1982, Month.AUGUST, 7));
        event.setStartTime(LocalTime.of(18, 30));

        assertThat(json.write(event)).isEqualToJson("Event.json");
    }

}
