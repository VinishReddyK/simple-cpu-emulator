public class Instruction {
    private String operation;
    private int[] operands;

    public Instruction(String operation, int... operands) {
        this.operation = operation;
        this.operands = operands;
    }

    public String getOperation() {
        return operation;
    }

    public int[] getOperands() {
        return operands;
    }
}
