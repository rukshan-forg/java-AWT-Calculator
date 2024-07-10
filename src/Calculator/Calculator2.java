package Calculator;

import java.awt.*;
import java.awt.event.*;

public class Calculator2 implements ActionListener {

    private String inputNumber = "0",operator;
    Double num1, num2, result, memory = 0.0;


    private TextField inputTextField, operationTextField;
    private final int FRAME_WIDTH = 400 , FRAME_HEIGHT = 600;
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);
    private final int INPUT_TEXT_FIELD_FONT_SIZE = 40;
    private final int OPERATOR_TEXT_FIELD_FONT_SIZE = 20;
    private final Color NUMBER_BUTTON_COLOR = Color.WHITE;
    private final Color NUMBER_BUTTON_TEXT_COLOR = new Color(78, 78, 78);
    private final Color OPERATOR_BUTTON_COLOR = new Color(19, 75, 180);
    private final Color OPERATOR_BUTTON_TEXT_COLOR = Color.WHITE;
    private final Color MAIN_TEXT_FIELD_COLOR = Color.WHITE;
    private final Color MAIN_TEXT_FIELD_TEXT_COLOR = Color.BLACK;
    private final Color SUB_TEXT_FIELD_COLOR = new Color(216, 216, 216);
    private final Color SUB_TEXT_FIELD_TEXT_COLOR = new Color(82, 82, 82);


    // Button labels for the calculator buttons
    private final String[] buttonLabels = {
            "MR",  "MC", "M+",   "M-",
            "Undo","Redo","X^" , "+-",
            "%",   "√",  "Clear","<",
            "7",   "8",  "9",    "÷",
            "4",   "5",  "6",    "×",
            "1",   "2",  "3",    "-",
            ".",   "0",  "=",    "+"
    };

    // Constructor
    public Calculator2() {
        init();
    }
    // Initialize the calculator
    private void init() {
        Frame frame = new Frame("Calculator");
        frame.setLayout(new BorderLayout());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        operationTextField = createTextField(SUB_TEXT_FIELD_COLOR, SUB_TEXT_FIELD_TEXT_COLOR, OPERATOR_TEXT_FIELD_FONT_SIZE);
        inputTextField = createTextField(MAIN_TEXT_FIELD_COLOR, MAIN_TEXT_FIELD_TEXT_COLOR, INPUT_TEXT_FIELD_FONT_SIZE);

        Panel displayPanel = new Panel(new GridLayout(2, 1));
        displayPanel.add(operationTextField);
        displayPanel.add(inputTextField);
        frame.add(displayPanel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridLayout(7, 4, 5, 5));
        for (String label : buttonLabels) {
            buttonPanel.add(createButton(label));
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        inputTextField.setText(inputNumber);
    }
    private TextField createTextField(Color bgColor, Color fgColor, int fontSize) {
        TextField textField = new TextField();
        textField.setFont(new Font("Arial", Font.BOLD, fontSize));
        textField.setBackground(bgColor);
        textField.setForeground(fgColor);
        textField.setFocusable(false);
        textField.setEditable(false);
        return textField;
    }
    private Button createButton(String label) {
        Button button = new Button(label);
        button.setFont(BUTTON_FONT);
        if (label.matches("[0-9.]")) {
            button.setBackground(NUMBER_BUTTON_COLOR);
            button.setForeground(NUMBER_BUTTON_TEXT_COLOR);
        } else {
            button.setBackground(OPERATOR_BUTTON_COLOR);
            button.setForeground(OPERATOR_BUTTON_TEXT_COLOR);
        }
        button.addActionListener(this);
        return button;
    }


    // ActionPerformed method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("=")) {
            calculate();
        } else if (command.equals("Clear")) {
            clear();
        } else if (command.equals("<")) {
            backspace();
        } else if (command.equals("+/-")) {
            toggleSign();
        } else if (command.equals("MR")) {
            memoryRead();
        } else if (command.equals("MC")) {
            memoryClear();
        } else if (command.equals("M+")) {
            memoryAdd();
        } else if (command.equals("M-")) {
            memorySubtract();
        } else if (command.matches("[0-9.]")) {
            addNumber(command);
        } else if (command.matches("[-+×÷]")) {
            setOperator(command);
        } else if (command.equals("%")) {
            calculatePercentage();
        } else if (command.equals("√")) {
            calculateSquareRoot();
        } else if (command.equals("X^")) {
            calculateSquare();
        }


        display();
        System.out.println((num1 == null ? "null" : num1) + " " + operator + " " + (num2 == null ? "null" : num2 + " = " + (result == null ? "null" : result)));
    }

    private void calculateSquare() {
        inputNumber = String.valueOf(Math.pow(Double.parseDouble(inputNumber), 2));
    }
    private void calculateSquareRoot() {
        inputNumber = String.valueOf(Math.sqrt(Double.parseDouble(inputNumber)));
    }
    private void calculatePercentage() {
        if (num1 == null) {
            num1 = Double.parseDouble(inputNumber) / 100;
        } else if (num2 == null) {
            num2 = Double.parseDouble(inputNumber) / 100;
            calculate();
        }
        inputNumber = "0";
    }
    private void setOperator(String command) {
        operator = command;

        if (result == null) {
            if (num1 == null) {
                num1 = Double.parseDouble(inputNumber);
            } else if (num2 == null) {
                num2 = Double.parseDouble(inputNumber);
                calculate();
            }
        } else {
            num1 = result;
            num2 = Double.parseDouble(inputNumber);
            result = null;
        }
        inputNumber = "0";

    }
    private void addNumber(String command) {
        if (command.equals(".") && !inputNumber.contains(".")) {
            inputNumber += command;
        } else if (inputNumber.equals("0")) {
            inputNumber = command;
        } else {
            inputNumber += command;
        }
    }
    private void memorySubtract() {
        memory -= Double.parseDouble(inputNumber);
        clearInput();
    }
    private void memoryAdd() {
        memory = Double.parseDouble(inputNumber);
        clearInput();
    }
    private void clearInput() {
        inputNumber = "0";
    }
    private void memoryClear() {
        memory = 0.0;
    }
    private void memoryRead() {
        inputNumber = String.valueOf(memory);
    }
    private void display() {
        if (num1 == null) {
            operationTextField.setText("");
        } else if (operator == null) {
            operationTextField.setText(String.valueOf(num1));
        } else if (num2 == null) {
            operationTextField.setText(num1 + " " + operator);
        } else {
            operationTextField.setText(num1 + " " + operator + " " + num2);
        }
        if (result != null) {
            inputTextField.setText(String.valueOf(result));
        }
        inputTextField.setText(inputNumber);
    }
    private void toggleSign() {
        if (!inputNumber.isEmpty()) {
            if (inputNumber.charAt(0) == '-') {
                inputNumber = inputNumber.substring(1);
            } else {
                inputNumber = "-" + inputNumber;
            }
        }
    }
    private void clear() {
        inputNumber = "0";
        num1 = null;
        num2 = null;
        operator = null;
        result = null;
    }
    private void backspace() {
        if (!inputNumber.isEmpty()) {
            inputNumber = inputNumber.substring(0, inputNumber.length() - 1);
        }
        if (inputNumber.isEmpty()) {
            inputNumber = "0";
        }
    }
    private void calculate() {

        if (num1 == null) {
            num1 = Double.parseDouble(inputNumber);
        }

        if (operator != null) {
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "×":
                    result = num1 * num2;
                    break;
                case "÷":
                    if (num2 == 0) {
                        inputTextField.setText("Cannot Divide By Zero");
                        clear();
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
        }

        if (result != null) {
            num1 = result;
            num2 = null;
            operator = null;
            result = null;
        }

    }




    public static void main(String[] args) {
        new Calculator2();
    }
}

