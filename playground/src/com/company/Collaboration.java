package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collaboration{
    private long employeeID1;
    private long employeeID2;
    private Map<Long, Long> commonTimeOnProjects;

    public Collaboration(long employeeID1, long employeeID2) {
        this.employeeID1 = employeeID1;
        this.employeeID2 = employeeID2;
        this.commonTimeOnProjects = new HashMap<>();
    }

    public long getEmployeeID1() {
        return employeeID1;
    }

    public long getEmployeeID2() {
        return employeeID2;
    }

    public Map<Long, Long> getCommonTimeOnProjects() {
        return commonTimeOnProjects;
    }

    public long getTotalCommonTimeOnProjects() {
        return commonTimeOnProjects.values().stream().mapToLong(Long::longValue).sum();
    }

    public void printGrid(){
        System.out.println("EmployeeID1, EmployeeID2, ProjectID, DaysWorked");
        commonTimeOnProjects
                .forEach((key, value) -> System.out.printf("%s, %s, %s, %s\n", employeeID1, employeeID2, key, value));
    }

    public static Collaboration findLongestCollaboratingPair(List<Employee> employees){
        long maxCollaborationInDays = 0;
        Collaboration longestCollaboration = null;
        for( Employee employee1 : employees){
            for( Employee employee2 : employees){
                if( employee1 != employee2 ){
                    Collaboration collaboration = getCollaboration(employee1, employee2);
                    long currentTime = collaboration.getTotalCommonTimeOnProjects();
                    if(maxCollaborationInDays < currentTime){
                        maxCollaborationInDays = currentTime;
                        longestCollaboration = collaboration;
                    }
                }
            }
        }
        return longestCollaboration;
    }

    public static Collaboration getCollaboration(Employee employee1, Employee employee2) {
        Collaboration collaboration = new Collaboration(employee1.getEmpID(), employee2.getEmpID());
        for (Project project1 : employee1.getProjects()) {
            for (Project project2 : employee2.getProjects()) {
                if (project1.getProjectID() == project2.getProjectID()) {
                    long currentDurationInDays =
                            findCommonPeriodInDays(
                                    project1.getDateStarted(),
                                    project1.getDateFinished(),
                                    project2.getDateStarted(),
                                    project2.getDateFinished()
                            );
                    if(currentDurationInDays > 0){
                        collaboration.getCommonTimeOnProjects().put(project1.getProjectID(), currentDurationInDays);
                    }
                }
            }
        }
        return collaboration;
    }

    private static long findCommonPeriodInDays(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        end1 = (end1 != null) ? end1 : LocalDate.now();
        end2 = (end2 != null) ? end2 : LocalDate.now();

        LocalDate latestStart = start1.isAfter(start2) ? start1 : start2;
        LocalDate earliestEnd = end1.isBefore(end2) ? end1 : end2;

        return ChronoUnit.DAYS.between(latestStart, earliestEnd);
    }
}