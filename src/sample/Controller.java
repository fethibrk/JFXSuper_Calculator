package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.eagle.ParseEngine;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private TextField screen1;
    @FXML
    private TextField screen2;
    @FXML
    private Button sin;
    @FXML
    private Button cos;
    @FXML
    private Button tan;
    @FXML
    private Button log;
    @FXML
    private Button sq;
    @FXML
    private Button paren;
    @FXML
    private Label status;
    private boolean shift_status = false; //shift off
    private String on_off = "On";
    private String par = "(";

    public void handleButtonAction(ActionEvent event) {
        String text = ((Labeled) event.getSource()).getText();
        if(text.equalsIgnoreCase("()")) {
            if(par.equalsIgnoreCase("(")) {
                screen1.appendText(par);
                par = ")";
            }else {
                screen1.appendText(par);
                par = "(";
            }
        }else if(text.equalsIgnoreCase("x!")){
            screen1.appendText("!");
        }else {
            screen1.appendText(text);
        }
    }

    public void equal() {
        ParseEngine pme = new ParseEngine(screen1.getText());
        double x = pme.parse();
        screen2.setText(screen1.getText()+" = " + x);
        screen1.setText(x+"");
    }

    public void shift() {
        status.setText(on_off);
        if(!shift_status) {
            sin.setText("asin");
            cos.setText("acos");
            tan.setText("atan");
            log.setText("ln ");
            sq.setText("^");
            paren.setText("x!");
            shift_status = true;
        }else {
            sin.setText("sin ");
            cos.setText("cos ");
            tan.setText("tan ");
            log.setText("log ");
            sq.setText("√");
            paren.setText("()");
            shift_status = false;
        }
        if(on_off.equalsIgnoreCase("On")) {
            on_off = "Off";
            status.setTextFill(Color.GREEN);
        }else {
            on_off = "On";
            status.setTextFill(Color.RED);
        }
    }

    public void clear() {
        screen1.clear();
    }

    public void undo() {
        String s = screen1.getText();
        if (!s.isEmpty()) {
            if(s.charAt(s.length()-1) >= 'a' &&
                    s.charAt(s.length()-1) <= 'z') {
                if(s.length() <= 3 && !s.substring(s.length()-2).equals("ln")) {
                    screen1.deleteText(s.length()-3,s.length());
                }else if(s.length() >= 4 && !s.substring(s.length()-2).equals("ln")) {
                    screen1.deleteText(s.length()-4,s.length());
                }else{
                    screen1.deleteText(s.length()-2,s.length());
                }
            }else
                screen1.deleteText(s.length() - 1, s.length());
        }
    }
}
