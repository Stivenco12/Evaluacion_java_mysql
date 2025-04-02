package evaluacionjava.infrestructure.persistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import evaluacionjava.domain.Entytis.Especialidad;
import evaluacionjava.domain.Repository.EspecialidadRepository;
import evaluacionjava.infrestructure.database.ConnectionDb;

public class EspecialidadRepositoryImpl implements EspecialidadRepository {
    private final ConnectionDb connection;
    public EspecialidadRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }
    @Override
    public void save(Especialidad especialidad) {
        String sql = "INSERT INTO especialidad (id, nombre) VALUES (?, ?)";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, especialidad.getId());
            stmt.setString(2, especialidad.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
    }

    @Override
    public Especialidad searchById(int id) {
        String sql = "SELECT * FROM especialidad WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);  
            try (ResultSet rs = stmt.executeQuery()) { 
                if (rs.next()) {
                    System.out.println("Id de la especialidad = " + rs.getInt("id"));
                    System.out.println("Nombre de la especialidad = " + rs.getString("nombre")); 
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
    public List<Especialidad> listAll() {
        List<Especialidad> especialidad = new ArrayList<>();
        String sql = "SELECT * FROM especialidad";
        try (Connection conexion = connection.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Id de la especialidad = " + rs.getInt("id"));
                System.out.println("Nombre de la especialidad = " + rs.getString("nombre")); 
            }
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
        return especialidad;
    }

    @Override
    public void update(Especialidad especialidad) {
        String sql = "UPDATE especialidad SET nombre = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, especialidad.getNombre()); 
            stmt.setInt(2, especialidad.getId()); 
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró una especialidad con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "SELECT * FROM especialidad WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sqlDelete = "DELETE FROM especialidad WHERE id = ?";
                    try (PreparedStatement stmtDelete = conexion.prepareStatement(sqlDelete)) {
                        stmtDelete.setInt(1, id);
                        int filasEliminadas = stmtDelete.executeUpdate();
                        if (filasEliminadas > 0) {
                            System.out.println("especialidad eliminado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo eliminar la especialidad.");
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
