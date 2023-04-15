package hi.verkefni.vinnsla.framkv;

import java.time.LocalDate;

public class DataModel {
    private TaskList taskList;

    public DataModel() {
        this.taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void deleteTask(Task task) {
        taskList.removeTask(task);
    }

    public void snoozeTask(Task task) {
        task.addDeadline();
        taskList.updateTask(task); // Add this line to update the task in the TaskList
    }

    public void editTask(Task task, String title, String description, String project, LocalDate deadline) {
        task.setTitle(title);
        task.setProject(project);
//        task.setDeadline(deadline);
    }

}

