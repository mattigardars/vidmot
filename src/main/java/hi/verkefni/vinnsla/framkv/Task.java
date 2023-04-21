package hi.verkefni.vinnsla.framkv;

import hi.verkefni.vidmot.framkv.EditTaskController;

import java.time.LocalDate;

/**

 The Task class represents a task with a title, project, deadline, and priority.
 */
public class Task {
    private String title; // The title of the task
    private String project; // The project the task belongs to
    private LocalDate deadline; // The deadline for the task
    private int priority; // The priority of the task

    /**

     Creates a new task with the given title, project, deadline, and priority.
     @param title the title of the task
     @param project the project the task belongs to
     @param deadline the deadline for the task
     @param priority the priority of the task
     */
    public Task(String title, String project, LocalDate deadline, int priority) {
        this.title = title;
        this.project = project;
        this.deadline = deadline;
        this.priority = priority;
    }
    /**

     Returns a string representation of the task, including its title, project, deadline, and priority.
     @return a string representation of the task
     */
    @Override
    public String toString() {
        return "Title: " + title + ", Project: " + project + ", Deadline: " + deadline + ", Priority: " + priority;
    }
    /**

     Returns the title of the task.
     @return the title of the task
     */
    public String getTitle() {
        return title;
    }
    /**

     Returns the project the task belongs to.
     @return the project the task belongs to
     */
    public String getProject() {
        return project;
    }
    /**

     Returns the deadline for the task.
     @return the deadline for the task
     */
    public LocalDate getDeadline() {
        return deadline;
    }
    /**

     Returns the priority of the task.
     @return the priority of the task
     */
    public int getPriority() {
        return priority;
    }
    /**

     Adds two days to the deadline of the task.
     */
    public void addDeadline() {
        this.deadline = deadline.plusDays(2);
    }
    /**

     Sets the title of the task.
     @param title the new title of the task
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**

     Sets the project the task belongs to.
     @param project the new project the task belongs to
     */
    public void setProject(String project) {
        this.project = project;
    }
    /**

     Sets the deadline for the task.
     @param deadline the new deadline for the task
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

