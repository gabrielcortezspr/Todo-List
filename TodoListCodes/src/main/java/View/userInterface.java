package View;

import Controller.Authenticator;
import Controller.TaskRepository;
import Model.Status;
import Model.Task;
import Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.*;
//comentando so de raiva

public class userInterface {
    private ScheduledExecutorService scheduler;
    private Authenticator authenticator = new Authenticator();
    private TaskRepository tasklogic = new TaskRepository();
    private User loggedUser;
    private Scanner scanner = new Scanner(System.in);

    private void startAlarmScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> checkAlarms(), 0, 1, TimeUnit.SECONDS);
    }

    private void checkAlarms() {
        for (Task task : tasklogic.getAllTasks()) {
            if (task.shouldTriggerAlarm()) {
                triggerAlarm(task);
            }
        }
    }

    private void triggerAlarm(Task task) {
        System.out.println("ALERTA! O alarme da tarefa '" + task.getNome() + "' foi disparado!");
        // Aqui você pode adicionar código para tocar um som ou exibir uma notificação
    }

    public void start() {
        System.out.println("Bem-vindo ao TODO List!");
        startAlarmScheduler();  // Inicia o agendador de alarmes
        showLoginMenu();
    }

    // Menu de login/cadastro
    private void showLoginMenu() {
        System.out.println("1 - Login");
        System.out.println("2 - Cadastrar");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            default -> {
                System.out.println("Opção inválida!");
                showLoginMenu();
            }
        }
    }

    // Fazer login
    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        loggedUser = authenticator.login(username, password);
        if (loggedUser != null) {
            showMainMenu();
        } else {
            System.out.println("Credenciais inválidas!");
            showLoginMenu();
        }
    }

    // Cadastrar novo usuário
    private void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (authenticator.register(username, password)) {
            System.out.println("Cadastro realizado com sucesso!");
            showLoginMenu();
        } else {
            System.out.println("Username já existe!");
            showLoginMenu();
        }
    }

    // Menu principal
    private void showMainMenu() {
        System.out.println("1 - Criar Tarefa");
        System.out.println("2 - Listar Tarefas");
        System.out.println("3 - Deletar Tarefa");
        System.out.println("4 - Sair");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (choice) {
            case 1 -> createTask();
            case 2 -> listTasks();
            case 3 -> deleteTask();
            case 4 -> System.exit(0);
            default -> {
                System.out.println("Opção inválida!");
                showMainMenu();
            }
        }
    }

    private void createTask() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data de término (yyyy-MM-dd): ");
        LocalDate dataTermino = LocalDate.parse(scanner.nextLine());
        System.out.print("Prioridade (1-5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Status (TODO, DOING, DONE): ");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        // Perguntar sobre o alarme
        System.out.print("Deseja configurar um alarme para esta tarefa? (s/n): ");
        String alarmeChoice = scanner.nextLine();
        LocalDateTime alarme = null;
        if (alarmeChoice.equalsIgnoreCase("s")) {
            System.out.print("Data e hora do alarme (yyyy-MM-ddTHH:mm): ");
            alarme = LocalDateTime.parse(scanner.nextLine());
        }

        Task newTask = new Task(nome, descricao, dataTermino, prioridade, categoria, status, loggedUser.getId(), alarme);
        tasklogic.addTask(newTask);
        System.out.println("Tarefa criada com sucesso!");
        showMainMenu();
    }

    // Deletar tarefa
    private void deleteTask() {
        System.out.print("Nome da tarefa a deletar: ");
        String nome = scanner.nextLine();
        boolean removed = tasklogic.removeTask(nome);
        if (removed) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada!");
        }
        showMainMenu();
    }

    private void listTasks() {
        System.out.println("1 - Listar por Status");
        System.out.println("2 - Listar por Categoria");
        System.out.println("3 - Listar por Prioridade");
        System.out.println("4 - Listar por User Criador");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (choice) {
            case 1 -> {
                System.out.print("Status (TODO, DOING, DONE): ");
                Status status = Status.valueOf(scanner.nextLine().toUpperCase());
                tasklogic.getTasksByStatus(status).forEach(this::printTaskDetails);
            }
            case 2 -> {
                System.out.print("Categoria: ");
                String categoria = scanner.nextLine();
                tasklogic.getTasksByCategory(categoria).forEach(this::printTaskDetails);
            }
            case 3 -> {
                int userId = loggedUser.getId(); // Obtém o ID do usuário logado
                tasklogic.getTasksByUser(userId)
                        .stream()
                        .sorted(Comparator.comparingInt(Task::getPrioridade))
                        .forEach(this::printTaskDetails);
            }
            case 4 -> {
                System.out.print("ID do User: ");
                int id = scanner.nextInt();
                tasklogic.getTasksByUser(id).forEach(this::printTaskDetails);
            }
            default -> {
                System.out.println("Opção inválida!");
                listTasks();
            }
        }
        showMainMenu();
    }

    private void printTaskDetails(Task task) {
        System.out.println("Nome: " + task.getNome());
        System.out.println("Prioridade: " + task.getPrioridade());
        System.out.println("Status: " + task.getStatus());
        System.out.println("Data de término: " + task.getDataTermino());
        if (task.getAlarme() != null) {
            System.out.println("Alarme: " + task.getAlarme());
        } else {
            System.out.println("Sem alarme configurado.");
        }
    }
}
