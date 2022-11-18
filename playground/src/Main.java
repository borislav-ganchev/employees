import com.company.Collaboration;
import com.company.CsvUtil;
import com.company.Employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = CsvUtil.readEmployees("src/resources/input.csv");
        Collaboration collaboration = Collaboration.findLongestCollaboratingPair(employees);
        collaboration.printGrid();

    }
}