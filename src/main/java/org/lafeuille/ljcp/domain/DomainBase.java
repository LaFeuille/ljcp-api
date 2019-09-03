package org.lafeuille.ljcp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@MappedSuperclass
public abstract class DomainBase implements Persistable<UUID>, Serializable {

    @Id
    private UUID id;

    @Column(updatable = false)
    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    @Version
    private Instant lastModifiedDate;

    private DomainBase(@NotNull UUID id, @NotNull Instant createdDate, Instant lastModifiedDate) {
        this.id = requireNonNull(id);
        this.createdDate = requireNonNull(createdDate);
        this.lastModifiedDate = lastModifiedDate;
    }

    protected DomainBase(@NotNull DomainBase that) {
        this(that.id, that.createdDate, that.lastModifiedDate);
    }

    protected DomainBase(@NotNull Clock clock) {
        this(UUID.randomUUID(), Instant.now(clock), null);
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        var that = (DomainBase) o;
        return id.equals(that.id);
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
