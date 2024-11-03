import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        List<Instruction> program = new ArrayList<>();

        String[] instructions = readAssemblyFile("BubbleSort.asm").toArray(new String[0]);

        Map<String, Integer> labels = extractLabels(instructions);
        parseInstructions(instructions, program, labels);
        
        // Initialize CPU emulator with 32 registers and 1024 memory size
        CPUEmulator cpu = new CPUEmulator(1024, 32, program);
        cpu.run();
    }
    public static List<String> readAssemblyFile(String filename) {
        List<String> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("//")) {
                    instructions.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
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
