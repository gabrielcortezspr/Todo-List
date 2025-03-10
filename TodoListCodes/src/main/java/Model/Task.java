package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

	private String nome;
	private String descricao;
	private LocalDate dataTermino;
	private int prioridade; // 1 a 5
	private String categoria;
	private Status status; // Enum: TODO, DOING, DONE
	private int userId; // ID do usuário que criou a tarefa
	private LocalDateTime alarme; // Data e hora do alarme

	public Task(String nome, String descricao, LocalDate dataTermino, int prioridade, String categoria, Status status, int userId, LocalDateTime alarme) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataTermino = dataTermino;
		this.prioridade = prioridade;
		this.categoria = categoria;
		this.status = status;
		this.userId = userId;
		this.alarme = alarme;
	}

	public LocalDateTime getAlarme() {
		return alarme;
	}

	public void setAlarme(LocalDateTime alarme) {
		this.alarme = alarme;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean shouldTriggerAlarm() {
		if (alarme != null) {
			LocalDateTime now = LocalDateTime.now();
			return !now.isBefore(alarme) && now.isEqual(alarme);
		}
		return false;
	}

}