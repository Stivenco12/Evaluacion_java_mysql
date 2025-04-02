package evaluacionjava.domain.Entytis;

public class User {
    private int Id_user;
    private String name;
    private String email;
    private String password;
    private String type;
    public User(int id_user, String name, String email, String password, String type) {
        this.Id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public int getId_user() {
        return Id_user;
    }
    public void setId_user(int id_user) {
        Id_user = id_user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
