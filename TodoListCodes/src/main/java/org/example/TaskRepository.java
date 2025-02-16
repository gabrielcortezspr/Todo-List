package todoList;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> listTasks = new ArrayList<>();

    // Adicionar tarefa
    public void addTask(Task task) {
    	listTasks.add(task);
    }

    // Remover tarefa por Nome
    public void removeTask(String nome) {
    	listTasks.removeIf(t -> t.getNome().equalsIgnoreCase(nome));

    }

    // Buscar tarefa por name
    public Task findByName(String nome) {
        return listTasks.stream()
                    .filter(t -> t.getNome().equalsIgnoreCase(nome))
                    .findFirst()
                    .orElse(null);
    }

    // Listar todas as tarefas de um usuário
    public List<Task> getTasksByUser(int userId) {
        return listTasks.stream()
                    .filter(t -> t.getUserId() == userId)
                    .toList();
    }

    // Listar todas as tarefas
    public List<Task> getAllTasks() {
        return listTasks;
    }
}
