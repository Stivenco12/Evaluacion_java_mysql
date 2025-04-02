package evaluacionjava.aplication;

import java.util.List;

import evaluacionjava.domain.Entytis.User;
import evaluacionjava.domain.Repository.UserRepository;

public class userUseCase {
    private final UserRepository repository;

     public userUseCase(UserRepository repository){
        this.repository = repository;
    }

    public void registerUser(int id_user, String name, String email, String password,String type){
        User user = new User(id_user,name,email,password,type);
        repository.save(user);
    }
    public User getUser(int id_user) {
        return repository.searchById(id_user);
    }

    public List<User> listUser() {
        return repository.listAll();
    }

    public void updateUser(int id_user, String name, String email, String password,String type){
        User user = new User(id_user,name,email,password,type);
        repository.save(user);
    }

    public void deleteUser(int id_user) {
        repository.delete(id_user);
    }

    public User searchByUsernameOrEmail(String userInput) {
        return repository.findByUsernameOrEmail(userInput);
    }
}
