package CaseStudy1_Day5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
	String name;
	double salary;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String toString() {
		return "Employee [ Name= " + name + " ,  Salary= "+salary+" ]";
	}
}
public class LambdaExpressionSortingFiltering {

	public static void main(String[] args) {
		List<Employee> emp = Arrays.asList(
				new Employee("Sanjith", 75000),
				new Employee("Harsha", 85000),
                new Employee("Reddy", 65000),
                new Employee("Alice", 95000),
                new Employee("Bob", 55000)
				);
		
		//sorting by name (lambda expression)
		System.out.println("Employee sorted by name: ");
		Collections.sort(emp, (e1, e2) -> e1.getName() .compareTo(e2.getName()));		                                            
//		emp.forEach(e -> System.out.println(e));
		emp.forEach(System.out::println);

		
		//sorting by salary (lambda expression)
		System.out.println("\nEmployee sorted by salary: ");
		Collections.sort(emp, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
		emp.forEach(e -> System.out.println(e));
//		emp.forEach(System.out::println);

		// Filtering employees with salary above threshold (70000)
        double threshold = 70000;
        System.out.println("\nEmployees with salary above " + threshold + ":");
        List<Employee> highEarners = emp.stream()
                .filter(e -> e.getSalary() > threshold)
                .collect(Collectors.toList());
        highEarners.forEach(System.out::println);
        
        // Alternative way: Sort using Comparator.comparing with method reference
        System.out.println("\nEmployees sorted by name (using method reference):");
        emp.sort(Comparator.comparing(Employee::getName));
        emp.forEach(System.out::println);
        
        System.out.println("\nEmployees sorted by salary (using method reference):");
        emp.sort(Comparator.comparingDouble(Employee::getSalary));
        emp.forEach(System.out::println);
	}

}
