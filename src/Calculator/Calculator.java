package Calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Calculator implements ActionListener {

    private final Vector<String> history = new Vector<>(10);
    private final Vector<String> operations = new Vector<>();
    private Frame frame;
    private Panel buttonPanel, displayPanel;
    private TextField inputTextField, operationTextField, resultTextField;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 800;
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);
    private static MenuBar menuBar;

    private static final Color NUMBER_BUTTON_COLOR = new Color(255, 255, 255);
    private static final Color NUMBER_BUTTON_TEXT_COLOR = new Color(78, 78, 78);
    private static final Color OPERATOR_BUTTON_COLOR = new Color(19, 75, 180);
    private static final Color OPERATOR_BUTTON_TEXT_COLOR = new Color(255, 255, 255);
    private static final Color MAIN_TEXT_FIELD_COLOR = new Color(255, 255, 255);
    private static final Color MAIN_TEXT_FIELD_TEXT_COLOR = new Color(0, 0, 0);
    private static final Color SUB_TEXT_FIELD_COLOR = new Color(216, 216, 216);
    private static final Color SUB_TEXT_FIELD_TEXT_COLOR = new Color(82, 82, 82);

    public Calculator() {
        this.init();
    }

    private void init() {
        frame = new Frame("Calculator");
        frame.setLayout(new GridLayout(2, 1));
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        MenuItem NewMenuItem = new MenuItem("New");
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.add(NewMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        frame.setMenuBar(menuBar);


        operationTextField = createTextField(SUB_TEXT_FIELD_COLOR, SUB_TEXT_FIELD_TEXT_COLOR, 20);
        resultTextField = createTextField(SUB_TEXT_FIELD_COLOR, SUB_TEXT_FIELD_TEXT_COLOR, 20);
        inputTextField = createTextField(MAIN_TEXT_FIELD_COLOR, MAIN_TEXT_FIELD_TEXT_COLOR, 40);

        displayPanel = new Panel(new GridLayout(3, 1));
        displayPanel.add(operationTextField);
        displayPanel.add(resultTextField);
        displayPanel.add(inputTextField);
        frame.add(displayPanel);

        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttonLabels = {
                "undo","redo","(",")",
                "%", "√", "Clear", "<",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            Button button = createButton(label);
            buttonPanel.add(button);
        }

        frame.add(buttonPanel);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
