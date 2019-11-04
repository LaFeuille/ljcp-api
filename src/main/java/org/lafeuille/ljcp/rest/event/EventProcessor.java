package org.lafeuille.ljcp.rest.event;

import org.lafeuille.ljcp.domain.event.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor implements RepresentationModelProcessor<EntityModel<Event>> {

    @Override
    public EntityModel<Event> process(EntityModel<Event> model) {
        return model;
    }
}
