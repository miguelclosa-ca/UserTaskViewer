/**
 * [IMPORTANT]
 * Requires https://gluonhq.com/products/javafx/
 *
 * Simple Task list viewer.
 *
 * Description:
 * A 300 by 500 program written in Java using JavaFX and Swing
 * depicting a Task list. Users can add Tasks to their list, or remove from said list.
 *
 * @author Miguel Closa
 * @date 20 May 2024
 *
 */



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TasksGUI extends Application {


    @Override
    public void start(Stage mainStage) {

        // For our ListView later, this is required. This stores all the user's tasks.
        ArrayList<String> userTasks = new ArrayList<>();

        // Create the Pane and the Scene
        // Create a Pane
        Pane myPane = new Pane();

        // We will need 2 buttons, a listview, and a label

        // Create the Label
        Label title = new Label("My Tasks");
        title.setFont(new Font("Arial", 24));
        title.relocate(100,10);


        // Create the Add Button
        Button addButton = new Button("Add Task");
        addButton.setPrefSize(100, 50);
        addButton.relocate(35,435);

        // Create the Remove Button
        Button removeButton = new Button("Delete Task");
        removeButton.setPrefSize(100,50);
        removeButton.relocate(165, 435);
        removeButton.setDisable(true);


        // Create the list of tasks (ListView)
        ListView<String> tasksList = new ListView<>();
        tasksList.relocate(10,45);
        tasksList.setPrefSize(275,375);


        // When the [Add Task] button is pressed,
        // Bring up a window to ask what task to add, then add it to the list of tasks
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String task = javax.swing.JOptionPane.showInputDialog("Enter a Task");

                if (task != null) {
                    userTasks.add(task);
                    ObservableList<String> currentTasks = FXCollections.observableArrayList(userTasks);
                    tasksList.setItems(currentTasks);
                } // End if
            }
        });

        // When a task is clicked on in the list of tasks,
        // Enable the [Remove Task] button.
        tasksList.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // First check if the list is empty; the [Remove Task] button must not be enabled if it is
                if (!tasksList.getSelectionModel().getSelectedItems().isEmpty()){
                    // End if
                    removeButton.setDisable(tasksList.getSelectionModel().getSelectedIndex() < 0);
                } // End if
            }
        });


        // When the [Remove Task] button is pressed,
        // Remove a task.
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                String taskToRemove = tasksList.getSelectionModel().getSelectedItem();
                userTasks.remove(tasksList.getSelectionModel().getSelectedItem());
                ObservableList<String> currentTasks = FXCollections.observableArrayList(userTasks);
                tasksList.setItems(currentTasks);
                removeButton.setDisable(true);


            }
        });





        // Add all components to the Pane
        myPane.getChildren().addAll(title, addButton, removeButton, tasksList);


        // Create the Scene
        Scene scene = new Scene(myPane, 300, 500);

        // Change the attributes for the Stage
        mainStage.setTitle("My Tasks");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();

    }

}
