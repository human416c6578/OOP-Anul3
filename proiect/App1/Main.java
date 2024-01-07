import java.util.Scanner;

import Database.*;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("/home/mrshark45/Workspace/faculty/Anul3/POO/proiect/App1/books.txt");
        database.printEntries();
        Scanner scanner = new Scanner(System.in);
        String clientInput;
        Boolean RUN = true;

        while (RUN) {
            System.out.println("1. Cauta Carte");
            System.out.println("2. Cauta Carte Binar");
            System.out.println("3. Stop");

            clientInput = scanner.nextLine();

            try {
                switch (Integer.parseInt(clientInput)) {
                    case 1:
                        getEntry(scanner, database);
                        break;
                    case 2:
                        getEntryBinary(scanner, database);
                        break;
                    case 3:
                        RUN = false;
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

        }

        scanner.close();

    }

    static void getEntry(Scanner scanner, Database database) {
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();

        Entry entry = database.getEntry(author, title);
        System.out.println("Numar exemplare: " + entry.getStock());
    }

    static void getEntryBinary(Scanner scanner, Database database) {
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();

        Entry entry = database.getEntryBinary(author, title);
        System.out.println("Numar exemplare: " + entry.getStock());
    }

}