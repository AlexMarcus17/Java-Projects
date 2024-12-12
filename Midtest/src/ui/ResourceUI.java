package ui;

import domain.Resource;
import domain.Storage;
import service.ResourceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ResourceUI {
    protected ResourceService service;

    public ResourceUI(ResourceService service) {
        this.service = service;
    }
    public void displayMenu() {
        System.out.println("1. Add a resource");
        System.out.println("2. Show all resources");
        System.out.println("0. Exit");
    }
    public void run(){

        addelements();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter resource type:");
                    System.out.println("1. Compute");
                    System.out.println("2. Storage");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    if (type == 1) {

                            System.out.println("Enter id: ");

                            String id = scanner.nextLine();
                            System.out.println("Enter expiration year: ");
                            int year= scanner.nextInt();
                            System.out.println("Enter expiration month: ");
                            int month= scanner.nextInt();
                            System.out.println("Enter expiration day: ");
                            int day= scanner.nextInt();
                            LocalDateTime exp = LocalDateTime.of(year,month,day,1,1);
                            System.out.println("Enter number of cores: ");
                            int cores= scanner.nextInt();
                            service.addCompute(id, exp, cores);

                    } else if (type == 2) {
                        try{
                            System.out.println("Enter id: ");
                            String id = scanner.nextLine();
                            System.out.println("Enter expiration year: ");
                            int year= scanner.nextInt();
                            System.out.println("Enter expiration month: ");
                            int month= scanner.nextInt();
                            System.out.println("Enter expiration day: ");
                            int day= scanner.nextInt();
                            LocalDateTime exp = LocalDateTime.of(year,month,day,1,1);
                            System.out.println("Enter capacity: ");
                            int capacity= scanner.nextInt();
                            System.out.println("Enter type:");
                            scanner.nextLine();
                            String storagetype = scanner.nextLine();
                            service.addStorage(id, exp, capacity, storagetype);
                        } catch (Exception e) {
                            System.out.println("Wrong type");
                        }
                    }
                    break;
                case 2:
                    ArrayList<Resource> resources = service.getResources();
                    for (Resource resource : resources) {
                        System.out.println(resource);
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
            }
        }
    }
    public void addelements(){
        service.addCompute("1", LocalDateTime.now(),8);
        service.addCompute("2", LocalDateTime.now(),16);
        service.addStorage("3", LocalDateTime.now(), 1000, "SSD");
        service.addStorage("4", LocalDateTime.now(), 500, "HDD");
    }
}
