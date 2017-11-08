package org.lafeuille.ljcp.core.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository repository;

    @Test
    public void put_and_get() {
        Event raw = new Event();
        UUID id = this.entityManager.persistAndGetId(raw, UUID.class);
        Event retrieved = repository.findOne(id);

        assertThat(retrieved).isNotNull();
    }

    @Test
    public void put_many_and_count() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setStartDate(
                    LocalDate.of(2001, Month.JANUARY, 1).plusDays(i));
            event.setTitle("Event " + i);

            this.entityManager.persist(event);
        }

        assertThat(repository.count()).isEqualTo(100);
    }

}
