public class RegisterFile {
    private int[] registers;

    public RegisterFile(int numRegisters) {
        registers = new int[numRegisters];
        registers[0] = 0;
    }

    public int get(int index) {
        return registers[index];
    }

    public void set(int index, int value) {
        registers[index] = value;
    }
    public void printRegs(int start, int end) {
        for(int i = start; i < end; i ++){
            System.out.println("Reg " + i + " "+ registers[i]);
        }
    }
}
