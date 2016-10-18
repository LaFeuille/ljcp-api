package org.lafeuille.ljcp.core.event;

import org.hibernate.validator.constraints.NotBlank;
import org.lafeuille.ljcp.infra.JpaParent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Event extends JpaParent {

    @Column(length = Lengths.TITLE)
    @Size(max = Lengths.TITLE)
    @NotBlank
    private String title;

    @Lob
    private String description;

    @NotNull
    private Date startDate;

    private Time startTime;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate.toLocalDate();
    }

    public LocalTime getStartTime() {
        return startTime == null ? null : startTime.toLocalTime();
    }

    /**
     *
     */
    interface Lengths {
        int TITLE = 100;
    }
}
