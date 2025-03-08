document.getElementById("taskForm").addEventListener("submit", function(event) {
    event.preventDefault();
    addTask();
});

function addTask() {
    let name = document.getElementById("taskName").value.trim();
    let dateTime = document.getElementById("taskDateTime").value;
    let priority = document.getElementById("taskPriority").value;
    let status = document.getElementById("taskStatus").value;
    let category = document.getElementById("taskCategory").value.trim();
    let description = document.getElementById("taskDescription").value.trim();
    
    if (!name || !dateTime) {
        alert("O nome da tarefa e a data/hora de conclusão são obrigatórios!");
        return;
    }

    if (new Date(dateTime) <= new Date()) {
        alert("A data de conclusão deve ser posterior à data atual.");
        return;
    }

    let task = { name, dateTime, priority, status, category, description };
    saveTask(task);
}

function saveTask(task) {
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks.push(task);
    localStorage.setItem("tasks", JSON.stringify(tasks));
    renderTasks();
}

function renderTasks() {
    let taskContainer = document.getElementById("taskContainer");
    taskContainer.innerHTML = "";

    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks.forEach((task, index) => {
        let div = document.createElement("div");
        div.classList.add("task-card");

        div.innerHTML = `
            <strong>${task.name}</strong>
            <p>🕒 ${task.dateTime}</p>
            <p>🔥 Prioridade: <select class = "selectStyle" onchange="updatePriority(${index}, this.value)">
                <option value="1" ${task.priority === "1" ? "selected" : ""}>UM</option>
                <option value="2" ${task.priority === "2" ? "selected" : ""}>DOIS</option>
                <option value="3" ${task.priority === "3" ? "selected" : ""}>TRES</option>
                <option value="4" ${task.priority === "4" ? "selected" : ""}>QUARTO</option>
                <option value="5" ${task.priority === "5" ? "selected" : ""}>QUINTA</option>
            </select></p>
            <p>📌 Status: <select class = "selectStyle" onchange="updateStatus(${index}, this.value)">
                <option value="TODO" ${task.status === "TODO" ? "selected" : ""}>TODO</option>
                <option value="DOING" ${task.status === "DOING" ? "selected" : ""}>DOING</option>
                <option value="DONE" ${task.status === "DONE" ? "selected" : ""}>DONE</option>
            </select></p>
            ${task.category ? `<p>📂 Categoria: ${task.category}</p>` : ""}
            ${task.description ? `<p>📝 ${task.description}</p>` : ""}
            <div class="actions">
                <button class="delete" onclick="deleteTask(${index})">Excluir</button>
            </div>
        `;

        taskContainer.appendChild(div);
    });
}

function updatePriority(index, newPriority) {
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks[index].priority = newPriority;
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function updateStatus(index, newStatus) {
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks[index].status = newStatus;
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function deleteTask(index) {
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks.splice(index, 1);
    localStorage.setItem("tasks", JSON.stringify(tasks));
    renderTasks();
}

document.addEventListener("DOMContentLoaded", renderTasks);
