// Initialize the memory
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

//Start of bubble sort
MOV 5, 0
ADDI 5, 5, 2

outer_loop:
MOV 4, 0
ADDI 4, 4, 9

inner_loop:
SUBI 6, 4, 1
LOAD 1, 4
LOAD 2, 6
SUBI 4, 4, 1
CMP 2, 1
JLE inner_loop
ADDI 4, 4, 1
STORE 6, 1
STORE 4, 2
SUBI 4, 4, 1
CMP 4, 5
JGE inner_loop
ADDI 5, 5, 1
CMPI 5, 10
JLT outer_loop

PF 0, 10