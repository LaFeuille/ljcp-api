package org.lafeuille.ljcp.infra;

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
import java.util.concurrent.ThreadLocalRandom;

@MappedSuperclass
public abstract class JpaParent implements Persistable<Long>, Serializable {

    @Column(updatable = false)
    @CreatedDate
    private Timestamp createdDate = Timestamp.from(Instant.now());

    @Id
    private long id = ThreadLocalRandom.current().nextLong();

    @LastModifiedDate
    @Version
    private Timestamp lastModifiedDate;

    public Instant getCreatedDate() {
        return createdDate.toInstant();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof JpaParent))
            return false;
        JpaParent that = (JpaParent) o;
        return id == that.id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate == null ? null : lastModifiedDate.toInstant();
    }

    public UUID getUUID() {
        return UUID.nameUUIDFromBytes(String.valueOf(id).getBytes());
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public boolean isNew() {
        return lastModifiedDate == null;
    }
}
