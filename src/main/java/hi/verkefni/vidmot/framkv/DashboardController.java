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

/**
 The DashboardController class controls the behavior and appearance of the dashboard view.
 It uses FXML to handle user interface components, including labels, panes, and buttons.
 The class contains methods for updating the task list, project tasks, and project buttons.
 It also has a method for initializing the view and a method for logging out the user.
 */
public class DashboardController {

    @FXML
    private Label fxUsernameLabel;

    private static User user; // make the User object static so that it can be accessed from anywhere in the DashboardController class

    /**
     Sets the User object to the given value.
     @param user The User object to set.
     */
    public static void setUser(User user) { // add a method to set the User object
        DashboardController.user = user;
    }

    private static DataModel dataModel;

    /**
     Sets the DataModel object to the given value.
     @param dataModel The DataModel object to set.
     */
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

    /**
     Sets the TaskList object to the given value.
     @param taskList The TaskList object to set.
     */
    public static void setTaskList(TaskList taskList) {
    }

    /**
     Updates the project tasks shown in the view to those that are associated with the given project name.
     @param projectName The name of the project to filter tasks by.
     */
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

    /**
     Updates the task list shown in the view to those that have the current date as their deadline.
     */
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

    /**
     Refreshes the task list and project buttons in the view.
     */
    public void refreshTaskList() {
        updateTaskList();
        updateProjectButtons();
    }
    /**
     Initializes the view with the current date and user information.
     Also sets up listeners for changes to the task list to update the view accordingly.
     */
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

    /**
     * This method updates the project buttons in the UI based on the task list in the data model.
     * It clears the projectButtonsContainer, creates a set of unique project names from the task list,
     * creates a button for each project, sets the button action to update the project tasks, adds
     * styling and spacing, and adds the button to the projectButtonsContainer.
     */
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

    /**
     * This method logs out the current user and switches the view to the login screen.
     * @param event The action event that triggered the method.
     */
    @FXML
    private void onLogout(ActionEvent event) {
        setUser(null);
        ViewSwitcher.switchTo(View.LOGIN);
    }

    /**
     * This method sets the task list and data model for the CreateTaskController, and switches the view to the CreateTask screen.
     * @param actionEvent The action event that triggered the method.
     */
    public void createTaskButton(ActionEvent actionEvent) {
        CreateTaskController.setTaskList(taskList);
        CreateTaskController.setDataModel(dataModel);
        ViewSwitcher.switchTo(View.CREATETASK);
    }

}
