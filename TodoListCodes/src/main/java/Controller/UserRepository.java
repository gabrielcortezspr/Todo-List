package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    // Adicionar usuário
    public void addUser(User user) {
        users.add(user);
    }

    // Buscar usuário por username
    public User findByUsername(String username) {
        return users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
    }

    // Listar todos os usuários
    public List<User> getAllUsers() {
        return users;
    }
}