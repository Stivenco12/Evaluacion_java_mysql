package evaluacionjava.infrestructure.persistense;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import evaluacionjava.domain.Entytis.Cita;
import evaluacionjava.domain.Repository.CitaRepository;
import evaluacionjava.infrestructure.database.ConnectionDb;

public class CitaRepositoryImpl implements CitaRepository {
    private final ConnectionDb connection;
    public CitaRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }
    @Override
    public void save(Cita cita) {
        String sql = "INSERT INTO Cita (cita_id, paciente_id,medico_id,fecha_hora,estado ) VALUES (?, ?,?,?,?)";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita.getCita_id());
            stmt.setInt(2, cita.getPaciente_id());
            stmt.setInt(3, cita.getMedico_id());
            stmt.setString(4, cita.getFecha_hora());
            stmt.setString(5, cita.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
    }
    @Override
    public Cita searchById(int cita_id) {
        String sql = "SELECT * FROM Cita WHERE cita_id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita_id);  
            try (ResultSet rs = stmt.executeQuery()) { 
                if (rs.next()) {
                    System.out.println("");
                    System.out.println("Id de la cita = " + rs.getInt("product_id"));
                    System.out.println("id del páciente registrado en la cita = " + rs.getString("paciente_id")); 
                    System.out.println("id del medico encargado de la cita = " + rs.getDouble("medico_id"));   
                    System.out.println("Fecha y hora de la cita " + rs.getDate("fecha_hora"));
                    System.out.println("Estado de la cita " + rs.getDate("estado"));
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
    public List<Cita> listAll() {
        List<Cita> cita = new ArrayList<>();
        String sql = "SELECT * FROM Cita";
        try (Connection conexion = connection.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("");
                System.out.println("Id de la cita = " + rs.getInt("product_id"));
                System.out.println("id del páciente registrado en la cita = " + rs.getString("paciente_id")); 
                System.out.println("id del medico encargado de la cita = " + rs.getDouble("medico_id"));   
                System.out.println("Fecha y hora de la cita " + rs.getDate("fecha_hora"));
                System.out.println("Estado de la cita " + rs.getDate("estado"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
        return cita;
    }
    @Override
    public void update(Cita cita) {
        String sql = "UPDATE cita SET paciente_id = ?,medico_id = ?, fecha_hora = ?,estado = ? WHERE cita_id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita.getPaciente_id()); 
            stmt.setInt(2, cita.getMedico_id()); 
            stmt.setString(3, cita.getFecha_hora()); 
            stmt.setString(4, cita.getEstado()); 
            stmt.setInt(5, cita.getCita_id());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró una cita con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }
    @Override
    public void delete(int cita_id) {
        String sql = "SELECT * FROM Cita WHERE cita_id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sqlDelete = "DELETE FROM Cita WHERE cita_id = ?";
                    try (PreparedStatement stmtDelete = conexion.prepareStatement(sqlDelete)) {
                        stmtDelete.setInt(1, cita_id);
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

    
    @Override
    public void cancelar(Cita cita) {
        String sql = "UPDATE cita SET estado = ? WHERE cita_id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cita.getEstado()); 
            stmt.setInt(2, cita.getCita_id());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró una cita con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }
}
