public class InstructionSet {
    private Memory memory;
    private RegisterFile registers;
    private int flagRegister;

    public InstructionSet(Memory memory, RegisterFile registers) {
        this.memory = memory;
        this.registers = registers;
        this.flagRegister = 0;
    }

    public void execute(Instruction instruction) {
        String operation = instruction.getOperation();
        String[] rawOperands = instruction.getOperands();
        int[] operands = new int[]{0, 0, 0};

        for (int i = 0; i < rawOperands.length; i++) {
            String str = rawOperands[i];
            if (str.startsWith("$")) {
                int number = Integer.parseInt(str.substring(1));
                operands[i] = registers.get(number);
            } else {
                operands[i] = Integer.parseInt(str);
            }
        }

        // Instruction.printInstruction(instruction);
        // System.out.println(operands[0] + " " + operands[1] + " " + operands[2]);

        switch (operation) {
            case "ADD":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1] + operands[2]);
                break;
            case "SUB":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1] - operands[2]);
                break;
            case "MUL":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1] * operands[2]);
                break;
            case "LOAD":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), memory.load(operands[1]));
                break;
            case "STORE":
                memory.store(operands[0], operands[1]);
                break;
            case "CMP":
                compare(operands[0], operands[1]);
                break;
            case "MOV":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1]);
                break;
            case "PMEM":
                memory.printFirst(operands[0], operands[1]);
                break;
            case "PR":
                registers.printRegs(operands[0], operands[1]);
                break;
            default:
                throw new IllegalArgumentException("Unknown instruction: " + operation);
        }
    }

    private void compare(int reg1, int reg2) {
        int result = reg1 - reg2;
        if(result > 0) flagRegister = 1;
        else if(result < 0) flagRegister = -1;
        else flagRegister = 0;
    }

    public int getFlagRegister() {
        return flagRegister;
    }
}
