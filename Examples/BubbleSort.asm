; Initialize the memory
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

; Start of bubble sort
MOV $5, $0
ADD $5, $5, 2

outer_loop:
MOV $4, $0
ADD $4, $4, 9

inner_loop:
SUB $6, $4, 1
LOAD $1, $4
LOAD $2, $6
SUB $4, $4, 1
CMP $2, $1
JLE inner_loop
ADD $4, $4, 1
STORE $6, $1
STORE $4, $2
SUB $4, $4, 1
CMP $4, $5
JGE inner_loop
ADD $5, $5, 1
CMP $5, 10
JLT outer_loop

PMEM 0, 10