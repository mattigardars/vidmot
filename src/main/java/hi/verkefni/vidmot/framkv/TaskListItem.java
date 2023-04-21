package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import hi.verkefni.vinnsla.framkv.Task;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 The TaskListItem class represents a single item in the list of tasks displayed in the dashboard view.
 It extends the HBox class to allow for horizontal layout of its child elements.
 */
public class TaskListItem extends HBox {
    private Task task;
    private CheckBox checkBox;
    private Label taskLabel;
    private Button delayButton;
    private Button editButton;
    private Button deleteButton;

    private DataModel dataModel;

    /**
     Constructs a new TaskListItem object with the specified task and data model.
     @param task the Task object to display
     @param dataModel the DataModel object containing the list of tasks
     */
    public TaskListItem(Task task, DataModel dataModel) {
        super(10);
        this.dataModel = dataModel;
        this.task = task;

        setAlignment(Pos.CENTER_LEFT);

        checkBox = new CheckBox();
        checkBox.setSelected(false);
        checkBox.getStyleClass().add("checkbox");

        taskLabel = new Label(task.getTitle());
        taskLabel.setAlignment(Pos.CENTER_LEFT);
        taskLabel.setMaxWidth(Double.MAX_VALUE);
        taskLabel.setWrapText(true);
        HBox.setHgrow(taskLabel, Priority.ALWAYS);

        delayButton = new Button();
        ImageView delayImage = new ImageView(new Image(getClass().getResourceAsStream("/hi.verkefni.assets/delay.png")));
        delayImage.setFitWidth(16); // set the width of the image to 16 pixels
        delayImage.setFitHeight(16); // set the height of the image to 16 pixels
        delayButton.setGraphic(delayImage);

        // snoozeButton.setOnAction(event -> snoozeTask(task));

        editButton = new Button();
        ImageView editImage = new ImageView(new Image(getClass().getResourceAsStream("/hi.verkefni.assets/edit.png")));
        editImage.setFitWidth(16); // set the width of the image to 16 pixels
        editImage.setFitHeight(16); // set the height of the image to 16 pixels
        editButton.setGraphic(editImage);
        //editButton.setOnAction(event -> {

        deleteButton = new Button();
        ImageView deleteImage = new ImageView(new Image(getClass().getResourceAsStream("/hi.verkefni.assets/delete.png")));
        deleteImage.setFitWidth(16); // set the width of the image to 16 pixels
        deleteImage.setFitHeight(16); // set the height of the image to 16 pixels
        deleteButton.setGraphic(deleteImage);
        Region spacer = new Region();
        spacer.setPrefWidth(10);
        Region spacer2 = new Region();
        spacer2.setPrefWidth(40);
        Region spacer3 = new Region();
        spacer3.setPrefWidth(40);
        getChildren().addAll(checkBox, spacer ,taskLabel, spacer2, delayButton, editButton, deleteButton, spacer3);
        delayButton.getStyleClass().add("iconButtons");
        editButton.getStyleClass().add("iconButtons");
        deleteButton.getStyleClass().add("iconButtons");
        delayButton.setOnAction(event -> {
            dataModel.snoozeTask(task);
            refresh();
        });
        editButton.setOnAction(event -> {
            EditTaskController editTaskController = new EditTaskController(dataModel, this, task);
            ViewSwitcher.switchTo(View.EDITTASK);
        });
        deleteButton.setOnAction(event -> dataModel.deleteTask(task));


    }

    /**
     Returns the Task associated with this TaskListItem.
     @return the Task object associated with this TaskListItem.
     */
    public Task getTask() {
        return task;
    }

    /**
     Updates the Task associated with this TaskListItem.
     @param task the new Task object to associate with this TaskListItem.
     */
    public void updateTask(Task task) {
        this.task = task;
        taskLabel.setText(task.getTitle());
    }

    /**
     Refreshes the display of this TaskListItem to reflect the current state of its associated Task object.
     */
    public void refresh() {
        taskLabel.setText(task.getTitle());
    }

    private EditTaskController editTaskController;

    /**
     Sets the EditTaskController object that will handle edits for this TaskListItem.
     @param editTaskController the EditTaskController object to set.
     */
    public void setEditTaskController(EditTaskController editTaskController) {
        this.editTaskController = editTaskController;
    }

}
