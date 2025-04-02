package evaluacionjava.infrestructure.persistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import evaluacionjava.domain.Entytis.Paciente;
import evaluacionjava.domain.Repository.PacienteRepository;
import evaluacionjava.infrestructure.database.ConnectionDb;

public class PacienteRepositoryImpl implements PacienteRepository{
    private final ConnectionDb connection;
    public PacienteRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }
    @Override
    public void save(Paciente paciente) {
        String sql = "INSERT INTO paciente (id, nombre,apellido,fecha_nacimiento,dirección ,teléfono,email) VALUES (?, ?,?,?,?,?,?)";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getNombre());
            stmt.setString(3, paciente.getApellido());
            stmt.setString(4, paciente.getFecha_nacimiento());
            stmt.setString(5, paciente.getDireccion());
            stmt.setString(6, paciente.getTelefono());
            stmt.setString(7, paciente.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
    }

    @Override
    public Paciente searchById(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);  
            try (ResultSet rs = stmt.executeQuery()) { 
                if (rs.next()) {
                    System.out.println("Id del paciente = " + rs.getInt("id"));
                    System.out.println("Nombre del paciente = " + rs.getString("nombre")); 
                    System.out.println("Apellido del paciente = " + rs.getDouble("apellido"));   
                    System.out.println("Fecha de nacimiento del paciente = " + rs.getDate("fecha_nacimiento"));
                    System.out.println("Direccion de vivienda del paciente =  " + rs.getDate("dirección"));
                    System.out.println("Numero de telefono del paciente =  " + rs.getDate("teléfono"));
                    System.out.println("Correo del paciente =  " + rs.getDate("email"));
                    System.out.println("");
                } else {
                    System.out.println("Error: ese ID no existe.");
                }
            }
        } catch (SQLException e) {
        
            System.out.println("Error en la consulta SQL.");
        }
        return null;
    }

    @Override
    public List<Paciente> listAll() {
        List<Paciente> cita = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Connection conexion = connection.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("");
                System.out.println("Id del paciente = " + rs.getInt("id"));
                System.out.println("Nombre del paciente = " + rs.getString("nombre")); 
                System.out.println("Apellido del paciente = " + rs.getDouble("apellido"));   
                System.out.println("Fecha de nacimiento del paciente = " + rs.getDate("fecha_nacimiento"));
                System.out.println("Direccion de vivienda del paciente =  " + rs.getDate("dirección"));
                System.out.println("Numero de telefono del paciente =  " + rs.getDate("teléfono"));
                System.out.println("Correo del paciente =  " + rs.getDate("email"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
        return cita;
    }

    @Override
    public void update(Paciente paciente) {
        String sql = "UPDATE cita SET nombre = ?, apellido = ?,fecha_nacimiento = ?,dirección = ? ,teléfono = ?, email = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre()); 
            stmt.setString(2, paciente.getApellido()); 
            stmt.setString(3, paciente.getFecha_nacimiento()); 
            stmt.setString(4, paciente.getDireccion()); 
            stmt.setString(5, paciente.getTelefono()); 
            stmt.setString(6, paciente.getEmail()); 
            stmt.setInt(7, paciente.getId());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró un paciente con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "SELECT * FROM paciente WHERE cita_id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sqlDelete = "DELETE FROM paciente WHERE cita_id = ?";
                    try (PreparedStatement stmtDelete = conexion.prepareStatement(sqlDelete)) {
                        stmtDelete.setInt(1, id);
                        int filasEliminadas = stmtDelete.executeUpdate();
                        if (filasEliminadas > 0) {
                            System.out.println("Cita eliminado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo eliminar el cita.");
                        }
                    }
                } else {
                    System.out.println("Error: Ese ID no existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }  
    }
    
}
