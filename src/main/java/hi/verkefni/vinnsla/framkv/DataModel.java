package hi.verkefni.vinnsla.framkv;

/**
 The DataModel class represents the data model of the application.
 It stores the TaskList and provides methods to manipulate it.
 */
public class DataModel {
    private TaskList taskList;

    /**
     Creates a new DataModel object with an empty TaskList.
     */
    public DataModel() {
        this.taskList = new TaskList();
    }

    /**
     Returns the TaskList object.
     @return the TaskList object
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     Removes the given task from the TaskList.
     @param task the task to remove
     */
    public void deleteTask(Task task) {
        taskList.removeTask(task);
    }

    /**
     Increases the deadline of the given task by a default amount of time.
     The task is also updated in the TaskList.
     @param task the task to snooze
     */
    public void snoozeTask(Task task) {
        task.addDeadline();
        taskList.updateTask(task); // Add this line to update the task in the TaskList
    }

    /**
     Updates the task in the TaskList.
     @param task the task to update
     */
    public void editTask(Task task) {
        int index = taskList.getTaskList().indexOf(task);
        if (index >= 0) {
            taskList.getTaskList().set(index, task);
        }
    }

}

