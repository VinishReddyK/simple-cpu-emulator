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

// Start of summing array
MOV 2, 0
MOV 3, 0
ADDI 3, 3, 9

sum_loop:
LOAD 1, 3
ADD 2, 2, 1
SUBI 3, 3, 1
CMP 3, 0
JGE sum_loop

STORE 0, 2
PF 0, 1