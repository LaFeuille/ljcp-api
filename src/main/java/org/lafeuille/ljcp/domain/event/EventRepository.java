package org.lafeuille.ljcp.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
