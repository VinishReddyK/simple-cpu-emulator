public class Memory {
    private int[] memory;

    public Memory(int size) {
        memory = new int[size];
    }

    public int load(int address) {
        return memory[address];
    }

    public void store(int address, int value) {
        memory[address] = value;
    }
    public void printFirst(int start, int end) {
        for(int i = start; i < end; i ++){
            System.out.println(memory[i]);
        }
    }
}
