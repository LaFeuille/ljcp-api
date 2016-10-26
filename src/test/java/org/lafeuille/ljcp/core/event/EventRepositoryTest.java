package org.lafeuille.ljcp.core.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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

}
