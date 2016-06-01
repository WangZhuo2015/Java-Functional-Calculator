import java.util.HashMap;

/**
 * Created by wz on 16/5/25.
 */
public class CalculatorModel {
    private CalculatorController controller;
    private boolean binaryOperationNotComplete = false;
    private Double lastBinaryOperation = 0.0;
    private BinaryOperation notCompleteBinaryOperation;
    private Boolean tappedNewNumber = false;
    public Double accumulator = 0.0;

    private HashMap<String,BinaryOperation> binaryOperators  = new HashMap<String,BinaryOperation>();
    private HashMap<String,UnaryOperation> unaryOperators  = new HashMap<String,UnaryOperation>();
    private HashMap<String,Constant> constants  = new HashMap<String,Constant>();
    private void initOperators(){
        binaryOperators.put("+",(op1, op2) -> op1+op2);
        binaryOperators.put("-",(op1, op2) -> op1-op2);
        binaryOperators.put("x",(op1, op2) -> op1*op2);
        binaryOperators.put("÷",(op1, op2) -> op1/op2);

        unaryOperators.put("%",op1 -> op1/100.0);
        unaryOperators.put("+/-",op1 -> -op1);
    }
    public void clearAll(){
        binaryOperationNotComplete = false;
        lastBinaryOperation = null;
        notCompleteBinaryOperation = null;
        tappedNewNumber = true;
        accumulator = 0.0;
    }
    public void setOperand(Double operand){
        accumulator = operand;
        tappedNewNumber = true;
    }

    public CalculatorModel(CalculatorController controller) {
        this.controller = controller;
        initOperators();

    }
    public void performOperaion(String symbol){
        if (tappedNewNumber&&binaryOperationNotComplete){
            accumulator = notCompleteBinaryOperation.evaluate(lastBinaryOperation,accumulator);
            binaryOperationNotComplete = false;
        }
        if (binaryOperators.get(symbol)!= null){
            lastBinaryOperation = accumulator;
            notCompleteBinaryOperation = binaryOperators.get(symbol);
            binaryOperationNotComplete = true;
            //处理
        }else if(unaryOperators.get(symbol)!= null){
            accumulator = unaryOperators.get(symbol).evaluate(accumulator);
        }else if(constants.get(symbol)!= null){
            accumulator = constants.get(symbol).constant();
        }
        tappedNewNumber = false;
    }

    /*
    *   函数式接口
    *   调用现场Lambda展开
    *
    * */
    @FunctionalInterface
    interface BinaryOperation<Op1,Op2>{
        double evaluate(Double op1,Double op2);
    }
    @FunctionalInterface
    interface UnaryOperation{
        double evaluate(Double op1);
    }
    @FunctionalInterface
    interface Constant{
        double constant();
    }
    @FunctionalInterface
    interface Equal{
        void equal();
    }
}
