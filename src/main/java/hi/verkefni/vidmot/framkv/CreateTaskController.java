package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 This class is the controller for the create task view, which allows the user to create a new task.
 It contains methods to initialize the view and handle user interactions with the view.
 The class has access to the task list and data model, which it uses to add new tasks to the system.
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

    private static TaskList taskList;
    private static DataModel dataModel;

    /**
     Initializes the create task view and sets up listeners on the input fields.
     Disables the create task button until all fields have valid input.
     */
    public void initialize() {
        taskList = new TaskList();
        CreateTaskButton.setDisable(true);

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
     Sets the task list for this controller.
     @param taskList the task list to be set
     */
    public static void setTaskList(TaskList taskList) { // add a method to set the User object
        CreateTaskController.taskList = taskList;
    }

    /**
     Sets the data model for this controller.
     @param dataModel the data model to be set
     */
    public static void setDataModel(DataModel dataModel) {
        CreateTaskController.dataModel = dataModel;
    }

    /**
     Enables or disables the create task button based on the validity of the input fields.
     */
    private void updateCreateTaskButton() {
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();
        if(hasValidValues){
            int priority = Integer.parseInt(fxPriorityField.getText());
            hasValidValues = (priority >= 1 && priority <= 5);
        }
        CreateTaskButton.setDisable(!hasValidValues);
    }


    /**
     Creates a new task with the input values and adds it to the task list and data model.
     Clears the input fields and disables the create task button.
     @param event the event that triggered this method
     */
    @FXML
    void CreateTaskButton(ActionEvent event) {
        String title = fxCreateTaskField.getText();
        String project = fxProjectField.getText();
        LocalDate deadline = fxDate.getValue();
        int priority = Integer.parseInt(fxPriorityField.getText());

        Task newTask = new Task(title,project, deadline, priority, false);

        taskList.addTask(newTask);

        fxCreateTaskField.clear();
        fxDate.setValue(null);
        fxPriorityField.clear();
        fxProjectField.clear();

        CreateTaskButton.setDisable(true);

        dataModel.getTaskList().addTask(newTask);

        ViewSwitcher.switchTo((View.DASHBOARD));
    }

    /**
     Switches the view back to the dashboard.
     @param event the event that triggered this method
     */
    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}
