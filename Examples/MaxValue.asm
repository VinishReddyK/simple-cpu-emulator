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

; Start of finding maximum
MOV $1, $0
MOV $3, $0
ADD $3, $3, 9

max_loop:
LOAD $2, $3
CMP $1, $2
JGE skip
MOV $1, $2

skip:
SUB $3, $3, 1
CMP $3, $0
JGE max_loop
STORE 10, $1

PMEM 10, 11 
