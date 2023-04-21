package hi.verkefni.vinnsla.framkv;


/**
 A class representing the data model of the application, which manages the task list and selected task.
 */
public class DataModel {
    private TaskList taskList;

    private Task selectedTask;

    /**
     Constructs a new DataModel with an empty TaskList.
     */
    public DataModel() {
        this.taskList = new TaskList();
    }

    /**
     Returns the TaskList managed by this DataModel.
     @return the TaskList managed by this DataModel
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     Deletes the specified task from the TaskList.
     @param task the task to delete
     */
    public void deleteTask(Task task) {
        taskList.removeTask(task);
    }

    /**
     Snoozes the deadline of the specified task and updates it in the TaskList.
     @param task the task to snooze
     */
    public void snoozeTask(Task task) {
        task.addDeadline();
        taskList.updateTask(task); // Add this line to update the task in the TaskList
    }

    /**
     Returns the currently selected task.
     @return the currently selected task
     */
    public Task getSelectedTask() {
        return selectedTask;
    }

    /**
     Sets the currently selected task to the specified task.
     @param selectedTask the task to select
     */
    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    /**
     Updates the specified task with the specified updated task in the TaskList.
     @param taskToUpdate the task to update
     @param updatedTask the updated task
     */
    public void updateTask(Task taskToUpdate, Task updatedTask) {
        int index = taskList.getTaskList().indexOf(taskToUpdate);
        if (index != -1) {
            taskList.getTaskList().set(index, updatedTask);
            // Notify observers of the change
        }
    }

    /**
     Returns whether the currently selected task is finished.
     @return true if the currently selected task is finished, false otherwise
     */
    public boolean getFinished () {
        return selectedTask.isFinished();
    }

}
