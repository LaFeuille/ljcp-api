package org.lafeuille.ljcp.rest.event;

import org.lafeuille.ljcp.core.event.Event;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    @RequestMapping("{id}")
    public Event foobar(@PathVariable("id") Event event) {
        return event;
    }
}
