package evaluacionjava.infrestructure.persistense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import evaluacionjava.domain.Entytis.User;
import evaluacionjava.domain.Repository.UserRepository;
import evaluacionjava.infrestructure.database.ConnectionDb;

public class UserRepositoryImpl implements UserRepository {
    private final ConnectionDb connection;
    
    public UserRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (Id_user, name,email,password,type ) VALUES (?, ?, ?, ?,?)";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, user.getId_user());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error por favor revisa que los datos esten bien");
        }
    }

    @Override
    public User searchById(int id_user) {
        String sql = "SELECT * FROM user WHERE Id_user = ?";
        User user = null;
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id_user);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("Id_user"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type")
                    );
                } else {
                    System.out.println("Error: ese ID no existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
        return user;
    }
    @Override
    public List<User> listAll() {
        List<User> user = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection conexion = connection.getConexion();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Id del producto = " + rs.getInt("Id_user"));
                System.out.println("Nombre del usuario = " + rs.getString("name")); 
                System.out.println("Email del usuario = " + rs.getString("email"));   
                System.out.println("password del usuario = " + rs.getString("password"));   
                System.out.println("tipo de usuario = " + rs.getString("type"));
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET name = ?,email = ?, password = ?,type = ? WHERE Id_user = ?";
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, user.getName()); 
            stmt.setString(2, user.getEmail()); 
            stmt.setString(3, user.getPassword()); 
            stmt.setString(4, user.getType()); 
            stmt.setInt(4, user.getId_user());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("usuario actualizado correctamente.");
            } else {
                System.out.println("Error: No se encontró un usuario con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }
    }

    @Override
    public void delete(int id_user) {
        String sql = "SELECT * FROM user WHERE Id_user = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id_user);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sqlDelete = "DELETE FROM user WHERE Id_user = ?";
                    try (PreparedStatement stmtDelete = conexion.prepareStatement(sqlDelete)) {
                        stmtDelete.setInt(1, id_user);
                        int filasEliminadas = stmtDelete.executeUpdate();
                        if (filasEliminadas > 0) {
                            System.out.println("usuario eliminado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo eliminar el usuario.");
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
    public User findByUsernameOrEmail(String userInput) {
        String sql = "SELECT * FROM user WHERE name = ? OR email = ?";
        User user = null;
        try (Connection conexion = connection.getConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, userInput);
            stmt.setString(2, userInput);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("Id_user"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type")
                    );
                } else {
                    System.out.println("Usuario no encontrado en la base de datos.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL.");
        }   
        return user;
    }


}