import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Instruction> program = new ArrayList<>();
        String[] instructions = {
            "STOREI 0, 45",
            "STOREI 1, 32",
            "STOREI 2, 89",
            "STOREI 3, 17",
            "STOREI 4, 56",
            "STOREI 5, 23",
            "STOREI 6, 78",
            "STOREI 7, 12",
            "STOREI 8, 92",
            "STOREI 9, 37",
            
            "MOV 5, 0",
            "ADDI 5, 5, 2",

            "outer_loop:",
            "MOV 4, 0",
            "ADDI 4, 4, 9",
            
            "inner_loop:",
            "SUBI 6, 4, 1",
            "LOAD 1, 4",
            "LOAD 2, 6",
            "SUBI 4, 4, 1",
            "CMP 2, 1",
            "JLE inner_loop",
            "ADDI 4, 4, 1",
            "STORE 6, 1",
            "STORE 4, 2",
            "SUBI 4, 4, 1",
            "CMP 4, 5",
            "JGE inner_loop",
            "ADDI 5, 5, 1",
            "CMPI 5, 10",
            "JLT outer_loop",

            "PF 0, 10"
        };

        Map<String, Integer> labels = extractLabels(instructions);
        parseInstructions(instructions, program, labels);
        
        // Initialize CPU emulator with 32 registers and 1024 memory size
        CPUEmulator cpu = new CPUEmulator(1024, 32, program);
        cpu.run();
    }
    public static Map<String, Integer> extractLabels(String[] instructionStrings) {
        Map<String, Integer> labels = new HashMap<>();
        int position = 0;

        for (String line : instructionStrings) {
            line = line.trim();
            if (line.endsWith(":")) {
                String labelName = line.substring(0, line.length() - 1);
                labels.put(labelName, position - labels.size());
            }
            position++;
        }
        return labels;
    }
    public static void parseInstructions(String[] instructionStrings, List<Instruction> program, Map<String, Integer> labels) {
        for (String line : instructionStrings) {
            line = line.trim();
            if (line.endsWith(":")) {
                continue;
            }

            String[] parts = line.split(" ",2);
            String operation = parts[0];
            String[] operandStrings = parts[1].split(",");

            int[] operands = new int[operandStrings.length];
            for (int i = 0; i < operandStrings.length; i++) {
                String operand = operandStrings[i].trim();
                if (labels.containsKey(operand)) {
                    operands[i] = labels.get(operand);
                } else {
                    operands[i] = Integer.parseInt(operand);
                }
            }
            program.add(new Instruction(operation, operands));
        }
    }
}
