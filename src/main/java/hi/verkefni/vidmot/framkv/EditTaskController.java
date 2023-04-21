package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditTaskController {
    private static DataModel dataModel;

    @FXML private TextField fxCreateTaskField;
    @FXML private DatePicker fxDate;
    @FXML private TextField fxProjectField;
    @FXML private TextField fxPriorityField;
    @FXML private Button fxEditTask;

    @FXML
    public void initialize() {
        Task task = dataModel.getSelectedTask();
        fxCreateTaskField.setText(task.getTitle());
        fxDate.setValue(task.getDeadline());
        fxProjectField.setText(task.getProject());
        fxPriorityField.setText(Integer.toString(task.getPriority()));
        fxEditTask.setDisable(false);

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

    @FXML
    public void editTask() {
        if (dataModel != null && dataModel.getSelectedTask() != null) {
            String title = fxCreateTaskField.getText();
            String project = fxProjectField.getText();
            LocalDate deadline = fxDate.getValue();
            int priority = Integer.parseInt(fxPriorityField.getText());
            boolean finished = dataModel.getFinished();
            Task updatedTask = new Task(title, project, deadline, priority, finished);
            dataModel.updateTask(dataModel.getSelectedTask(), updatedTask);
            clearFields();
            ViewSwitcher.switchTo(View.DASHBOARD);
        }
    }

    private void updateCreateTaskButton() {
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();
        if(hasValidValues){
            int priority = Integer.parseInt(fxPriorityField.getText());
            hasValidValues = (priority >= 1 && priority <= 5);
        }
        fxEditTask.setDisable(!hasValidValues);
    }

    public static void setDataModel(DataModel dataModel) {
        EditTaskController.dataModel = dataModel;
    }

    private void clearFields() {
        fxCreateTaskField.clear();
        fxPriorityField.clear();
        fxDate.setValue(null);
        fxPriorityField.clear();
        fxProjectField.clear();
    }

    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}
