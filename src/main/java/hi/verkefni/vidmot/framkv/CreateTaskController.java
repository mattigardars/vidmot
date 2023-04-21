package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Controller class responsible for creating a new task and adding it to the task list.
 */
public class CreateTaskController {
    @FXML
    private TextField fxCreateTaskField;
    @FXML
    private DatePicker fxDate;
    @FXML
    private Button CreateTaskButton;
    @FXML
    private TextField fxPriorityField;
    @FXML
    private TextField fxProjectField;
    private Task task;

    private static TaskList taskList; // make the User object static so that it can be accessed from anywhere in the DashboardController class

    /**
     Sets the TaskList object.
     @param taskList The TaskList object to set.
     */
    public static void setTaskList(TaskList taskList) { // add a method to set the User object
        CreateTaskController.taskList = taskList;
    }


    private static DataModel dataModel;

    /**
     Sets the DataModel object.
     @param dataModel The DataModel object to set.
     */
    public static void setDataModel(DataModel dataModel) {
        CreateTaskController.dataModel = dataModel;
    }

    /**
     No-argument constructor required by JavaFX.
     */
    public CreateTaskController() {
        // This is the no-argument constructor required by JavaFX
    }

    /**
     Constructor that takes in a TaskList object.
     @param taskList The TaskList object to set.
     */
    public CreateTaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     Initializes the controller by creating a new TaskList object and disabling the CreateTaskButton by default.
     Also adds listeners to the textfields and datepicker for updating the button's disable status.
     */
    public void initialize() {
        taskList = new TaskList();
        CreateTaskButton.setDisable(true); // disable the button by default

        fxCreateTaskField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateCreateTaskButton();
        });

        fxProjectField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateCreateTaskButton();
        });

        fxPriorityField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateCreateTaskButton();
        });

        fxDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateCreateTaskButton();
        });
    }

    /**
     Updates the CreateTaskButton's disable status based on the values in the textfields and datepicker.
     */
    private void updateCreateTaskButton() {
        // check if both the textfield and datepicker have valid values
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();

        // enable/disable the button accordingly
        CreateTaskButton.setDisable(!hasValidValues);
    }

    /**
     Handles the event of the CreateTaskButton being clicked.
     Creates a new task with the given information and adds it to the task list.
     Then clears the input fields and disables the CreateTaskButton.
     @param event The ActionEvent that triggered the method.
     */
    @FXML
    void CreateTaskButton(ActionEvent event) {
        System.out.println("KYERI");
        String title = fxCreateTaskField.getText();
        String project = fxProjectField.getText();
        LocalDate deadline = fxDate.getValue();
        int priority = Integer.parseInt(fxPriorityField.getText());

        // create a new task with the given name and date
        Task newTask = new Task(title,project, deadline, priority);

        // add the new task to the task list
        taskList.addTask(newTask);


        // clear the input fields
        fxCreateTaskField.clear();
        fxDate.setValue(null);
        fxPriorityField.clear();
        fxProjectField.clear();

        // disable the button again
        CreateTaskButton.setDisable(true);
        // create a new label for the task and set its text
        //Label taskLabel = new Label("Title: " + newTask.getTitle() + ", Project: " + newTask.getProject() + ", Deadline: " + newTask.getDeadline() + ", Priority: " + newTask.getPriority());

        System.out.println(taskList.getTaskList());
        System.out.println("Title: " + newTask.getTitle() + ", Project: " + newTask.getProject() + ", Deadline: " + newTask.getDeadline() + ", Priority: " + newTask.getPriority());
        ViewSwitcher.switchTo((View.DASHBOARD));
        dataModel.getTaskList().addTask(newTask);

        ViewSwitcher.switchTo((View.DASHBOARD));
    }

    /**
     * Back to dasboard
     * @param event
     */
    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}
