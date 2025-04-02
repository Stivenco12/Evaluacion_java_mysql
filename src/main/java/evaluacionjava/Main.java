package evaluacionjava;

import java.util.Scanner;

import evaluacionjava.aplication.PacienteUseCase;
import evaluacionjava.aplication.userUseCase;
import evaluacionjava.domain.Entytis.User;
import evaluacionjava.domain.Entytis.inicioDeSesion;
import evaluacionjava.domain.Repository.PacienteRepository;
import evaluacionjava.domain.Repository.UserRepository;
import evaluacionjava.infrestructure.database.ConnectMysqlFactory;
import evaluacionjava.infrestructure.persistense.PacienteRepositoryImpl;
import evaluacionjava.infrestructure.persistense.UserRepositoryImpl;
import evaluacionjava.ui.UiMenuAdmin;
import evaluacionjava.ui.UiMenuPacientes;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            UserRepository repositoryUser = new UserRepositoryImpl(ConnectMysqlFactory.crearConexion());
            userUseCase userUsecase = new userUseCase(repositoryUser);
            PacienteRepository repositoryPaciente = new PacienteRepositoryImpl(ConnectMysqlFactory.crearConexion());
            PacienteUseCase pacienteUserCase = new PacienteUseCase(repositoryPaciente);
            String menu;
            do {
                System.out.println("\nbienvenido a progroma hospitalario");
                System.out.println("1.) Iniciar Sesion ");
                System.out.println("2.) registrarte    ");
                System.out.print("Elige una opcion = ");
                menu = sc.nextLine();
                System.out.println("");
                switch (menu) {
                    case "1" -> {
                        try {
                            System.out.println("=== Iniciar Sesión ===");
                            System.out.println("¿Desea iniciar sesión con nombre de usuario o con correo electrónico?");
                            System.out.println("1. Nombre de usuario");
                            System.out.println("2. Correo electrónico");
                            System.out.print("Seleccione una opción (1 o 2): ");
                            String opcion = sc.nextLine();
                            String userInput = "";

                            switch (opcion) {
                                case "1" -> {
                                    System.out.print("Ingrese su nombre de usuario: ");
                                    userInput = sc.nextLine();
                                }
                                case "2" -> {
                                    while (true) {
                                        System.out.print("Ingrese su correo electrónico: ");
                                        userInput = sc.nextLine();
                                        if (userInput.contains("@")) {
                                            break;
                                        } else {
                                            System.out.println("El correo electrónico debe contener un '@'. Intente nuevamente.");
                                        }
                                    }
                                }
                                default -> {
                                    System.out.println("Opción inválida.");
                                    main(args);
                                }
                            }
                            String password;    
                            System.out.print("Ingrese su contraseña: ");
                            password = sc.nextLine();

                            User user = userUsecase.searchByUsernameOrEmail(userInput);

                            if (user != null && user.getPassword().equals(password)) {
                                inicioDeSesion.setCurrentUserId(user.getId_user());
                                System.out.println("Inicio de sesión exitoso para: " + user.getName());
                                switch (user.getType().trim().toLowerCase()) {
                                    case "administradores" -> {
                                        System.out.println("\nRedirigiendo al panel de administración...\n");
                                        UiMenuAdmin.main(args);
                                    }
                                    case "pacientes" -> {
                                        System.out.println("\nRedirigiendo al menú de pacientes...\n");
                                        UiMenuPacientes.main(args);
                                    }

                                    default -> System.out.println("Error: tipo de usuario no reconocido.");
                                }
                                System.out.println("");
                            } else {
                                System.out.println("Nombre de usuario o contraseña incorrectos.");
                                System.out.println("");
                            }
                        } catch (Exception e) {
                            System.out.println("Error crítico");
                            System.out.println("");
                        }
                    }
                                       
                    case "2" -> {
                        try {
                            int id = 0;
                            System.out.print("Ingrese Nombre: ");
                            String nombre = sc.nextLine();
                            System.out.print("Ingrese apellido: ");
                            String apellido = sc.nextLine();
                            String nacimiento ;
                            do {
                                System.out.print("Ingrese fecha de nacimiento en formato 00/00/00: ");
                                nacimiento = sc.nextLine();
                                if (!nacimiento.contains("/")) {
                                    System.out.println("Error: la fecha debe contener '/'. Inténtelo de nuevo.");
                                }
                            } while (!nacimiento.contains("/"));
                            System.out.print("Ingrese su direccion: ");
                            String direccion = sc.nextLine();
                            System.out.print("Ingrese su telefono: ");
                            String telefono = sc.nextLine();
                            String email;
                            do {
                                System.out.print("Ingrese el email: ");
                                email = sc.nextLine();
                                if (!email.contains("@")) {
                                    System.out.println("Error: El email debe contener '@'. Inténtelo de nuevo.");
                                }
                            } while (!email.contains("@"));
                            
                            System.out.print("Ingrese el password: ");
                            String passwords = sc.nextLine();
                            String type1 = "pacientes";
                            
                            try {
                                userUsecase.registerUser(id, nombre, email, passwords, type1);
                                pacienteUserCase.registerPaciente(id, nombre, apellido, nacimiento, direccion, telefono, email);
                                System.out.println("\nSe ha creado exitosamente el usuario");
                                System.out.println("enviando para el menu de pacientes");
                                UiMenuPacientes.main(args);
                            } catch (Exception e) {
                                System.out.println("Error: por favor verifica que los datos ingresados sean correctos");
                            }
                        } catch (Exception e) {
                            System.out.println("Error = por favor verifica que los datos ingresados sean correctos");
                        }
                    }
                    case "3" -> System.out.println("saliendo..............");
                    default -> {
                        System.out.println("Dato seleccionado no existe, volviendo a menu........");
                        System.out.println();
                    }
                }
            }while(!menu.equals("3"));
        }catch (Exception e) {
            System.out.println("Error: por favor, vuelve a iniciar el programa.");
        }
    }
}