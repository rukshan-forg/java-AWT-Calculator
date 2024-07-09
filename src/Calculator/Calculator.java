package Calculator;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;


public class Calculator implements ActionListener {

    int c,n;
    String s1,s2,s3,s4,s5;
    Frame frame;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bPlus,bMinus,bMul,bDiv,bEqual,bClear;
    Panel panel;
    TextField t1;
    GridLayout gl;

    int frameWidth = 400;
    int frameHeight = 600;

    public Calculator() {
        frame = new Frame("Calculator");
        frame.setLayout(new GridLayout(4,4));


        b0 = new Button("0");
        b0.addActionListener(this);

        b1 = new Button("1");
        b1.addActionListener(this);

        b2 = new Button("2");
        b2.addActionListener(this);

        b3 = new Button("3");
        b3.addActionListener(this);

        b4 = new Button("4");
        b4.addActionListener(this);

        b5 = new Button("5");
        b5.addActionListener(this);

        b6 = new Button("6");
        b6.addActionListener(this);

        b7 = new Button("7");
        b7.addActionListener(this);

        b8 = new Button("8");
        b8.addActionListener(this);

        b9 = new Button("9");
        b9.addActionListener(this);

        bPlus = new Button("+");
        bPlus.addActionListener(this);

        bMinus = new Button("-");
        bMinus.addActionListener(this);

        bMul = new Button("*");
        bMul.addActionListener(this);

        bDiv = new Button("/");
        bDiv.addActionListener(this);

        bEqual = new Button("=");
        bEqual.addActionListener(this);

        bClear = new Button("C");
        bClear.addActionListener(this);

        t1 = new TextField(20);
        t1.setSize(frameWidth,frameHeight/4);
        frame.add(t1);

        gl = new GridLayout(4,4);
        panel = new Panel();
        panel.setSize(frameWidth,frameHeight);
        panel.setLayout(gl);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(bDiv);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(bMul);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(bMinus);
        panel.add(b0);
        panel.add(bClear);
        panel.add(bPlus);
        panel.add(bEqual);
        frame.add(panel);

        frame.add(panel);
        frame.setSize(frameWidth, frameHeight);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
}