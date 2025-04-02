package evaluacionjava.domain.Repository;

import java.util.List;

import evaluacionjava.domain.Entytis.User;

public interface UserRepository {
    void save(User user);
    User searchById(int id_user);
    List<User> listAll();
    void update(User user);
    void delete(int id_user);
    User findByUsernameOrEmail(String userInput);
}
