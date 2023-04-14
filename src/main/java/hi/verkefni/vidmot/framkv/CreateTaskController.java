package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
    private TaskList taskList;

    public CreateTaskController() {
        // This is the no-argument constructor required by JavaFX
    }

    public CreateTaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }




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

    private void updateCreateTaskButton() {
        // check if both the textfield and datepicker have valid values
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();

        // enable/disable the button accordingly
        CreateTaskButton.setDisable(!hasValidValues);
    }

    @FXML
    void CreateTaskButton(ActionEvent event) {
        String taskName = fxCreateTaskField.getText();
        String project = fxProjectField.getText();
        LocalDate taskDate = fxDate.getValue();
        int priority = Integer.parseInt(fxPriorityField.getText());

        // create a new task with the given name and date
        Task newTask = new Task(taskName,project, taskDate, priority);

        // add the new task to the task list
        taskList.addTask(newTask);

        ObservableList<Task> tasks = taskList.getTaskList();

        // clear the input fields
        fxCreateTaskField.clear();
        fxDate.setValue(null);
        fxPriorityField.clear();
        fxProjectField.clear();

        //DashboardController dashboardController = new DashboardController(taskList);
        // disable the button again
        CreateTaskButton.setDisable(true);
        System.out.println(taskList.getTaskList());
    }

    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}
