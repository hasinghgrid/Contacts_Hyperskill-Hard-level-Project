package contactBook;

import java.util.Scanner;

import static contactBook.ContactBook.*;

public class Menu {
    public static void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = sc.nextLine();

            switch (action) {
                case "add" :  addContact(sc);
                              break;

                case "list":  listContacts(sc);
                              break;
                case "search":searchContact(sc);
                               break;
                case "count": System.out.println("The Phone book has " + ListSize(contactsList) + " records");
                              break;
                case "exit": sc.close();
                             System.exit(0);
            }
        }
    }
}


