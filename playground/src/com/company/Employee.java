package com.company;

import java.util.List;

public class Employee {
    private final long empID;
    private List<Project> projects;

    public Employee(long empID, List<Project> projectsWorked) {
        this.empID = empID;
        this.projects = projectsWorked;
    }

    public long getEmpID() {
        return empID;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}