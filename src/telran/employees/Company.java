package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;
//SO far we don't consider optimization
public class Company implements Iterable<Employee>{
	private Employee[] employees;
	public void addEmployee(Employee empl) {
		//if an employee with id equaled to id of empl exists, then to throw IllegalStateException
		long id = empl.getId();
		if(getEmployee(id) != null) {
			throw new IllegalStateException(String.format("employee with id %d already exists",
					id));
		}
		employees = Arrays.insertSorted(employees, empl, Comparator.naturalOrder());
		
	}
	public Employee getEmployee(long id) {
		//if the company doesn't have such employee, then return null
		int index = Arrays.binarySearch(employees,  new Employee(id, 0, null), Comparator.naturalOrder());
		return index < 0 ? null : employees[index];
	}
	public Employee removeEmployee(long id) {
		//removes from the company an employee with a given id
		//if such employee doesn't exist, throw NoSuchElementException
		//returns reference to being removed employee
		Employee result = getEmployee(id);
		if(result == null) {
			throw new NoSuchElementException
			(String.format("Employee with id %d doesn't exist", id));
		}
		employees = Arrays.removeIf(employees, e -> e.getId() == id);
		return result;
	}
	public int getDepartmentBudget(String department) {
		
		//returns sum of salary values for all employees of a given department
		//if employees of a given department don't exist, returns 0
		int result = 0;
		for(Employee empl: employees) {
			if(empl.getDepartment().equals(department)) {
				result += empl.computeSalary();
			}
		}
		return result;
	}
	public Company(Employee[] employees) {
		this.employees = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees);
	}
	@Override
	public Iterator<Employee> iterator() {
		
		return new CompanyIterator();
	}
	public String[] getDepartments() {
		String [] departments = new String[0];
		for(Employee empl: employees) {
			String department = empl.getDepartment();
			departments = insertToDepartments(departments, department);
		}
		return departments;
	}
	private String[] insertToDepartments(String[] departments, String department) {
		String[] result = departments;
		int index = Arrays.binarySearch(departments, department, String::compareTo);
		if (index < 0) {
			result = Arrays.insert(departments, -(index + 1), department);
		}
		return result;
		
	}
	public Manager[] getManagersWithMostFactor() {
		//TODO
		//returns array of managers with the most factor value
		return null;
	}
	private class CompanyIterator implements Iterator<Employee> {
		int currentIndex = 0;
		//iterating all employees in the ascending order of the ID values
		@Override
		public boolean hasNext() {
			return currentIndex < employees.length;
		}
		@Override
		public Employee next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return employees[currentIndex++];
		}
		
	}
	
}
