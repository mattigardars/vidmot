package hi.verkefni.vinnsla.framkv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 A class representing a list of tasks.
 */
public class TaskList {

    private ObservableList<Task> taskList;

    /**
     Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = FXCollections.observableArrayList();
    }

    /**
     Returns the observable list of tasks.
     @return The observable list of tasks.
     */
    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    /**
     Adds a task to the task list.
     @param task The task to add to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     Removes a task from the task list.
     @param task The task to remove from the task list.
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     Updates a task in the task list.
     @param task The task to update.
     */
    public void updateTask(Task task) {
        int index = taskList.indexOf(task);
        if (index != -1) {
            taskList.set(index, task);
        }
    }

}


