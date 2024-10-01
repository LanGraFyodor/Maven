package Stream_API;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван Иванов", 30, "IT", 100000.0));
        employees.add(new Employee("Мария Смирнова", 28, "HR", 80000.0));
        employees.add(new Employee("Петр Петров", 35, "IT", 120000.0));
        employees.add(new Employee("Ольга Кузнецова", 25, "Marketing", 70000.0));
        employees.add(new Employee("Александр Сидоров", 40, "IT", 110000.0));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название департамента для поиска средней зарплаты: ");
        String department = scanner.nextLine();

        // Находим среднюю зарплату по отделу с использованием Stream API
        OptionalDouble averageSalary = employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department)) // Фильтрация по отделу
                .mapToDouble(Employee::getSalary) // Преобразование к зарплатам
                .average(); // Поиск среднего значения

        if (averageSalary.isPresent()) {
            System.out.println("Средняя зарплата в департаменте " + department + ": " + averageSalary.getAsDouble());
        } else {
            System.out.println("Сотрудники в департаменте " + department + " не найдены.");
        }
    }
}
