package todoList;


public class Authenticator {
    private UserRepository userRepository = new UserRepository();

    // Cadastrar novo usuário
    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // Usuário já existe
        }
        int newId = userRepository.getAllUsers().size() + 1;
        User newUser = new User(newId, username, password);
        userRepository.addUser(newUser);
        return true;
    }

    // Fazer login
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Credenciais inválidas
    }
}