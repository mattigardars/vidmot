package hi.verkefni.vinnsla.framkv;


import java.time.LocalDate;
import java.util.Comparator;

/**
 The Task class represents a task with a title, project, deadline, priority, and finished status.
 */
public class Task {
    private String title;
    private String project;
    private LocalDate deadline;
    private int priority;

    private boolean finished;

    /**
     Creates a new Task object with the specified parameters.
     @param title the title of the task
     @param project the project that the task belongs to
     @param deadline the deadline for the task
     @param priority the priority of the task
     @param finished the finished status of the task
     */
    public Task(String title, String project, LocalDate deadline, int priority, boolean finished) {
        this.title = title;
        this.project = project;
        this.deadline = deadline;
        this.priority = priority;
        this.finished = finished;
    }

    /**
     Returns true if the task is finished, false otherwise.
     @return true if the task is finished, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }
    /**
     Compares tasks based on their priorities.
     */
    public static final Comparator<Task> PRIORITY_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task task1, Task task2) {
            return Integer.compare(task1.getPriority(), task2.getPriority());
        }
    };

    /**
     Returns a string representation of the task.
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
     Returns the project that the task belongs to.
     @return the project that the task belongs to
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
     Sets the finished status of the task to the specified value.
     @param selected the new finished status of the task
     */
    public void setFinished(boolean selected) {
        this.finished = selected;
    }
}

