package evaluacionjava.ui;

import java.util.Scanner;

import evaluacionjava.aplication.CitaUseCase;
import evaluacionjava.aplication.EspecialidadUseCase;
import evaluacionjava.aplication.MedicoUseCase;
import evaluacionjava.aplication.PacienteUseCase;
import evaluacionjava.domain.Repository.CitaRepository;
import evaluacionjava.domain.Repository.EspecialidadRepository;
import evaluacionjava.domain.Repository.MedicoRepository;
import evaluacionjava.domain.Repository.PacienteRepository;
import evaluacionjava.infrestructure.database.ConnectMysqlFactory;
import evaluacionjava.infrestructure.persistense.CitaRepositoryImpl;
import evaluacionjava.infrestructure.persistense.EspecialidadRepositoryImpl;
import evaluacionjava.infrestructure.persistense.MedicoRepositoryImpl;
import evaluacionjava.infrestructure.persistense.PacienteRepositoryImpl;

public class UiMenuAdmin {
     public static void main(String[] args) {
         try (Scanner sc = new Scanner(System.in)) {
            EspecialidadRepository repositoryEspecialidad = new EspecialidadRepositoryImpl(ConnectMysqlFactory.crearConexion());
            EspecialidadUseCase especialidadUseCase = new EspecialidadUseCase(repositoryEspecialidad);
            MedicoRepository medicoRepository = new MedicoRepositoryImpl(ConnectMysqlFactory.crearConexion());
            MedicoUseCase medicoUseCase = new MedicoUseCase(medicoRepository);
            CitaRepository repositoryCita = new CitaRepositoryImpl(ConnectMysqlFactory.crearConexion());
            CitaUseCase citaUseCase = new CitaUseCase(repositoryCita);
            PacienteRepository pacienteRepository = new PacienteRepositoryImpl(ConnectMysqlFactory.crearConexion());
            PacienteUseCase pacienteUseCase = new PacienteUseCase(pacienteRepository);
            String menu;
            do {
                System.out.println("\nbienvenido a la sesion de admin");
                System.out.println("1.) Registrar medico ");
                System.out.println("2.) Ver todos los medicos registrados  ");
                System.out.println("3.) ver todos los pacientes registrados");
                System.out.println("4.) ver todas las especializaciones");
                System.out.println("5.) ver todas las citas");
                System.out.print("Elige una opcion = ");
                menu = sc.nextLine();
                System.out.println("");
                switch (menu) {
                    case "1":
                        int id = 0;
                        System.out.print("Ingrese el nombre del medico = ");
                        String nombre = sc.nextLine();
                        String horario1 ;
                        do {
                            System.out.println("Ingrese el horario del medico = ");
                            System.out.print("mañana = ");
                            horario1 = sc.nextLine();
                            if (!horario1.contains("/")) {
                                System.out.println("Error: la fecha debe contener '/'. Inténtelo de nuevo.");
                            }
                        } while (!horario1.contains("/"));
                        String horario2 ;
                        do {
                            System.out.println("Ingrese el horario del medico = ");
                            System.out.print("Tarde = ");
                            horario2 = sc.nextLine();
                            if (!horario2.contains("/")) {
                                System.out.println("Error: la fecha debe contener '/'. Inténtelo de nuevo.");
                            }
                        } while (!horario1.contains("/"));

                        System.out.println("");
                        do {
                            System.out.println("Todas las especialidades");
                            System.out.println("");
                            especialidadUseCase.listespecialidad();
                            System.out.println("");
                            System.out.print("encontraste tu especialidad dar 1\nNo encuentras tu especialidad ?, registra una si(2) no(3) = ");
                            String elegir = sc.nextLine();
                            switch (elegir) {
                            case "1":
                                try {
                                    System.out.println("");
                                    especialidadUseCase.listespecialidad();
                                    System.out.println("");
                                    System.out.print("ingresa el id de tu especialidad = ");
                                    int id_especialidad = sc.nextInt();
                                    sc.nextLine();
                                    medicoUseCase.registerPaciente(id, nombre, id_especialidad, horario1, horario2);
                                    System.out.println("medico registrado con exito");
                                    main(args);
                                } catch (Exception e) {
                                    System.out.println("Error elegistes una especialidad que no esta existe");
                                }
             
                                break;
                            case "2":
                                    System.out.print("Ingrese el nombre de la especialidad = ");
                                    String especialidad  = sc.nextLine();
                                    especialidadUseCase.registerespecialidad(id, especialidad);
                                break;
                            case "3":
                                    System.out.println("volviendo al principio");
                                    main(args);
                                break;
                            default:
                                    System.out.println("error");
                                break;
                        }
                        } while(!menu.equals("3"));
                        break;
                    case "2":
                            System.out.println("todos lo medicos");
                            medicoUseCase.listCita();
                        break;
                    case "3":
                            System.out.println("todos los pacientes ");
                            pacienteUseCase.listCita();
                        break;
                    case "4":
                            System.out.println("Todos las especializaciones");
                            especialidadUseCase.listespecialidad();
                    case "5":
                            System.out.println("todas las citas que hay");
                            citaUseCase.listCita();
                        break;
                    default:
                        System.out.println("Error dato elegido no existe");
                        break;
                }
            } while(!menu.equals("3"));
        } catch (Exception e) {
            System.out.println("error faltal");
        }
    }
}
