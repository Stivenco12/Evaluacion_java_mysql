package evaluacionjava.ui;

import java.util.Scanner;

import evaluacionjava.aplication.CitaUseCase;
import evaluacionjava.aplication.MedicoUseCase;

import evaluacionjava.domain.Entytis.inicioDeSesion;
import evaluacionjava.domain.Repository.CitaRepository;
import evaluacionjava.domain.Repository.MedicoRepository;

import evaluacionjava.infrestructure.database.ConnectMysqlFactory;
import evaluacionjava.infrestructure.persistense.CitaRepositoryImpl;
import evaluacionjava.infrestructure.persistense.MedicoRepositoryImpl;


public class UiMenuPacientes {
    public static void main(String[] args) {
         try (Scanner sc = new Scanner(System.in)) {

            CitaRepository repositoryCita = new CitaRepositoryImpl(ConnectMysqlFactory.crearConexion());
            CitaUseCase citaUseCase = new CitaUseCase(repositoryCita);
            MedicoRepository medicoRepository = new MedicoRepositoryImpl(ConnectMysqlFactory.crearConexion());
            MedicoUseCase medicoUseCase = new MedicoUseCase(medicoRepository);
            String menu;
            do {
                System.out.println("\nbienvenido a la sesion de pacientes");
                System.out.println("1.) Registrar cita ");
                System.out.println("2.) Ver todas las citas que hay ");
                System.out.println("3.) editar una cita ");
                System.out.println("4.) Canselar una cita ");
                System.out.print("Elige una opcion = ");
                menu = sc.nextLine();
                System.out.println("");
                switch (menu) {
                    case "1":
                        try {
                            int id = 0;
                            int userId = inicioDeSesion.getCurrentUserId();
                            System.out.println("Todos los medicos");
                            medicoUseCase.listCita();
                            System.out.print("Ingresa el id del medico que deseas para tu cita = ");
                            int medico = sc.nextInt();
                            sc.nextLine();
                            String fecha;
                            do {
                                System.out.print("Ingrese la fecha y hora de la cita en formato dia/mes/año/hora ");
                                fecha = sc.nextLine();
                                if (!fecha.contains("/")) {
                                    System.out.println("Error: la fecha debe contener '/'. Inténtelo de nuevo.");
                                }
                            } while (!fecha.contains("/"));
                            String estado = "activo";
                            citaUseCase.registerCita(id, userId, medico, fecha, estado);
                            System.out.println("Se registro correctamente");
                        } catch (Exception e) {
                            System.out.println("Error elegistes un medico que no existe");
                        }
                        break;
                    case "2":
                        System.out.println("todas las citas");
                        citaUseCase.listCita();
                        break;
                    case "3":
                        try {
                            int nuevoid = 0;
                            int userId = inicioDeSesion.getCurrentUserId();
                            System.out.println("Todos los medicos");
                            medicoUseCase.listCita();
                            System.out.print("Ingresa el id del medico que deseas para tu cita");
                            int nuevomedico = sc.nextInt();
                            String nuevafecha;
                            do {
                                System.out.print("Ingrese la fecha y hora de la cita en formato dia/mes/año/hora ");
                                nuevafecha = sc.nextLine();
                                if (!nuevafecha.contains("/")) {
                                    System.out.println("Error: la fecha debe contener '/'. Inténtelo de nuevo.");
                                }
                            } while (!nuevafecha.contains("/"));
                            String estado = "activo";
                            citaUseCase.updateCita(nuevoid, userId, nuevomedico, menu, estado);
                            System.out.println("Se registro correctamente");
                        } catch (Exception e) {
                            System.out.println("Error elegistes un medico que no existe");
                        }
                        break;
                    case "4":
                        try {
                            System.out.print("Ingrese el id de la cita a canselar");
                            int canselar = sc.nextInt();
                            System.out.println("Canselando cita");
                            String estado = "Canselada";
                            citaUseCase.deleteCita(canselar,estado);
                        } catch (Exception e) {
                            System.out.println("Error faltal");
                        }
                        break;
                    default:
                        System.out.println("error en el tipo de dato selecionado");
                        break;
                }
            } while(!menu.equals("4"));
        } catch (Exception e) {
            System.out.println("error faltal");
        }
    }
}
