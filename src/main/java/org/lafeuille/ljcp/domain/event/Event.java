package org.lafeuille.ljcp.domain.event;

import org.lafeuille.ljcp.domain.DomainBase;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Event extends DomainBase {

    @Column(length = Lengths.TITLE)
    @Size(max = Lengths.TITLE)
    @NotBlank
    private String title;

    @Lob
    private String description;

    @NotNull
    private LocalDate startDate;

    private LocalTime startTime;

    public Event() {
        this(Clock.systemDefaultZone());
    }

    public Event(@NotNull Clock clock) {
        super(clock);
        this.startDate = LocalDate.now(clock);
    }

    public Event(@NotNull Event that) {
        super(that);
        this.title = that.title;
        this.description = that.description;
        this.startDate = that.startDate;
        this.startTime = that.startTime;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Nullable
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     *
     */
    enum Lengths {
        ;
        static final int TITLE = 100;
    }
}
