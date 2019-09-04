package org.lafeuille.ljcp.rest.event;

import org.junit.Test;
import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.hateoas.Resource;

import static org.assertj.core.api.Assertions.assertThat;

public class EventProcessorTest {

    @Test
    public void process() {
        var eventResource = new Resource<>(new Event());

        var processor = new EventProcessor();
        var result = processor.process(eventResource);

        assertThat(result.getLinks()).isEmpty();
    }

}
