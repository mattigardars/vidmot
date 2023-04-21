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

public class TaskListItem extends HBox {
    private Task task;
    private CheckBox checkBox;
    private Label taskLabel;
    private Button delayButton;
    private Button editButton;
    private Button deleteButton;
    private DataModel dataModel;

    public TaskListItem(Task task, DataModel dataModel) {
        super(10);
        this.dataModel = dataModel;
        this.task = task;

        setAlignment(Pos.CENTER_LEFT);

        checkBox = new CheckBox();
        checkBox.setSelected(task.isFinished());
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

        editButton = new Button();
        ImageView editImage = new ImageView(new Image(getClass().getResourceAsStream("/hi.verkefni.assets/edit.png")));
        editImage.setFitWidth(16); // set the width of the image to 16 pixels
        editImage.setFitHeight(16); // set the height of the image to 16 pixels
        editButton.setGraphic(editImage);

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
            System.out.println("Edit task is: " + dataModel.getSelectedTask());
            dataModel.setSelectedTask(task);
            System.out.println("Edit task is: " + dataModel.getSelectedTask());
            ViewSwitcher.switchToNoCache(View.EDITTASK);
        });
        deleteButton.setOnAction(event -> dataModel.deleteTask(task));

        checkBox.setOnAction(event -> { // add an event handler to update the task when the checkbox is checked or unchecked
            task.setFinished(checkBox.isSelected());
            dataModel.updateTask(task, task);
        });
    }

    public void refresh() {
        taskLabel.setText(task.getTitle());
        checkBox.setSelected(task.isFinished());
    }

}
