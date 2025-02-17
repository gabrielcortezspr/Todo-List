package Controller;

import Model.Status;
import Model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository {
    private final List<Task> listTasks = new ArrayList<>();

    // Adicionar tarefa com rebalanceamento por prioridade
    public void addTask(Task task) {
        listTasks.add(task);
        rebalanceTasks();
    }

    // Remover tarefa por Nome
    public boolean removeTask(String nome) {
        listTasks.removeIf(t -> t.getNome().equalsIgnoreCase(nome));
        return true;
    }

    // Buscar tarefa por Nome
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
                .collect(Collectors.toList());
    }

    // Listar tarefas por status
    public List<Task> getTasksByStatus(Status status) {
        return listTasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    // Listar tarefas por categoria
    public List<Task> getTasksByCategory(String category) {
        return listTasks.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Listar tarefas por prioridade
    public List<Task> getTasksByPriority(int priority) {
        return listTasks.stream()
                .filter(t -> t.getPrioridade() == priority)
                .collect(Collectors.toList());
    }

    // Listar todas as tarefas
    public List<Task> getAllTasks() {
        return new ArrayList<>(listTasks); // Evita exposição direta da lista interna
    }

    // Rebalancear tarefas por prioridade
    private void rebalanceTasks() {
        listTasks.sort(Comparator.comparingInt(Task::getPrioridade));
    }
}
