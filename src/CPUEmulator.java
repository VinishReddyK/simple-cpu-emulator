import java.util.*;

public class CPUEmulator {
    private Memory memory;
    private RegisterFile registers;
    private InstructionSet instructionSet;
    private List<Instruction> program;
    private int programCounter;

    public CPUEmulator(int memorySize, int numRegisters, List<Instruction> program) {
        this.memory = new Memory(memorySize);
        this.registers = new RegisterFile(numRegisters);
        this.instructionSet = new InstructionSet(memory, registers);
        this.program = program;
        this.programCounter = 0;
    }

    public void run() {
        while (programCounter < program.size()) {
            Instruction instruction = program.get(programCounter);
            executeInstruction(instruction);
        }
    }

    private void executeInstruction(Instruction instruction) {
        String operation = instruction.getOperation();

        if (operation.matches("J|BNE|BEQ|BLT|BLE|BGE|BGT|JE|JNE|JL|JLE|JG|JGE")) {
            handleControlFlow(instruction);
        } else {
            instructionSet.execute(instruction);
            programCounter++;
        }
    }
    private boolean checkCondition(int flag, String operation) {
        switch (operation) {
            case "JE": return flag == 0;
            case "JNE": return flag != 0;
            case "JL": return flag < 0;
            case "JLE": return flag <= 0;
            case "JG": return flag > 0;
            case "JGE": return flag >= 0;
            default: throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
    private void handleControlFlow(Instruction instruction) {
        String operation = instruction.getOperation();
        String[] operands = instruction.getOperands();
        int jumpTo = 0;
        switch (operation) {
            case "BEQ": case "BNE": case "BLT": case "BLE": case "BGT": case "BGE":
                jumpTo = Integer.parseInt(operands[2]);
                if (instructionSet.execute(instruction)) {
                    programCounter = jumpTo;
                } else
                programCounter++;
            break;
            case "J":
                jumpTo = Integer.parseInt(operands[0]);
                programCounter = jumpTo;
            break;
            default:
                jumpTo = Integer.parseInt(operands[0]);
                if (checkCondition(instructionSet.getFlagRegister(), operation)) 
                    programCounter = jumpTo;
                else 
                    programCounter++;
        }
    }
}
