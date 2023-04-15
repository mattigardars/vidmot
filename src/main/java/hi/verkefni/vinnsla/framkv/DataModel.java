package hi.verkefni.vinnsla.framkv;

import java.time.LocalDate;

public class DataModel {
    private TaskList taskList;

    private Task selectedTask;

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

    public void editTask(Task task) {
        int index = taskList.getTaskList().indexOf(task);
        if (index >= 0) {
            taskList.getTaskList().set(index, task);
        }
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void updateTask(Task taskToUpdate, Task updatedTask) {
        int index = taskList.getTaskList().indexOf(taskToUpdate);
        if (index != -1) {
            taskList.getTaskList().set(index, updatedTask);
            // Notify observers of the change
        }
    }

}

