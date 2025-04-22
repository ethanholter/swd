import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Controller for the QuizGUI application.
 */
public class QuizController {
    @FXML
    RadioButton q1Correct;
    @FXML
    CheckBox q2A, q2B, q2C, q2D;
    @FXML
    TextArea q3TextArea;
    @FXML
    ChoiceBox tipBox;
    @FXML
    VBox goodList, badList;
    @FXML
    Label score;
    @FXML
    Label draggable_0, draggable_1, draggable_2, draggable_3, draggable_4;
    @FXML
    TextField signatureArea;

    // workaround because either I'm stupid or jfx has weird behavior with parent reassignment
    private int[] draggables = {0, 0, 0, 0, 0};

    /**
     * called when the window is first loaded. Sets the default value of the tipBox to "50%".
     */
    public void initialize() {
        tipBox.getSelectionModel().select(3);
    }

    /**
     * responsible for handling the "drag and drop" functionality of the draggable labels.
     * @param e the MouseEvent that triggered this method.
     */
    @FXML
    void listItemOnClick(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource();
        int index = Integer.parseInt(Character.toString(clickedLabel.getId().charAt(10)));
        System.out.println(index);

        if (clickedLabel.getParent() == goodList) {
            goodList.getChildren().remove(clickedLabel);
            badList.getChildren().add(clickedLabel);
            draggables[index] = 1;
        } else {
            badList.getChildren().remove(clickedLabel);
            goodList.getChildren().add(clickedLabel);
            draggables[index] = 0;
        }
    }


    /**
     * Tallies the score of the quiz and displays it to the user.
     * @param event unused
     */
    @FXML
    void quizSubmitted(ActionEvent event) {
        if (signatureArea.getText().isEmpty()) {
            this.score.setText("Please sign your name");
            return;
        }

        double currScore = 0;
        if (q1Correct.isSelected()) {
            currScore += 1;
        }
        if (q2A.isSelected()) {
            currScore += 0.25;
        }
        if (q2B.isSelected()) {
            currScore += 0.25;
        }
        if (q2C.isSelected()) {
            currScore += 0.25;
        }
        if (q2D.isSelected()) {
            currScore += 0.25;
        }
        if (q3TextArea.getText().equals("42")) {
            currScore += 4;
        }
        if (tipBox.getSelectionModel().isSelected(0)) {
            currScore -= 1;
        }
        if (tipBox.getSelectionModel().isSelected(1)) {
            currScore -= 0.25;
        }
        if (tipBox.getSelectionModel().isSelected(2)) {
            currScore += 1;
        }
        if (tipBox.getSelectionModel().isSelected(3)) {
            currScore += 3;
        }

        //0, 2, 4 good
        // 1, 3 bad
        if (draggables[0] == 0) {
            currScore += 0.2;
        }
        if (draggables[1] == 1) {
            currScore += 0.2;
        }
        if (draggables[2] == 0) {
            currScore += 0.2;
        }
        if (draggables[3] == 1) {
            currScore += 0.2;
        }
        if (draggables[4] == 0) {
            currScore += 0.2;
        }

        this.score.setText("Score: " + (Math.round(currScore*100.0))/100.0 + "/10");
    }
}
