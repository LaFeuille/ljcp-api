package org.lafeuille.ljcp.domain.event;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void copy_is_equal() {
        Event event0 = new Event();
        Event event1 = new Event(event0);

        assertThat(event0).isEqualTo(event1);
    }

    @Test
    public void copy_has_same_hashcode() {
        Event event0 = new Event();
        Event event1 = new Event(event0);

        assertThat(event0).hasSameHashCodeAs(event1);
    }

    @Test
    public void copy_has_same_properties() {
        Event event0 = new Event();
        event0.setTitle("Foobar");
        event0.setDescription("Foobar description");
        event0.setStartDate(LocalDate.of(2001, Month.JANUARY, 1));
        event0.setStartTime(LocalTime.NOON);

        Event event1 = new Event(event0);
        assertThat(event1).isEqualToComparingFieldByField(event1);
    }
}
