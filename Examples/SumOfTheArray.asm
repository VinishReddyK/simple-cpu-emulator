; Initialize the memory with sample values
STORE 0, 45
STORE 1, 32
STORE 2, 89
STORE 3, 17
STORE 4, 56
STORE 5, 23
STORE 6, 78
STORE 7, 12
STORE 8, 92
STORE 9, 37

; Start of summing array
MOV $2, $0
MOV $3, $0
ADD $3, $3, 9

sum_loop:
LOAD $1, $3
ADD $2, $2, $1
SUB $3, $3, 1
CMP $3, $0
JGE sum_loop

STORE 10, $2
PMEM 10, 11