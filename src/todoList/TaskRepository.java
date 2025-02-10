package todoList;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    // Adicionar tarefa
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remover tarefa por ID
    public void removeTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    // Buscar tarefa por ID
    public Task findById(int id) {
        return tasks.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    // Listar todas as tarefas de um usuário
    public List<Task> getTasksByUser(int userId) {
        return tasks.stream()
                    .filter(t -> t.getUserId() == userId)
                    .toList();
    }

    // Listar todas as tarefas
    public List<Task> getAllTasks() {
        return tasks;
    }
}
