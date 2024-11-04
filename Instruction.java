public class Instruction {
    private String operation;
    private String[] operands;

    public Instruction(String operation, String[] operands) {
        this.operation = operation;
        this.operands = operands;
    }

    public String getOperation() {
        return operation;
    }

    public String[] getOperands() {
        return operands;
    }
    public static void printInstruction(Instruction instruction) {
        StringBuilder output = new StringBuilder();
        output.append(instruction.getOperation());

        String[] operands = instruction.getOperands();
        if (operands != null && operands.length > 0) {
            output.append(" ");
            output.append(String.join(", ", operands));
        }

        System.out.println(output.toString());
    }
}
