package org.example;

import org.example.models.Appointment;
import org.example.models.Employee;
import org.example.models.Service;
import org.example.repository.AppointmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.repository.ServiceRepository;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static org.example.infrastructure.database.mysql.MysqlConnector.connect;

public class Main {
    public static void main(String[] args) {
        Connection connection = connect("localhost:3306", "root", "", "appointments_helper");
        EmployeeRepository employeeRepository = new EmployeeRepository(connection);
        AppointmentRepository appointmentRepository = new AppointmentRepository(connection);
        ServiceRepository serviceRepository = new ServiceRepository(connection);
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;
        boolean firstRun = true;

        System.out.println("\nSistema de carga de turnos - MP&FF \n\n");

        while (!exitProgram) {
            if (!firstRun) {
                System.out.print("""
                        ¿Quiere realizar otra acción?
                        n. No
                        otro. Si
                        """);
                if (scanner.next().charAt(0) == 'n') {
                    System.out.println("Hasta luego!");
                    exitProgram = true;
                    continue;
                }
            }

            switch (chooseAction(scanner)) {
                case 1 -> createNewAppointment(scanner, appointmentRepository, employeeRepository, serviceRepository);
                case 2 -> System.out.println("opcion 2");
                case 3 -> saveNewEmployee(scanner, employeeRepository);
                case 4 -> getAllEmployees(employeeRepository);
                case 5 -> saveNewService(scanner, serviceRepository);
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
                4. Ver empleados
                5. Cargar servicios
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

    public static void saveNewService(Scanner scanner, ServiceRepository repository){
        System.out.print("Ingrese el nombre del nuevo servicio: ");
        scanner.nextLine(); // para que no rompa
        String name = scanner.nextLine();

        System.out.print("Ingrese la duracion en minutos del servicio: ");
        int duration = scanner.nextInt();

        scanner.nextLine();

        Service service = new Service(name, duration);
        repository.create(service);
    }

    public static void createNewAppointment(Scanner scanner, AppointmentRepository appointmentRepository,
                                            EmployeeRepository employeeRepository, ServiceRepository serviceRepository) {
        try {
            System.out.println("Elija a que empleado cargar turno");
            ArrayList<Employee> employees = employeeRepository.getAll();
            for (Employee employee : employees) {
                System.out.println("Id: " + employee.getId() + " name: " + employee.getName());
            }
            int employeeId = scanner.nextInt();

            System.out.println("Elija el servicio realizado");
            ArrayList<Service> services = serviceRepository.getAll();
            for (Service service : services){
                System.out.println("Id: "+ service.getId() + " name: " + service.getName() + " duration: " + service.getDuration());
            }
            int serviceId = scanner.nextInt();



            System.out.print("Ingresar nombre del cliente: ");
            scanner.nextLine();
            String clientName = scanner.nextLine();
            System.out.print("Ingresar fecha del servicio (formato: DD/MM/AAAA): ");
            String inputDate = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(inputDate);
            System.out.print("Ingresar monto del servicio:(incluir decimales con coma, aunque sean 0):  ");
            Double payment = scanner.nextDouble();

            Appointment appointment = new Appointment(employeeId, date, clientName, serviceId, payment);
            appointmentRepository.create(appointment);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Asegúrese de ingresar la fecha en formato dd/mm/aaaa.");
        }
    }


    public static void getAllEmployees(EmployeeRepository repository) {
        System.out.println("Los empleados cargados son: ");
        ArrayList<Employee> employees = repository.getAll();

        for (Employee employee : employees) {
            System.out.println("Id: " + employee.getId() + " name: " + employee.getName() + " percentage: " + employee.getPercentage());
        }
    }
}