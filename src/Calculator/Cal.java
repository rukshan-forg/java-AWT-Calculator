package Calculator;

import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cal extends Frame implements ActionListener {


    private String lastOperator = "";
    private Double currentResult, memory = 0.0;
    private boolean resetDisplay = false;

    private final String[] buttonLabels = {
            "MR","MC", "M+", "M-",
            "π", "√","X²", "xʸ",
            "%", "+/-", "Clear", "CE",
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "MR":
                display.setText(formattedValue(memory));
                display.setForeground(Color.RED);
                resetDisplay = true;
                break;
            case "MC":
                memory = 0.0;
                break;
            case "M+":
                memory += getDisplayValue();
                break;
            case "M-":
                memory -= getDisplayValue();
                break;
            case "π":
                display.setText(formattedValue(Math.PI));
                break;
            case "X²":
                display.setText(formattedValue(getDisplayValue() * getDisplayValue()));
                break;
            case "+/-":
                toggleSign();
                break;
            case "%":
                display.setText(formattedValue(getDisplayValue() / 100));
                break;
            case "√":
                display.setText(formattedValue(Math.sqrt(getDisplayValue())));
                break;
            case "Clear":
                clear();
                break;
            case "CE":
                backspace();
                if (display.getText().equals("-0")) {
                    display.setText("0");
                }
                break;
            case "=":
                evaluateExpression();
                lastOperator = "";
                break;
            default:
                if ("÷×-+xʸ".contains(command)) {
                    if (!lastOperator.isEmpty()) {
                        evaluateExpression();
                    }
                    lastOperator = command;
                    currentResult = getDisplayValue();
                    display.setText(display.getText() + "  " + command);
                    display.setForeground(Color.black);
                    resetDisplay = true;
                } else {
                    if (resetDisplay) {
                        display.setText("0");
                        resetDisplay = false;
                    }
                    addInputs(command);
                }
                break;
        }
    }

    private void evaluateExpression() {
        try {
            double operand2 = getDisplayValue();

            switch (lastOperator) {
                case "+":
                    currentResult += operand2;
                    break;
                case "-":
                    currentResult -= operand2;
                    break;
                case "×":
                    currentResult *= operand2;
                    break;
                case "÷":
                    currentResult /= operand2;
                    break;
                case "xʸ":
                    currentResult = Math.pow(currentResult, operand2);
                    break;
            }

            display.setText(formattedValue(currentResult));
            display.setForeground(Color.BLUE);
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private double getDisplayValue() {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private void toggleSign() {
        if (display.getText().equals("0")) {
            return;
        }
        if (display.getText().charAt(0) == '-') {
            display.setText(display.getText().substring(1));
        } else {
            display.setText("-" + display.getText());
        }
    }
    private void clear() {
        currentResult = 0.0;
        lastOperator = "";
        display.setText("0");
    }
    private void backspace() {
        if (display.getText().length() > 1) {
            display.setText(display.getText().substring(0, display.getText().length() - 1));
        } else {
            display.setText("0");
        }
    }
    private void addInputs(String command) {

        if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + command);
            }
        } else if (display.getText().equals("0")){
            display.setText(command);
        } else {
            display.setText(display.getText() + command);
        }
        display.setForeground(Color.BLACK);
    }
    private String formattedValue(Double value) {
        if (value == 0.0) {
            return "0";
        } else if (String.valueOf(value).endsWith(".0")) {
            return String.valueOf(value).replace(".0", "");
        } else {
            return String.valueOf(value);
        }
    }

    Cal() {
        this.setTitle("Calculator");
        this.setLayout(new BorderLayout());
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        display = new TextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setFocusable(false);
        display.setEditable(false);

        Panel displayPanel = new Panel(new GridLayout(1, 1));
        displayPanel.add(display);
        this.add(displayPanel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridLayout(7, 4, 5, 5));
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setFont(BUTTON_FONT);
            button.setBackground(label.matches("[0-9.]") ? NUMBER_BUTTON_COLOR : OPERATOR_BUTTON_COLOR);
            button.setForeground(label.matches("[0-9.]") ? NUMBER_BUTTON_TEXT_COLOR : OPERATOR_BUTTON_TEXT_COLOR);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        display.setText("0");
    }

    private final TextField display;
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);
    private final Color NUMBER_BUTTON_COLOR = new Color(255, 255, 255);
    private final Color NUMBER_BUTTON_TEXT_COLOR = new Color(0, 0, 0);
    private final Color OPERATOR_BUTTON_COLOR = new Color(0, 73, 200);
    private final Color OPERATOR_BUTTON_TEXT_COLOR = Color.WHITE;
}
