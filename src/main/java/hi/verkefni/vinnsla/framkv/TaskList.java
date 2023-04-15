package hi.verkefni.vinnsla.framkv;

import hi.verkefni.vidmot.framkv.TaskListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;


public class TaskList {

    private ObservableList<Task> taskList;

    public TaskList() {
        this.taskList = FXCollections.observableArrayList();
    }

    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    // In TaskList class
    public void updateTask(Task task) {
        int index = taskList.indexOf(task);
        if (index != -1) {
            taskList.set(index, task);
        }
    }

}


