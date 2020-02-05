package org.lafeuille.ljcp.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository repository;

    @Test
    public void put_and_get() {
        var raw = new Event();
        var id = this.entityManager.persistAndGetId(raw, UUID.class);
        var retrieved = repository.findById(id);

        Assertions.assertThat(retrieved).isPresent();
    }

    @Test
    public void put_many_and_count() {
        for (var i = 0; i < 100; i++) {
            var event = new Event();
            event.setStartDate(
                    LocalDate.of(2001, Month.JANUARY, 1).plusDays(i));
            event.setTitle("Event " + i);

            this.entityManager.persist(event);
        }

        assertThat(repository.count()).isEqualTo(100);
    }

}
