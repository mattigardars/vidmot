package hi.verkefni.vidmot.framkv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DashboardController {

    @FXML
    private FlowPane fxFlow;

    @FXML
    private FlowPane fxProject;

    @FXML
    private HBox projectButtonsContainer;

    @FXML
    private Label fxTodayLabel;

    private LocalDate today;
    private TaskList taskList = new TaskList();

    private void updateProjectTasks(String projectName) {
        fxProject.getChildren().clear();
        for (Task task : taskList.getTaskList()) {
            if (task.getProject().equals(projectName)) {
                TaskListItem taskListItem = new TaskListItem(task);
                fxProject.getChildren().add(taskListItem);
            }
        }
    }

    public void initialize() {
        taskList.addRandomTasks();
        today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedDate = today.format(formatter);
        System.out.println(today);
        fxTodayLabel.setText(formattedDate);

        // Add a label for each task to the FlowPane
        for (Task task : taskList.getTaskList()) {
            System.out.println(task.getDeadline());
            if (task.getDeadline().equals(today)) {
                TaskListItem taskListItem = new TaskListItem(task);
                fxFlow.getChildren().add(taskListItem);
            }
        }
        updateProjectButtons();
        if (projectButtonsContainer.getChildren().size() > 0) {
            Button firstProjectButton = (Button) projectButtonsContainer.getChildren().get(0);
            firstProjectButton.fire();
            firstProjectButton.setFocusTraversable(true);
        }
    }


    private void updateProjectButtons() {
        projectButtonsContainer.getChildren().clear();

        Set<String> uniqueProjects = new HashSet<>();
        for (Task task : taskList.getTaskList()) {
            uniqueProjects.add(task.getProject());
        }

        for (String projectName : uniqueProjects) {
            Button projectButton = new Button(projectName);
            projectButton.setOnAction(event -> updateProjectTasks(projectName));
            projectButton.getStyleClass().add("project");
            projectButton.setMinWidth(Region.USE_PREF_SIZE);
            projectButton.setMinHeight(Region.USE_PREF_SIZE);
            projectButtonsContainer.setSpacing(10);
            projectButtonsContainer.getChildren().add(projectButton);


        }
    }
    @FXML
    private void handleProjectButtonClick(ActionEvent event) {
        Button projectButton = (Button) event.getSource();
        String projectName = projectButton.getText();
        updateProjectTasks(projectName);
    }

    private void editTask(Task task) {

    }

    private void deleteTask(Task task) {

    }

    private void snoozeTask(Task task) {

    }


}
