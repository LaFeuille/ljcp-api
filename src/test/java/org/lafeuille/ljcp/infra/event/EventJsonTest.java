package org.lafeuille.ljcp.infra.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class EventJsonTest {

    @Autowired
    private JacksonTester<Event> json;

    @Test
    public void event_write() throws IOException {
        var event = new Event();
        event.setTitle("My birthday");
        event.setDescription("Party for my birthday");
        event.setStartDate(LocalDate.of(1982, Month.AUGUST, 7));
        event.setStartTime(LocalTime.of(18, 30));

        assertThat(json.write(event)).isEqualToJson("Event.json");
    }

}
