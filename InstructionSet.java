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
        int[] operands = instruction.getOperands();

        switch (operation) {
            case "ADD":
                registers.set(operands[0], registers.get(operands[1]) + registers.get(operands[2]));
                break;
            case "ADDI":
                registers.set(operands[0], registers.get(operands[1]) + operands[2]);
                break;
            case "SUB":
                registers.set(operands[0], registers.get(operands[1]) - registers.get(operands[2]));
                break;
            case "SUBI":
                registers.set(operands[0], registers.get(operands[1]) - operands[2]);
                break;
            case "MUL":
                registers.set(operands[0], registers.get(operands[1]) * registers.get(operands[2]));
                break;
            case "MULI":
                registers.set(operands[0], registers.get(operands[1]) * operands[2]);
                break;
            case "LOAD":
                registers.set(operands[0], memory.load(registers.get(operands[1])));
                break;
            case "STORE":
                memory.store(registers.get(operands[0]), registers.get(operands[1]));
                break;
            case "STOREI":
                memory.store(operands[0], operands[1]);
                break;
            case "CMP":
                compare(registers.get(operands[0]), registers.get(operands[1]));
                break;
            case "CMPI":
                compare(registers.get(operands[0]), operands[1]);
                break;
            case "MOV":
                registers.set(operands[0], registers.get(operands[1]));
                break;
            case "PF":
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
