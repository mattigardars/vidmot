package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
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
    @FXML private Button fxSaveButton;

    public static void setDataModel(DataModel dataModel) {
        EditTaskController.dataModel = dataModel;
    }
    public EditTaskController() {
    }

    @FXML
    public void initialize() {
        if (dataModel != null && dataModel.getSelectedTask() != null) {
            Task task = dataModel.getSelectedTask();
            fxCreateTaskField.setText(task.getTitle());
            fxDate.setValue(task.getDeadline());
            fxProjectField.setText(task.getProject());
            fxPriorityField.setText(Integer.toString(task.getPriority()));
        }
    }

    @FXML
    public void handleSaveButtonAction() {
        if (dataModel != null && dataModel.getSelectedTask() != null) {
            String title = fxCreateTaskField.getText();
            String project = fxProjectField.getText();
            LocalDate deadline = fxDate.getValue();
            int priority = Integer.parseInt(fxPriorityField.getText());

            Task updatedTask = new Task(title, project, deadline, priority);
            dataModel.updateTask(dataModel.getSelectedTask(), updatedTask);
            ViewSwitcher.switchTo(View.DASHBOARD);
        }
    }

}
