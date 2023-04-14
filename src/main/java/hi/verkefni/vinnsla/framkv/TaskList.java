package hi.verkefni.vinnsla.framkv;

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

    public void addRandomTasks() {
        String[] names = {"Buy groceries", "Clean the house", "Finish project", "Go for a run", "Call mom"};
        String[] descriptions = {"asdfa sdfasdf", "asdfasdf the hasdfasfouse", "Finishasfdasdf project", "Go for asdfaa run", "Call mom"};
        String[] projects = {"Home", "Work", "Personal", "School", "Hobby"};
        int[] priorities = {1, 2, 3, 4, 5};
        LocalDate[] deadlines = {LocalDate.now().plusDays(0), LocalDate.now().plusDays(3), LocalDate.now().plusDays(5)};

        for (int i = 0; i < 10; i++) {
            String title = names[(int) (Math.random() * names.length)];
            String description = descriptions[(int) (Math.random() * names.length)];
            String project = projects[(int) (Math.random() * projects.length)];
            int priority = priorities[(int) (Math.random() * priorities.length)];
            LocalDate deadline = deadlines[(int) (Math.random() * deadlines.length)];

            Task task = new Task(title, description, project, deadline, priority);
            taskList.add(task);
        }
    }

}
