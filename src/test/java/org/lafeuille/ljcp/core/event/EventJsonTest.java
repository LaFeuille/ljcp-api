package org.lafeuille.ljcp.core.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class EventJsonTest {

    private Clock clock;

    @Autowired
    private JacksonTester<Event> json;

    @Before
    public void setUp() {
        clock = Clock.fixed(Instant.EPOCH, ZoneId.systemDefault());
    }

    @Test
    public void event_write() throws IOException {
        Event event = new Event(clock);
        event.setTitle("My birthday");
        event.setDescription("Party for my birthday");
        event.setStartDate(LocalDate.of(1982, Month.AUGUST, 7));
        event.setStartTime(LocalTime.of(18, 30));

        assertThat(json.write(event))
                .isEqualToJson("{title:'My birthday', description:'Party for my birthday', startDate:'1982-08-07', startTime:'18:30:00', createdDate: '1970-01-01T00:00:00Z'}");
    }

}
