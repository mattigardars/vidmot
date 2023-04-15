package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import hi.verkefni.vinnsla.framkv.User;

public class DashboardController {

    @FXML
    private Label fxUsernameLabel;

    private static User user; // make the User object static so that it can be accessed from anywhere in the DashboardController class

    public static void setUser(User user) { // add a method to set the User object
        DashboardController.user = user;
    }

    private static DataModel dataModel;

    public static void setDataModel(DataModel dataModel) {
        DashboardController.dataModel = dataModel;
    }

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

    private String lastSelectedProject;

    public static void setTaskList(TaskList taskList) {
    }

    private void updateProjectTasks(String projectName) {
        lastSelectedProject = projectName;
        fxProject.getChildren().clear();
        for (Task task : dataModel.getTaskList().getTaskList()) { // Use dataModel.getTaskList() instead of taskList
            if (task.getProject().equals(projectName)) {
                TaskListItem taskListItem = new TaskListItem(task, dataModel);
                EditTaskController editTaskController = new EditTaskController(dataModel, taskListItem, task);
                taskListItem.setEditTaskController(editTaskController);
                fxProject.getChildren().add(taskListItem);
                System.out.println("updateProjectTasks() called with projectName: " + projectName);
            }
        }
    }

    private void updateTaskList() {
        System.out.println("UpdatetaskList");
        fxFlow.getChildren().clear();
        for (Task task : dataModel.getTaskList().getTaskList()) {
            if (task.getDeadline().isEqual(today)) { // Add this line to filter tasks by their deadline
                System.out.println(task.getDeadline());
                TaskListItem taskListItem = new TaskListItem(task, dataModel);
                fxFlow.getChildren().add(taskListItem);
            }
        }
    }

    public void refreshTaskList() {
        updateTaskList();
        updateProjectButtons();
    }

    public void initialize() {
        today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedDate = today.format(formatter);
        System.out.println(today);
        fxTodayLabel.setText(formattedDate);

        fxUsernameLabel.setText(user.getName()); // use the User object to set the text of the label
        dataModel.getTaskList().getTaskList().addListener((ListChangeListener.Change<? extends Task> change) -> {
            updateTaskList();
            updateProjectButtons();
            updateProjectTasks(lastSelectedProject);
        });
    }

    private void updateProjectButtons() {
        System.out.println("UPDATEPROJECTBUTTON");
        projectButtonsContainer.getChildren().clear();

        Set<String> uniqueProjects = new HashSet<>();
        for (Task task : dataModel.getTaskList().getTaskList()) { // Use dataModel.getTaskList() instead of taskList
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
    private void onLogout(ActionEvent event) {
        setUser(null);
        ViewSwitcher.switchTo(View.LOGIN);
    }


    public void createTaskButton(ActionEvent actionEvent) {
        CreateTaskController.setTaskList(taskList);
        CreateTaskController.setDataModel(dataModel);
        ViewSwitcher.switchTo(View.CREATETASK);
    }

}
