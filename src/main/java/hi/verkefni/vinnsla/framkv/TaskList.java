package hi.verkefni.vinnsla.framkv;

import hi.verkefni.vidmot.framkv.TaskListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A list of tasks that can be observed for changes.
 */
public class TaskList {

    private ObservableList<Task> taskList;

    /**
     * Constructs a new empty task list.
     */
    public TaskList() {
        this.taskList = FXCollections.observableArrayList();
    }

    /**
     * Returns the observable list of tasks in the task list.
     * @return the observable list of tasks
     */
    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the task list.
     * @param task the task to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Updates a task in the task list with the new task data.
     * @param task the task to update
     */
    // In TaskList class
    public void updateTask(Task task) {
        int index = taskList.indexOf(task);
        if (index != -1) {
            taskList.set(index, task);
        }
    }

}


