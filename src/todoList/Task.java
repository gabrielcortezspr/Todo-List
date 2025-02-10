package todoList;

import java.time.LocalDate;

public class Task {
	
	private String name;
	private String description;
	private LocalDate finalDate;
	private int priority;
	private Status status;
	
	public Task(String name, String description, LocalDate finalDate, int priority, Status status) {
		super();
		this.name = name;
		this.description = description;
		this.finalDate = finalDate;
		this.priority = priority;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
