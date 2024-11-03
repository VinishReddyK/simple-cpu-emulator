import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Instruction> program = new ArrayList<>();

        program.add(new Instruction("STOREI", 0, 45));
        program.add(new Instruction("STOREI", 1, 32));
        program.add(new Instruction("STOREI", 2, 89));
        program.add(new Instruction("STOREI", 3, 17));
        program.add(new Instruction("STOREI", 4, 56));
        program.add(new Instruction("STOREI", 5, 23));
        program.add(new Instruction("STOREI", 6, 78));
        program.add(new Instruction("STOREI", 7, 12));
        program.add(new Instruction("STOREI", 8, 92));
        program.add(new Instruction("STOREI", 9, 37));

        program.add(new Instruction("MOV", 5, 0));
        program.add(new Instruction("ADDI", 5, 5, 2));

        // label: outer_loop
        int outerLoopStart = program.size();

        program.add(new Instruction("MOV", 4, 0));
        program.add(new Instruction("ADDI", 4, 4, 9));
        
        // label: inner_loop
        int innerLoopStart = program.size();

        program.add(new Instruction("SUBI", 6, 4, 1));
        program.add(new Instruction("LOAD", 1, 4));
        program.add(new Instruction("LOAD", 2, 6));
        program.add(new Instruction("SUBI", 4, 4, 1));
        program.add(new Instruction("CMP", 2, 1));
        program.add(new Instruction("JLE", innerLoopStart));
        program.add(new Instruction("ADDI", 4, 4, 1));
        program.add(new Instruction("STORE", 6, 1));
        program.add(new Instruction("STORE", 4, 2));
        program.add(new Instruction("SUBI", 4, 4, 1));
        program.add(new Instruction("CMP", 4, 5));
        program.add(new Instruction("JGE", innerLoopStart));
        program.add(new Instruction("ADDI", 5, 5, 1));
        program.add(new Instruction("CMPI", 5, 10));
        program.add(new Instruction("JLT", outerLoopStart));

        program.add(new Instruction("PF", 0, 10));

        // Initialize CPU emulator with 32 registers and 1024 memory size
        CPUEmulator cpu = new CPUEmulator(1024, 32, program);
        cpu.run();
    }
}
