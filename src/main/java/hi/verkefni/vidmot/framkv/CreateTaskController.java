package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import hi.verkefni.vinnsla.framkv.TaskList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private static TaskList taskList;
    private static DataModel dataModel;

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
    public static void setTaskList(TaskList taskList) { // add a method to set the User object
        CreateTaskController.taskList = taskList;
    }

    public static void setDataModel(DataModel dataModel) {
        CreateTaskController.dataModel = dataModel;
    }

    private void updateCreateTaskButton() {
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();
        if(hasValidValues){
            int priority = Integer.parseInt(fxPriorityField.getText());
            hasValidValues = (priority >= 1 && priority <= 5);
        }
        CreateTaskButton.setDisable(!hasValidValues);
    }


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


    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}
