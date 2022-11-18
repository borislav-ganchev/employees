package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtil {
    public static final String COMMA_DELIMITER = ",";

    public static List<Employee> readEmployees(String filename) {
        List<Employee> partialEmployees = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip headers
            while (scanner.hasNextLine()) {
                partialEmployees.add(getEmployeeFromLine(scanner.nextLine()));
            }
            for(Employee partialEmployee : partialEmployees){
                Optional<Employee> optionalEmployee =
                        employees.stream()
                        .filter(emp -> emp.getEmpID() == partialEmployee.getEmpID())
                        .findFirst();

                if(optionalEmployee.isEmpty()) {
                    employees.add(partialEmployee);
                }else{
                    List<Project> projects =
                            Stream.concat(
                                    optionalEmployee.get().getProjects().stream(),
                                    partialEmployee.getProjects().stream())
                            .collect(Collectors.toList());
                    optionalEmployee.get().setProjects(projects);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    private static Employee getEmployeeFromLine(String line) {
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            long employeeID = rowScanner.nextLong();
            long projectID = rowScanner.nextLong();
            String startDateRaw = rowScanner.next();
            LocalDate startDate = null;
            if(!startDateRaw.equals("null")){
                startDate = LocalDate.parse(startDateRaw);
            }
            String endDateRaw = rowScanner.next();
            LocalDate endDate = null;
            if(!endDateRaw.equals("null")){
                endDate = LocalDate.parse(endDateRaw);
            }
            Project project = new Project(projectID, startDate, endDate);
            return new Employee(employeeID, Collections.singletonList(project));
        }
    }
}
