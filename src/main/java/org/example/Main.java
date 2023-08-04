package org.example;

import org.example.models.Employee;
import org.example.repository.EmployeeRepository;

import java.sql.Connection;
import java.util.Scanner;

import static org.example.infrastructure.database.mysql.MysqlConnector.connect;

public class Main {
    public static void main(String[] args) {
        Connection connection = connect("localhost:3307", "root", "root", "appointments_helper");
        EmployeeRepository employeeRepository = new EmployeeRepository(connection);
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;
        boolean firstRun = true;

        System.out.println("\nSistema de carga de turnos - MP&FF \n\n");

        while (!exitProgram) {
            if (!firstRun){
                System.out.print("""
                ¿Quiere realizar otra acción?
                n. No
                otro. Si
                """);
                if(scanner.next().charAt(0) == 'n') {
                    System.out.println("Hasta luego!");
                    exitProgram = true;
                    continue;
                }
            }

            switch (chooseAction(scanner)) {
                case 1 -> System.out.println("opcion 1");
                case 2 -> System.out.println("opcion 2");
                case 3 -> saveNewEmployee(scanner, employeeRepository);
                case 0 -> {
                    System.out.println("Hasta luego!");
                    exitProgram = true;
                }
                default -> System.out.println("Acción inválida");
            }

            firstRun = false;
        }
    }

    /**
     * metodo para elegir la accion a realizar
     */
    public static int chooseAction(Scanner scanner) {
        System.out.println("""
                Elija la acción a realizar:
                1. Cargar turnos
                2. Calcular pagos
                3. Cargar empleado
                0. Salir
                """);

        return scanner.nextInt();
    }

    /**
     * metodo para guardar un nuevo empleado
     */
    public static void saveNewEmployee(Scanner scanner, EmployeeRepository repository) {
        System.out.print("Ingrese el nombre del nuevo empleado: ");
        scanner.nextLine(); // para que no rompa
        String name = scanner.nextLine();

        System.out.print("Ingrese el porcentaje que recibe el empleado: ");
        int percentage = scanner.nextInt();

        scanner.nextLine();

        Employee employee = new Employee(name, percentage);
        repository.create(employee);
    }
}