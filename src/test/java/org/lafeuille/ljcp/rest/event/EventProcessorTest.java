package org.lafeuille.ljcp.rest.event;

import org.junit.jupiter.api.Test;
import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.hateoas.EntityModel;

import static org.assertj.core.api.Assertions.assertThat;

class EventProcessorTest {

    @Test
    void process() {
        var eventModel = new EntityModel<>(new Event());

        var processor = new EventProcessor();
        var result = processor.process(eventModel);

        assertThat(result.getLinks()).isEmpty();
    }

}
