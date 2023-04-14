package hi.verkefni.vinnsla.framkv;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private String project;
    private LocalDate deadline;
    private int priority;

    public Task(String title, String description, String project, LocalDate deadline, int priority) {
        this.title = title;
        this.project = project;
        this.deadline = deadline;
        this.priority = priority;
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
}

