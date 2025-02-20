package contactBook;

import contact.Organization;
import contact.Person;
import contact.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactBook {
    public static List<Contact> contactsList = new ArrayList<>();

    public static void addContact(Scanner sc){
        System.out.print("Enter the type (person, organization): ");
        String type = sc.nextLine();

        if(type.equals("person")){
            contactsList.add(Person.add(sc));
        }
        else{
            contactsList.add(Organization.add(sc));
        }
    }

    public static void searchContact(Scanner sc){
        System.out.print("Enter search query");
        String type = sc.nextLine();
        List<Contact> SearchList = new ArrayList<>();

        Pattern pattern = Pattern.compile(type, Pattern.CASE_INSENSITIVE);

        for (int i = 0; i < contactsList.size(); i++) {
            Matcher matcher = pattern.matcher(contactsList.get(i).getAllInfo());
            if (matcher.find()) {
                SearchList.add(contactsList.get(i));
            }
        }

        System.out.println("Found " + ListSize(SearchList) + " results: \n");
        for (int i = 0; i < SearchList.size(); i++) {
            System.out.println( i+1 + ". " + SearchList.get(i).getAllInfo());
        }

        searchMenu(sc , SearchList);


    }

    public static void listContacts(Scanner sc ){
        if(ListSize(contactsList) == 0) {
            System.out.println("No records to list");
        }

        for (int i = 0; i < contactsList.size(); i++) {
            String info = contactsList.get(i).getAllInfo();
            System.out.println(i+1 + ". " + info);
        }

        System.out.println("\n[list] Enter action ([number], back):");
        String action = sc.nextLine();

        if ("back".equals(action)){
            Menu.start();
        } else{
            editContact(sc , contactsList , action);
        }

    }



    private static void searchMenu(Scanner sc , List<Contact>SearchList){
        if(ListSize(SearchList) == 0){
            System.out.println("No contacts in the list");
            return;
        }

        System.out.println("\n[search] Enter Action ([number], back, again): ");
        String action = sc.nextLine();

        switch (action){
            case "back": Menu.start();
                break;
            case "again": searchContact(sc);
                break;
            default : editContact(sc , contactsList , action);
        }
    }

    private static void editContact(Scanner sc, List<Contact> contactsList, String action) {
        int index = Integer.parseInt(action) - 1;
        Contact SelectedContact = contactsList.get(index);
        SelectedContact.getFields();
        System.out.println("\n[record] Enter action (edit, delete, menu): ");

        String option = sc.nextLine();
        switch (option){
            case "edit": SelectedContact.editFields(sc); break;
            case "delete": contactsList.remove(SelectedContact); break;
            case "back": Menu.start(); break;
            default:
                System.out.println("Invalid Input!");
        }

    }

    public static int ListSize(List<Contact>SearchList){
        return SearchList.size();
    }




}
