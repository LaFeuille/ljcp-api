package org.lafeuille.ljcp.rest.event;

import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor implements ResourceProcessor<Resource<Event>> {

    @Override
    public Resource<Event> process(Resource<Event> resource) {
        return resource;
    }
}
