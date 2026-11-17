
 // File Name: EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method to read employee data
    public static String[] readEmployees() throws IOException {

        BufferedReader reader = new BufferedReader(
            new FileReader(Constants.FILE_PATH)
        );

        String line = reader.readLine();
        reader.close();

        return line.split(",");
    }

    // Method to write employee data
    public static void writeEmployees(String[] employees) throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.FILE_PATH)
        );

        writer.write(String.join(",", employees));
        writer.close();
    }

    // Method to append a new employee
    public static void appendEmployee(String employeeName) throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.FILE_PATH, true)
        );

        writer.write(", " + employeeName);
        writer.close();
    }

    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument.");
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            return;
        }

        String command = args[0].trim();

        // Check for invalid arguments
        if (command.isEmpty() ||
            (!command.equals("l") &&
             !command.equals("s") &&
             !command.equals("c") &&
             !command.startsWith("+") &&
             !command.startsWith("?") &&
             !command.startsWith("u") &&
             !command.startsWith("d"))) {

            System.out.println("Error: Invalid command.");
            System.out.println("Please enter a valid command.");
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            return;
        }

        // Check for missing employee name
        if (command.equals("+") ||
            command.equals("?") ||
            command.equals("u") ||
            command.equals("d")) {

            System.out.println("Error: Employee name is required.");
            return;
        }

        // Load employee data
        if (command.equals("l")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
                System.out.println("Error loading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                System.out.println(String.join(",", employees));
                System.out.println(
                    employees[new Random().nextInt(employees.length)]
                );

            } catch (Exception exception) {
                System.out.println("Error loading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {

            System.out.println("Loading data ...");

            try {
                appendEmployee(command.substring(1).trim());

            } catch (Exception exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                String searchName = command.substring(1).trim();
                boolean employeeFound = false;

                for (String employee : employees) {

                    if (employee.trim().equalsIgnoreCase(searchName)) {
                        System.out.println("Employee found: " + employee.trim());
                        employeeFound = true;
                        break;
                    }
                }

                if (!employeeFound) {
                    System.out.println("Employee not found: " + searchName);
                }

            } catch (Exception exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                String employeeData = String.join(",", employees);

                int wordCount = employeeData.trim().split("\\s+").length;

                int characterCount = employeeData.length();

                System.out.println(
                    wordCount + " word(s) found " + characterCount
                );

            } catch (Exception exception) {
                System.out.println("Error counting words.");
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                String employeeName = command.substring(1).trim();
                boolean employeeFound = false;

                for (int i = 0; i < employees.length; i++) {

                    if (employees[i].trim().equalsIgnoreCase(employeeName)) {
                        employees[i] = "Updated";
                        employeeFound = true;
                    }
                }

                if (employeeFound) {
                    writeEmployees(employees);
                    System.out.println("Employee updated successfully.");
                } else {
                    System.out.println("Employee not found.");
                }

            } catch (Exception exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {

            System.out.println("Loading data ...");

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readEmployees())
                );

                String employeeName = command.substring(1).trim();

                if (employeeList.remove(employeeName)) {
                    writeEmployees(employeeList.toArray(new String[0]));
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("Employee not found.");
                }

            } catch (Exception exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println("Data Deleted.");
        }
    }
}
