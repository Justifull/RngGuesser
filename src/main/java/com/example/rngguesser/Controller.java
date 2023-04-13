package com.example.rngguesser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;


public class Controller {

    @FXML
    public Label actionsLabel = new Label();

    @FXML
    public Label titleLabel = new Label();

    @FXML
    public Label resultLabel = new Label();

    @FXML
    private TextField textField;

    @FXML
    private Button reStartButton;

    int input;

    public boolean played = false;

    public boolean hints = false;

    public static int randomEnd = getRandomStart(200);

    public static int randomNum = new Rng(randomEnd).num();

    public static int getRandomStart(int n) {
        int output;
        while (true) {
            int cur = new Rng(n).num();
            if (cur % 5 == 0) {
                output = cur;
                break;
            }
        }
        return output;
    }

    public static Counter counter = new Counter();

    public void test() {
        try {
            input = Integer.parseInt(textField.getText());

            if(input > randomEnd) {
                actionsLabel.setText("[Only Numbers under [" + randomEnd + "]]");
                return;
            }

            if(input == randomNum) {
                counter.addCount();
                actionsLabel.setText("Success!");
                actionsLabel.setTextFill(GREEN);
                resultLabel.setVisible(true);
                resultLabel.setText("Number [" + randomNum + "]" + " with [" + counter.getCount() + "] Trys");
                textField.setEditable(false);
                played = true;
                reset();
            }
            else {
                counter.addCount();
                actionsLabel.setText("Trys [" + counter.getCount() + "]");
                if (hints == true) {
                    if (input < randomNum) {
                        resultLabel.setVisible(true);
                        if (randomNum - input < 3) resultLabel.setText("[*CLOSE, but Higher*]");
                        else if (randomNum - input < 10) resultLabel.setText("[*Higher*]");
                        else resultLabel.setVisible(false);
                    }
                    if (input > randomNum) {
                        resultLabel.setVisible(true);
                        if (input - randomNum < 3) resultLabel.setText("[*CLOSE, but Lower*]");
                        else if (input - randomNum < 10) resultLabel.setText("[*Lower*]");
                        else resultLabel.setVisible(false);
                    }
                } else {
                    resultLabel.setVisible(false);
                }
            }
        }
        catch (NumberFormatException e){
            actionsLabel.setText("[Only enter Numbers]");
        }
        catch (Exception e) {
            actionsLabel.setText("[Error]");
        }
    }

    public void reset() {
        reStartButton.setVisible(true);
        reStartButton.setText("[Restart]");
    }
    
    public void pressEnter(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            test();
            textField.clear();
        }
    }

    public void checked(ActionEvent e) {
        if (hints == false) {
            hints = true;
        } else {
            hints = false;
        }
    }

    public void reStart(ActionEvent e) {
        if (played) {
            textField.setEditable(true);
            randomEnd = getRandomStart(200);
            randomNum = new Rng(randomEnd).num();
            counter = new Counter();
            actionsLabel.setText("Trys [" + counter.getCount() + "]");
            actionsLabel.setTextFill(BLACK);
            resultLabel.setVisible(false);
        }
        titleLabel.setText("Number between [0 - " + randomEnd + "]");
        textField.setEditable(true);
        reStartButton.setVisible(false);
        played = false;
    }
}