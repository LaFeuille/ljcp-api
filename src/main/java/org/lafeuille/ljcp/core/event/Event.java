package org.lafeuille.ljcp.core.event;

import org.hibernate.validator.constraints.NotBlank;
import org.lafeuille.ljcp.infra.JpaBase;

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
public class Event extends JpaBase {

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate == null ? null : startDate.toLocalDate();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate == null ? null : Date.valueOf(startDate);
    }

    public LocalTime getStartTime() {
        return startTime == null ? null : startTime.toLocalTime();
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime == null ? null : Time.valueOf(startTime);
    }

    /**
     *
     */
    interface Lengths {
        int TITLE = 100;
    }
}
