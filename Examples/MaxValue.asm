// Initialize the memory with sample values
STOREI 0, 45
STOREI 1, 32
STOREI 2, 89
STOREI 3, 17
STOREI 4, 56
STOREI 5, 23
STOREI 6, 78
STOREI 7, 12
STOREI 8, 92
STOREI 9, 37

// Start of finding maximum
MOV 1, 0
MOV 3, 0
ADDI 3, 3, 9

max_loop:
LOAD 2, 3
CMP 1, 2
JGE skip
MOV 1, 2

skip:
SUBI 3, 3, 1
CMPI 3, 0
JGE max_loop

STORE 0, 1
PF 0, 1 
