package todoList;

import java.util.Comparator;
import java.util.List;

public class TaskLogic {
    private TaskRepository taskRepository = new TaskRepository();

    // Adicionar tarefa com rebalanceamento por prioridade
    public void addTask(Task task) {
        taskRepository.addTask(task);
        rebalanceTasks();
    }

    // Rebalancear tarefas por prioridade
    private void rebalanceTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        tasks.sort(Comparator.comparingInt(Task::getPrioridade));
    }

    // Listar tarefas por status
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.getAllTasks().stream()
                             .filter(t -> t.getStatus() == status)
                             .toList();
    }

    // Listar tarefas por categoria
    public List<Task> getTasksByCategory(String category) {
        return taskRepository.getAllTasks().stream()
                             .filter(t -> t.getCategoria().equalsIgnoreCase(category))
                             .toList();
    }

    // Listar tarefas por prioridade
    public List<Task> getTasksByPriority(int priority) {
        return taskRepository.getAllTasks().stream()
                             .filter(t -> t.getPrioridade() == priority)
                             .toList();
    }
    
    public List<Task> getTasksByUser(int id){
    	return taskRepository.getAllTasks().stream()
                .filter(t -> t.getUserId() == id)
                .toList();
    }
    

    // Remover tarefa por ID
    public void removeTask(String nome) {
        taskRepository.removeTask(nome);
    }
}