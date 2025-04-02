package evaluacionjava.infrestructure.persistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import evaluacionjava.domain.Entytis.Medico;
import evaluacionjava.domain.Repository.MedicoRepository;
import evaluacionjava.infrestructure.database.ConnectionDb;

public class MedicoRepositoryImpl implements MedicoRepository{
    private final ConnectionDb connection;
    public MedicoRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }
    @Override
    public void save(Medico medico) {
           String sql = "INSERT INTO médico (id, nombre,horario_inicio,horario_fin,Especialidad_id) VALUES (?, ?,?,?,?)";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, medico.getId());
            stmt.setString(2, medico.getNombre());
            stmt.setString(3, medico.getHorario_inicio());
            stmt.setString(4, medico.getHorario_final());
            stmt.setInt(5, medico.getEspecialidad_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
    }

    @Override
    public Medico searchById(int id) {
        String sql = "SELECT * FROM médico WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);  
            try (ResultSet rs = stmt.executeQuery()) { 
                if (rs.next()) {
                    System.out.println("Id del medico = " + rs.getInt("id"));
                    System.out.println("Nombre del medioco = " + rs.getString("nombre")); 
                    System.out.println("Inicio del horario del medico = " + rs.getString("horario_inicio"));
                    System.out.println("Final del horario del medico = " + rs.getString("horario_fin")); 
                    System.out.println("Id de la especialidad = " + rs.getInt("Especialidad_id"));
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
    public List<Medico> listAll() {
        List<Medico> medico = new ArrayList<>();
        String sql = "SELECT * FROM médico";
        try (Connection conexion = connection.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("");
                System.out.println("Id del medico = " + rs.getInt("id"));
                System.out.println("Nombre del medioco = " + rs.getString("nombre")); 
                System.out.println("Inicio del horario del medico = " + rs.getString("horario_inicio"));
                System.out.println("Final del horario del medico = " + rs.getString("horario_fin")); 
                System.out.println("Id de la especialidad = " + rs.getInt("Especialidad_id"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error: por favor intentelo de nuevo");
        }
        return medico;
    }

    @Override
    public void update(Medico medico) {
        String sql = "UPDATE médico SET nombre = ?,horario_inicio = ?,horario_fin = ?, Especialidad_id = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, medico.getNombre()); 
            stmt.setString(2, medico.getHorario_inicio()); 
            stmt.setString(1, medico.getHorario_final()); 
            stmt.setInt(2, medico.getEspecialidad_id());             
            stmt.setInt(2, medico.getId()); 
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("medico actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró un medico con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "SELECT * FROM médico WHERE id = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sqlDelete = "DELETE FROM médico WHERE id = ?";
                    try (PreparedStatement stmtDelete = conexion.prepareStatement(sqlDelete)) {
                        stmtDelete.setInt(1, id);
                        int filasEliminadas = stmtDelete.executeUpdate();
                        if (filasEliminadas > 0) {
                            System.out.println("médico eliminado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo eliminar el médico.");
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
