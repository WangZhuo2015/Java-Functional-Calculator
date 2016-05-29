/**
 * Created by wz on 16/5/25.
 */


import java.util.stream.Stream;


public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;
    private Boolean userIsInTheMiddleOfTyping = false;
    CalculatorController(){
        view = new CalculatorView(this);
        model = new CalculatorModel(this);
    }

    public void touchDigit(String title){
        if (userIsInTheMiddleOfTyping) view.setDisplay(view.getDisplay() + title);
        else view.setDisplay(title);
        if (!(Stream.of(view.getDisplay()).filter(ch -> ch!="0").count()==0)) userIsInTheMiddleOfTyping = true;
        System.out.print("touchDigit\n");
    }
    public void performOperation(String symbol){
        if (userIsInTheMiddleOfTyping){
            model.setOperand(Double.parseDouble(view.getDisplay()));
            userIsInTheMiddleOfTyping = false;
        }
        model.performOperaion(symbol);
        view.setDisplay(String.format("%f",model.accumulator));
        System.out.print("performOperation\n");
    }
}



//使用Lambda
//class KeyBoardDigitAction implements ActionListener{
//    private CalculatorController controller;
//    private String title;
//    public KeyBoardDigitAction(CalculatorController controller, String title){
//        this.controller = controller;
//        this.title = title;
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        controller.touchDigit(title);
//    }
//}
//class KeyBoardOperationAction implements ActionListener{
//    private CalculatorController controller;
//    private String title;
//    public KeyBoardOperationAction(CalculatorController controller,String title){
//        this.controller = controller;
//        this.title = title;
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        controller.performOperation(title);
//    }
//}
