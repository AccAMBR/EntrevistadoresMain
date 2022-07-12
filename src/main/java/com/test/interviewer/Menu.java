package com.test.interviewer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    Scanner sc;

    public Menu() {
        sc = new Scanner(System.in);
        Interviewer.data = new ArrayList<Interviewer>();

        showMainMenu();
    }

    public void showMainMenu() {
        int option = 0;

        while (option != 4) {
            System.out.println("Seleccione la operacion a realizar:");
            System.out.println("1. Dar de alta un entrevistador");
            System.out.println("2. Consultar un entrevistador");
            System.out.println("3. Modificar un entrevistador");
            System.out.println("4. Salir");

            try {
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        addInterviewer();
                        break;
                    case 2:
                        searchInterviewer();
                        break;
                    case 3:
                        modifyInterviewer();
                        break;
                    case 4:
                        System.out.println("Gracias por usar el programa, espero que le funcionara adecuadamente!");
                        break;
                    default:
                        System.out.println("La opcion no es valida. Ingrese un número del 1 al 4");
                        break;
                }
            } catch(InputMismatchException ex) {
                System.out.println("Ha introducido una letra. Ingrese un número");
                option = 0;
                sc.nextLine();
            }
        };

        System.out.println("Programa terminado");
    }

    public void addInterviewer() {
        System.out.println("Ingresando un nuevo entrevistador");
        System.out.println("Ingrese el nombre del entrevistador: ");
        String name = sc.nextLine();
        System.out.println("Ingrese el apellido del entrevistador: ");
        String lastName = sc.nextLine();
        System.out.println("Ingrese el email del entrevistador: ");
        String email = sc.nextLine();
        System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
        Boolean isActive = sc.nextInt() == 1;
        System.out.println("El entrevistador es un administrador? (1=Si/2=No)");
        Boolean isAdmin = sc.nextInt() == 1;
        sc.nextLine();

        Interviewer interviewer = new Interviewer(name, lastName, email, isActive, isAdmin);
        interviewer.add();

        System.out.println(interviewer.toString());
    }

    public void searchInterviewer() {
        int searchBy = 0;
        String searchByS = null;
        while (searchBy == 0) {
            System.out.println("Seleccione el parametro de busqueda:");
            System.out.println("1. Email");
            System.out.println("2. Id");
            System.out.println("Ingrese el parametro de busqueda para consultar (1=email/2=id):");
            try {
                searchBy = sc.nextInt();
                switch (searchBy) {
                    case 1:
                        searchByS = "email";
                        break;
                    case 2:
                        searchByS = "id";
                        break;
                    default:
                        System.out.println("La opcion no es valida. Ingrese un número del 1 al 2");
                        searchBy = 0;
                        break;
                }
            } catch(InputMismatchException ex) {
                System.out.println("Ha introducido una letra. Ingrese un número");
                searchBy = 0;
            }
            sc.nextLine();
        }
        System.out.println("Ingrese el " + searchByS + " del entrevistador a consultar:");
        Interviewer interviewer = null;
        switch (searchBy) {
            case 1:
                String searchEmail = sc.nextLine();
                interviewer = Interviewer.getByEmail(searchEmail);
                break;
            case 2:
                try {
                int searchId = sc.nextInt();
                interviewer = Interviewer.getById(searchId);
                } catch(InputMismatchException ex) {
                    System.out.println("Ha introducido una letra. Ingrese un número");
                }
                break;
        }

        if (interviewer != null) {
            System.out.println("Entrevistador encontrado:");
            System.out.println(interviewer.toString());
        } else {
            System.out.println("Entrevistador no encontrado");
        }
    }

    public void modifyInterviewer() {
        int searchBy = 0;
        String searchByS = null;
        while (searchBy == 0) {
            System.out.println("Seleccione el parametro de busqueda:");
            System.out.println("1. Email");
            System.out.println("2. Id");
            try {
                searchBy = sc.nextInt();
                switch (searchBy) {
                    case 1:
                        searchByS = "email";
                        break;
                    case 2:
                        searchByS = "id";
                        break;
                    default:
                        System.out.println("La opcion no es valida. Ingrese un número del 1 al 2");
                        searchBy = 0;
                        break;
                }
            } catch(InputMismatchException ex) {
                System.out.println("Ha introducido una letra. Ingrese un número");
                searchBy = 0;
            }
            sc.nextLine();
        };
        System.out.println("Ingrese el "+searchByS+ " del entrevistador a modificar:");
        Interviewer interviewer = null;
        switch (searchBy) {
            case 1:
                String searchEmail = sc.nextLine();
                interviewer = Interviewer.getByEmail(searchEmail);
                break;
            case 2:
                try {
                    int searchId = sc.nextInt();
                    sc.nextLine();
                    interviewer = Interviewer.getById(searchId);
                    break;
                } catch(InputMismatchException ex) {
                    System.out.println("Ha introducido una letra. Ingrese un número");
                    break;
                }
        }

        if (interviewer == null) {
            System.out.println("Entrevistador no encontrado:");
        } else {
            System.out.println("Presionar Enter al mantener o ingresar el nuevo nombre: ");
            String newName = sc.nextLine();
            if (newName.equals("")) {
                newName = interviewer.name;
            }
            System.out.println("Presionar Enter al mantener o ingresar el nuevo apellido: ");
            String newLastName = sc.nextLine();
            if (newLastName.equals("")) {
                newLastName = interviewer.lastName;
            }
            System.out.println("Presionar Enter al mantener o ingresar el nuevo email: ");
            String newEmail = sc.nextLine();
            if (newEmail.equals("")) {
                newEmail = interviewer.email;
            }
            System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
            Boolean newIsActive = sc.nextInt() == 1;
            sc.nextLine();
            System.out.println("El entrevistador es un administrador? (1=Si/2=No)");
            Boolean newIsAdmin = sc.nextInt() == 1;
            sc.nextLine();

            interviewer.save(newName,newLastName,newEmail,newIsActive,newIsAdmin);

            System.out.println(interviewer.toString());
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}