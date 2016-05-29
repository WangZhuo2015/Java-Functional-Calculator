///**
// * Created by wz on 16/5/25.
// */



import javax.swing.*;
import java.awt.*;

public class CalculatorView {

    static final int row = 5 ;
    static final int col = 4;
    CalculatorController controller;

    //显示
    JLabel displayArea = new JLabel("0",JLabel.RIGHT);

    public CalculatorView(CalculatorController controller){
        this.controller = controller;
        setContent();
    }

    public void setDisplay(String display){
        this.displayArea.setText(display);
    }
    public String getDisplay(){
        return displayArea.getText();
    }

    public void setContent() {
        String[] buttonTitle = {"AC","+/-","%","÷","7","8","9","x","4","5","6","-","1","2","3","+","0","0",".","="};
        //窗体
        JFrame frame = new JFrame("Calculator");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        //显示部分
        JPanel display = new JPanel();
        displayArea.setFont(new Font("Arial",Font.ITALIC,24));
        displayArea.setBackground(Color.LIGHT_GRAY);

        display.add(displayArea);
        //键盘部分
        JPanel keyBoard = new JPanel(new GridLayout(row,col,8,8));
        container.add(display,BorderLayout.NORTH);
        container.add(keyBoard, BorderLayout.CENTER);

        JButton[] buttons = new JButton[20];
        int index = 0;
        for (String title:buttonTitle) {
            JButton button = new JButton();
            button.setSize(30,30);
            button.setText(title);
            button.setFont(new Font("Arial",Font.ITALIC,24));
            if ( title.compareTo("9")<=0 && title.compareTo("0")>=0){
                //Java8 Lambda
                button.addActionListener(event -> {
                    controller.touchDigit(title);
                });
            }else{
                button.addActionListener(event -> {
                    controller.performOperation(title);
                });
            }

            buttons[index] = button;
            keyBoard.add(button);
            index ++;
        }
        frame.setSize(400,300);
        frame.setVisible(true);
    }
}
