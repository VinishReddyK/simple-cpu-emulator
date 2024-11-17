public class InstructionSet {
    private Memory memory;
    private RegisterFile registers;
    private int flagRegister;

    public InstructionSet(Memory memory, RegisterFile registers) {
        this.memory = memory;
        this.registers = registers;
        this.flagRegister = 0;
    }

    public Boolean execute(Instruction instruction) {
        String operation = instruction.getOperation();
        String[] rawOperands = instruction.getOperands();
        int[] parsedOperands = new int[rawOperands.length];
        int[] operands = new int[]{0, 0, 0};

        for (int i = 0; i < rawOperands.length; i++) {
            String str = rawOperands[i];
            if (str.startsWith("$")) {
                int number = Integer.parseInt(str.substring(1));
                parsedOperands[i] = number;
                operands[i] = registers.get(number);
            } else {
                operands[i] = Integer.parseInt(str);
                parsedOperands[i] = Integer.parseInt(str);
            }
        }

        // Instruction.printInstruction(instruction);
        // System.out.println(operands[0] + " " + operands[1] + " " + operands[2]);

        switch (operation) {
            case "ADD":
                registers.set(parsedOperands[0], operands[1] + operands[2]);
                break;
            case "ADDI":
                registers.set(parsedOperands[0], operands[1] + operands[2]);
                break;
            case "SUB":
                registers.set(parsedOperands[0], operands[1] - operands[2]);
                break;
            case "SUBI":
                registers.set(parsedOperands[0], operands[1] - operands[2]);
                break;
            case "MUL":
                registers.set(parsedOperands[0], operands[1] * operands[2]);
                break;
            case "MULI":
                registers.set(parsedOperands[0], operands[1] * operands[2]);
                break;
            case "DIV":
                registers.set(parsedOperands[0], operands[1] / operands[2]);
                break;
            case "DIVI":
                registers.set(parsedOperands[0], operands[1] / operands[2]);
                break;
            case "AND":
                registers.set(parsedOperands[0], operands[1] & operands[2]);
                break;
            case "ANDI":
                registers.set(parsedOperands[0], operands[1] & operands[2]);
                break;
            case "OR":
                registers.set(parsedOperands[0], operands[1] | operands[2]);
                break;
            case "ORI":
                registers.set(parsedOperands[0], operands[1] | operands[2]);
                break;
            case "XOR":
                registers.set(parsedOperands[0], operands[1] ^ operands[2]);
                break;
            case "XORI":
                registers.set(parsedOperands[0], operands[1] ^ operands[2]);
                break;
            case "NOP":
                // Do nothing
                break;
            case "SLL":
                registers.set(parsedOperands[0], operands[1] << operands[2]);
                break;
            case "SRL":
                registers.set(parsedOperands[0], operands[1] >> operands[2]);
                break;
            case "LOAD":
                registers.set(parsedOperands[0], memory.load(operands[1]));
                break;
            case "LOADI":
                registers.set(parsedOperands[0], memory.load(operands[1]));
                break;
            case "STORE":
                memory.store(operands[0], operands[1]);
                break;
            case "STOREI":
                memory.store(operands[0], operands[1]);
                break;
            case "MOV":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1]);
                break;
            case "MOVI":
                registers.set(Integer.parseInt(rawOperands[0].substring(1)), operands[1]);
                break;
            case "CMP":
                compare(operands[0], operands[1]);
                break;
            case "BEQ": 
                compare(operands[0], operands[1]);
                return flagRegister == 0;
            case "BNE": 
                compare(operands[0], operands[1]);
                return flagRegister != 0;
            case "BLT": 
                compare(operands[0], operands[1]);
                return flagRegister < 0;
            case "BLE": 
                compare(operands[0], operands[1]);
                return flagRegister <= 0;
            case "BGT": 
                compare(operands[0], operands[1]);
                return flagRegister > 0;
            case "BGE": 
                compare(operands[0], operands[1]);
                return flagRegister >= 0;
            // Print Memory
            case "PMEM":
                memory.printFirst(operands[0], operands[1]);
                break;
            // Print Register
            case "PR":
                registers.printRegs(operands[0], operands[1]);
                break;
            default:
                throw new IllegalArgumentException("Unknown instruction: " + operation);
        }
        return true;
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
