package hi.verkefni.vidmot.framkv;


import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.User;

/**
 Controller for the dashboard view. This class is responsible for updating the user interface
 with the appropriate data from the data model.
 The class uses FXML to define the user interface components and handles user events using
 event handlers defined in the FXML file.
 The class also contains methods for updating the task list and project list based on changes to
 the data model.
 */
public class DashboardController {

    @FXML
    private Label fxUsernameLabel;
    @FXML
    private FlowPane fxFlow;
    @FXML
    private FlowPane fxProject;
    @FXML
    private HBox projectButtonsContainer;
    @FXML
    private Label fxTodayLabel;

    private LocalDate today;
    private static User user;
    private static DataModel dataModel;
    private TaskList taskList = new TaskList();
    private String lastSelectedProject;

    /**
     Initializes the controller after its root element has been completely processed.
     This method is called after the FXML file has been loaded and all the injected fields
     have been initialized.
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
            selectFirstProject();
            updateProjectTasks(lastSelectedProject);
        });
    }

    /**
     Updates the list of tasks displayed in the project view.
     @param projectName the name of the project to display tasks for
     */
    private void updateProjectTasks(String projectName) {
        lastSelectedProject = projectName;
        fxProject.getChildren().clear();
        List<Task> projectTasks = new ArrayList<>();
        for (Task task : dataModel.getTaskList().getTaskList()) {
            if (task.getProject().equals(projectName)) {
                projectTasks.add(task);
            }
        }
        Collections.sort(projectTasks, Task.PRIORITY_COMPARATOR);
        for (Task task : projectTasks) {
            TaskListItem taskListItem = new TaskListItem(task, dataModel);
            fxProject.getChildren().add(taskListItem);
            System.out.println("updateProjectTasks() called with projectName: " + projectName);
        }
    }

    /**
     Updates the list of tasks displayed in the task list view.
     */
    private void updateTaskList() {
        System.out.println("UpdatetaskList");
        fxFlow.getChildren().clear();
        List<Task> tasks = dataModel.getTaskList().getTaskList();
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDeadline().isEqual(today)) { // filter tasks by their deadline
                filteredTasks.add(task);
            }
        }
        Collections.sort(filteredTasks, Task.PRIORITY_COMPARATOR); // sort tasks by priority
        for (Task task : filteredTasks) {
            TaskListItem taskListItem = new TaskListItem(task, dataModel);
            fxFlow.getChildren().add(taskListItem);
        }
    }

    /**
     Updates the list of project buttons based on the projects in the task list.
     */
    private void updateProjectButtons() {
        System.out.println("UPDATEPROJECTBUTTON");
        projectButtonsContainer.getChildren().clear();

        Set<String> uniqueProjects = new HashSet<>();
        for (Task task : dataModel.getTaskList().getTaskList()) {
            uniqueProjects.add(task.getProject());
        }

        int projectIndex = 0;
        for (String projectName : uniqueProjects) {
            Button projectButton = new Button(projectName);
            projectButton.setOnAction(event -> {
                updateProjectTasks(projectName);
                for (Node button : projectButtonsContainer.getChildren()) {
                    if (button instanceof Button) {
                        button.getStyleClass().remove("btnActive");
                    }
                }
                projectButton.getStyleClass().add("btnActive");
            });

            projectButton.getStyleClass().add("project");
            projectButton.setMinWidth(Region.USE_PREF_SIZE);
            projectButton.setMinHeight(Region.USE_PREF_SIZE);
            projectButtonsContainer.setSpacing(10);
            projectButtonsContainer.getChildren().add(projectButton);

            // Set the first project button to be selected by default
            if (projectIndex == 0) {
                System.out.println("HERE" + projectButton.getStyleClass());
                projectButton.getStyleClass().add("btnActive");
                System.out.println("HERE" + projectButton.getStyleClass());

                projectButton.fire();
            }
            projectIndex++;
        }
    }
    /**
     * Sets the DataModel instance for this DashboardController.
     *
     * @param dataModel the User instance to set
     */
    public static void setDataModel(DataModel dataModel) {
        DashboardController.dataModel = dataModel;
    }

    /**
     * Sets the User instance for this DashboardController.
     *
     * @param user the User instance to set
     */
    public static void setUser(User user) { // add a method to set the User object
        DashboardController.user = user;
    }

    /**
     Selects the first project button in the project buttons container and fires its action event to update the tasks displayed in the project view.
     If the container is empty, no action is taken.
     */
    private void selectFirstProject() {
        if (!projectButtonsContainer.getChildren().isEmpty()) {
            Button firstProjectButton = (Button) projectButtonsContainer.getChildren().get(0);
            firstProjectButton.fire();
        }
    }

    /**
     Sets the static User object to null and switches the view to the login view when the user clicks the logout button.
     @param event the ActionEvent that triggered this method
     */
    @FXML
    private void onLogout(ActionEvent event) {
        setUser(null);
        ViewSwitcher.switchTo(View.LOGIN);
    }

    /**
     Sets the TaskList and DataModel objects in the CreateTaskController and switches the view to the create task view
     when the user clicks the "Create Task" button.
     @param actionEvent the ActionEvent that triggered this method
     */
    public void createTaskButton(ActionEvent actionEvent) {
        CreateTaskController.setTaskList(taskList);
        CreateTaskController.setDataModel(dataModel);
        ViewSwitcher.switchTo(View.CREATETASK);
    }

}
