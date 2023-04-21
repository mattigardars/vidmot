package hi.verkefni.vinnsla.framkv;

import hi.verkefni.vidmot.framkv.EditTaskController;

import java.time.LocalDate;
import java.util.Comparator;

public class Task {
    private String title;
    private String project;
    private LocalDate deadline;
    private int priority;

    private boolean finished;
    public Task(String title, String project, LocalDate deadline, int priority, boolean finished) {
        this.title = title;
        this.project = project;
        this.deadline = deadline;
        this.priority = priority;
        this.finished = finished;
    }
    public boolean isFinished() {
        return finished;
    }
    public static final Comparator<Task> PRIORITY_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task task1, Task task2) {
            return Integer.compare(task1.getPriority(), task2.getPriority());
        }
    };

    @Override
    public String toString() {
        return "Title: " + title + ", Project: " + project + ", Deadline: " + deadline + ", Priority: " + priority;
    }

    public String getTitle() {
        return title;
    }

    public String getProject() {
        return project;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void addDeadline() {
        this.deadline = deadline.plusDays(2);
    }

    public void setFinished(boolean selected) {
        this.finished = selected;
    }
}

