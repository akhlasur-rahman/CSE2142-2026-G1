
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

        // Load employee data
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                System.out.println(String.join(",", employees));
                System.out.println(
                    employees[new Random().nextInt(employees.length)]
                );

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {

            System.out.println("Loading data ...");

            try {
                appendEmployee(args[0].substring(1));

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                boolean found = false;

                for (int i = 0; i < employees.length && !found; i++) {

                    if (employees[i].equals(args[0].substring(1))) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                char[] characters = String.join(",", employees).toCharArray();

                boolean inWord = false;
                int wordCount = 0;

                for (char character : characters) {

                    if (character == ' ') {

                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(wordCount + " word(s) found " + characters.length);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                for (int i = 0; i < employees.length; i++) {

                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployees(employees);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {

            System.out.println("Loading data ...");

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readEmployees())
                );

                employeeList.remove(args[0].substring(1));

                writeEmployees(employeeList.toArray(new String[0]));

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");
        }
    }
}
