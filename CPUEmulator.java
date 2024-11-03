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

        if (operation.equals("JMP") || operation.equals("JNE") || operation.equals("JEQ") || operation.equals("JLT") || operation.equals("JLE") || operation.equals("JGE") || operation.equals("JGT")) {
            handleControlFlow(instruction);
        } else {
            instructionSet.execute(instruction);
            programCounter++;
        }
    }

    private void handleControlFlow(Instruction instruction) {
        int[] operands = instruction.getOperands();

        switch (instruction.getOperation()) {
            case "JMP":
                programCounter = operands[0];
                break;
            case "JNE":
                if (instructionSet.getFlagRegister() != 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            case "JEQ":
                if (instructionSet.getFlagRegister() == 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            case "JLT":
                if (instructionSet.getFlagRegister() < 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            case "JLE":
                if (instructionSet.getFlagRegister() <= 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            case "JGT":
                if (instructionSet.getFlagRegister() > 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            case "JGE":
                if (instructionSet.getFlagRegister() >= 0) {
                    programCounter = operands[0];
                } else {
                    programCounter++;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown control flow instruction: " + instruction.getOperation());
        }
    }
}
