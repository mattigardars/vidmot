package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditTaskController {
    private final DataModel dataModel;
    private final TaskListItem taskListItem;
    private final Task task;

    @FXML private TextField fxTitleField;
    @FXML private DatePicker fxDatePicker;
    @FXML private TextField fxProjectField;
    @FXML private TextField fxPriorityField;
    @FXML private Button fxSaveButton;

    public EditTaskController(DataModel dataModel, TaskListItem taskListItem, Task task) {
        this.dataModel = dataModel;
        this.taskListItem = taskListItem;
        this.task = task;
    }

    @FXML
    public void initialize() {
        fxTitleField.setText(task.getTitle());
        fxDatePicker.setValue(task.getDeadline());
        fxProjectField.setText(task.getProject());
        fxPriorityField.setText(Integer.toString(task.getPriority()));
    }

    @FXML
    public void handleSaveButtonAction() {
        String title = fxTitleField.getText();
        String project = fxProjectField.getText();
        LocalDate deadline = fxDatePicker.getValue();
        int priority = Integer.parseInt(fxPriorityField.getText());

        Task updatedTask = new Task(title,project, deadline, priority);
        taskListItem.updateTask(updatedTask);
        ViewSwitcher.switchTo(View.DASHBOARD);
    }
}
