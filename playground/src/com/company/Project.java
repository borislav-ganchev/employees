package com.company;

import java.time.LocalDate;
public class Project {
    private long projectID;
    private LocalDate dateStarted;
    private LocalDate dateFinished;

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", dateStarted=" + dateStarted +
                ", dateFinished=" + dateFinished +
                '}';
    }

    public Project(long projectID, LocalDate dateStarted, LocalDate dateFinished) {
        this.projectID = projectID;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
    }

    public long getProjectID() {
        return projectID;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }
}
