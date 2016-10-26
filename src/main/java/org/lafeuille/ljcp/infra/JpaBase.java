package org.lafeuille.ljcp.infra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class JpaBase implements Persistable<UUID>, Serializable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(updatable = false)
    @CreatedDate
    private Timestamp createdDate = Timestamp.from(Instant.now());

    @LastModifiedDate
    @Version
    private Timestamp lastModifiedDate;

    public Instant getCreatedDate() {
        return createdDate.toInstant();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate == null ? null : lastModifiedDate.toInstant();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaBase jpaBase = (JpaBase) o;
        return id.equals(jpaBase.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return lastModifiedDate == null;
    }
}
