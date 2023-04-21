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

/**
 This class is responsible for controlling the Edit Task view, which allows the user to edit the details of a task.
 */
public class EditTaskController {
    private static DataModel dataModel;

    @FXML private TextField fxCreateTaskField;
    @FXML private DatePicker fxDate;
    @FXML private TextField fxProjectField;
    @FXML private TextField fxPriorityField;
    @FXML private Button fxEditTask;

    /**
     Initializes the view with the details of the selected task, if there is one.
     Updates the create task button status based on the values entered in the fields.
     */
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

    /**
     Updates the selected task with the values entered in the fields, if they are valid.
     Clears the fields and switches the view back to the Dashboard view.
     */
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
    /**
     Updates the status of the create task button based on the values entered in the fields.
     The button is enabled only if all fields have values and the priority is between 1 and 5.
     */
    private void updateCreateTaskButton() {
        boolean hasValidValues = !fxCreateTaskField.getText().isEmpty() && fxDate.getValue() != null && !fxProjectField.getText().isEmpty() && !fxPriorityField.getText().isEmpty();
        if(hasValidValues){
            int priority = Integer.parseInt(fxPriorityField.getText());
            hasValidValues = (priority >= 1 && priority <= 5);
        }
        fxEditTask.setDisable(!hasValidValues);
    }

    /**
     Sets the DataModel instance used by this controller.
     @param dataModel the DataModel instance to be used by this controller.
     */
    public static void setDataModel(DataModel dataModel) {
        EditTaskController.dataModel = dataModel;
    }

    /**
     Clears the fields of the Edit Task view.
     */
    private void clearFields() {
        fxCreateTaskField.clear();
        fxPriorityField.clear();
        fxDate.setValue(null);
        fxPriorityField.clear();
        fxProjectField.clear();
    }

    /**
     Switches the view back to the Dashboard view.
     @param event the event that triggered the method.
     */
    @FXML
    void backToDasboardButton(ActionEvent event) {
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

}