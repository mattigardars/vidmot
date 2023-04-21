package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import hi.verkefni.vinnsla.framkv.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 The EditTaskController class handles user input and updates an existing task with new information.
 */
public class EditTaskController {
    private final DataModel dataModel;
    private final TaskListItem taskListItem;
    private final Task task;

    @FXML private TextField fxTitleField;
    @FXML private DatePicker fxDatePicker;
    @FXML private TextField fxProjectField;
    @FXML private TextField fxPriorityField;
    @FXML private Button fxSaveButton;

    /**
     Constructs an EditTaskController with the given DataModel, TaskListItem, and Task objects.
     @param dataModel the DataModel object to be used
     @param taskListItem the TaskListItem object to be updated
     @param task the Task object to be edited
     */
    public EditTaskController(DataModel dataModel, TaskListItem taskListItem, Task task) {
        this.dataModel = dataModel;
        this.taskListItem = taskListItem;
        this.task = task;
    }

    /**
     Initializes the EditTaskController with the task's current information.
     */
    @FXML
    public void initialize() {
        fxTitleField.setText(task.getTitle());
        fxDatePicker.setValue(task.getDeadline());
        fxProjectField.setText(task.getProject());
        fxPriorityField.setText(Integer.toString(task.getPriority()));
    }

    /**
     Updates the existing task with the new information provided by the user and returns to the dashboard view.
     */
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
