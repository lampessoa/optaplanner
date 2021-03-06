************************************************************************
file with basedata            : mf43_.bas
initial value random generator: 457971615
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  32
horizon                       :  259
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     30      0       28       21       28
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           7   9  24
   3        3          3           5   6  15
   4        3          2           8  16
   5        3          3          17  20  24
   6        3          2          18  26
   7        3          2          16  19
   8        3          2          21  30
   9        3          2          10  15
  10        3          3          11  12  13
  11        3          2          14  19
  12        3          1          29
  13        3          2          14  27
  14        3          2          25  28
  15        3          2          19  28
  16        3          2          23  25
  17        3          1          30
  18        3          2          23  27
  19        3          1          22
  20        3          2          22  23
  21        3          2          22  24
  22        3          2          26  31
  23        3          3          28  29  31
  24        3          2          25  27
  25        3          1          26
  26        3          1          29
  27        3          1          31
  28        3          1          30
  29        3          1          32
  30        3          1          32
  31        3          1          32
  32        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       5    0    9    4
         2     1       0    7    5    4
         3     8       0    7    1    3
  3      1     3       8    0    8    6
         2     6       0    9    7    4
         3     8       0    2    4    2
  4      1     1       0    9    9   10
         2     4       0    7    7    9
         3     8       5    0    6    8
  5      1     3       7    0    9    9
         2     5       0    3    7    9
         3    10       0    3    3    6
  6      1     2       5    0   10    3
         2     3       0    6    7    3
         3     5       0    5    5    2
  7      1     3       0    5    9    2
         2     3       6    0    7    2
         3    10       3    0    2    2
  8      1     2       0    6    8   10
         2     4       4    0    8    9
         3    10       0    4    8    9
  9      1     7       6    0    9    9
         2     7       7    0    8    7
         3     9       5    0    7    6
 10      1     1       5    0    6    7
         2     4       3    0    6    5
         3     5       0    8    6    4
 11      1     3       0    7   10    6
         2     5       0    6    8    6
         3     9       0    5    7    3
 12      1     3       6    0    8    9
         2     5       6    0    7    7
         3    10       0    1    7    5
 13      1     4       7    0    5    6
         2     7       0    3    4    6
         3    10       0    2    3    3
 14      1     3       0    7    6    9
         2     5       5    0    6    9
         3     7       0    5    6    8
 15      1     8       0    5    5    9
         2     9       0    4    2    8
         3    10       6    0    2    7
 16      1     2       0    9    5    6
         2     4       7    0    5    4
         3     5       7    0    4    4
 17      1     1       0    7    8    3
         2     2       0    5    8    2
         3     5       0    4    7    2
 18      1     6      10    0    6    4
         2     8       9    0    5    4
         3    10       0    8    5    3
 19      1     2       7    0    3    9
         2     6       0    3    2    8
         3    10       7    0    1    7
 20      1     2       0    7    5    3
         2     6       0    6    3    2
         3    10       0    6    2    2
 21      1     2       9    0    9    5
         2     2       0    8   10    5
         3     9       9    0    7    4
 22      1     5       5    0    8    4
         2     8       3    0    6    4
         3    10       2    0    5    3
 23      1     1       6    0    7    7
         2     4       0    8    6    7
         3     7       0    6    3    7
 24      1     4       7    0    6    2
         2     6       0    2    6    2
         3    10       6    0    5    2
 25      1     5       2    0    6    5
         2    10       1    0    3    2
         3    10       0    5    1    4
 26      1     2       8    0    4    9
         2     6       5    0    4    9
         3    10       3    0    3    8
 27      1     2       0    9    7    9
         2     3       0    7    4    7
         3     9       0    3    3    5
 28      1     7       0    6    7    6
         2     9       2    0    3    5
         3     9       0    5    6    3
 29      1     3       0    7    8    7
         2     4       0    6    7    7
         3     9       7    0    7    6
 30      1     5       0    6    9    5
         2     5       0    7    7    6
         3     8       4    0    5    2
 31      1     4       4    0    8    7
         2     4       0    6    8    6
         3     9       4    0    7    4
 32      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   31   29  177  162
************************************************************************
