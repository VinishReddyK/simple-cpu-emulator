# Simple CPU Emulator

This project is a simple CPU emulator that reads assembly code from a file, simulates the execution of instructions, and provides various outputs. It allows for command-line execution, where you specify the path to an assembly file containing the program instructions.

## Features

- **Instruction Simulation**: Executes basic assembly instructions.

## Prerequisites

- **Java 11** or higher installed on your system.

## Getting Started

### Running the Program

To run the program, use the `java -jar` command and pass the assembly file as an argument.

```bash
cd Examples
java -jar mips.jar <path_to_assembly_file>
```

For example:

```bash
java -jar mips.jar BubbleSort.asm
```

### Command-Line Argument

- **File Name**: Provide the path to an assembly file containing the program instructions as the first command-line argument.

## Usage

The program will read the specified assembly file, parse the instructions, and simulate their execution. Example assembly files (`BubbleSort.asm`, `MaxValue.asm`, `SumOfTheArray.asm`) are located in the `Examples/` directory for testing.

## Structure

- `Examples/`: Contains the exported JAR file (`mips.jar`) and example assembly files.

## License

This project is licensed under the MIT License.

---

### Additional Notes

1. If you encounter errors, ensure that the file path is correct.
2. For advanced functionality, explore the RBAC settings and shift management features in the code.
