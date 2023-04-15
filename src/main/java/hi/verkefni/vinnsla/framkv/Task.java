package hi.verkefni.vinnsla.framkv;

import hi.verkefni.vidmot.framkv.EditTaskController;

import java.time.LocalDate;

public class Task {
    private String title;
    private String project;
    private LocalDate deadline;
    private int priority;

    public Task(String title, String project, LocalDate deadline, int priority) {
        this.title = title;
        this.project = project;
        this.deadline = deadline;
        this.priority = priority;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

