# Algorithm Programming Problems
Contains solutions for problems from Elements of Programming Interviews and Cracking the Coding Interview
books and other problems from other sources


## Common stuffs that one should know about

### Permutations, subsets and substrings
* There are n! permutations of an n-element set
* There are 2^n subsets of an n-element set
* There are n(n-1)/2 contiguous substrings in a string of length n (n^2)
* There are n(n-1)/2 possible subtrees in a binary search tree (n^2)

### Subsets
* The number of subsets of size k of a set of size n is n!/k!*(n-k)!
* This is also known as combinations

### Permutations
* The number of permutation of a set of size n is n!


### Common steps for solving a technical questions
* ask questions to clarify ambiguity
* design an algorithm
* write pseudo code first (let interview know)
* write code at moderate pace
* test code and carefully fix mistakes


### Binary values
| # bits  |  Value              | Bytes   |
| :-----: | :-----------------  | :------:|
| 7       | 128                 |         |
| 8       | 256                 |         |
| 10      | 1024                |  1K     |
| 16      | 65,536              |  64K    |
| 20      | 1,048,536           |  1MB    |
| 30      | 1,073,741,824       |  1GB    |
| 32      | 4,294,967,296       |  4GB    |
| 40      | 1,099,511,627,776   |  1TB    |

### Bits & Bytes
| Bytes                  |  Name   |  Name       |
| :-----                 | :------:| :------:    |
| 1,000                  | 1KB     | Thousand    |
| 1,000,000              | 1MB     | Million     |
| 1,000,000,000          | 1GB     | Billion     |
| 1,000,000,000,000      | 1TB     | Trillion    |
| 1,000,000,000,000,000  | 1PB     | Quadrillion |


### Latency Numbers Every Software Engineers Should Know
| Category                           | Time (ns)        | Time (us)   | Time (ms) | Relative                     |
| :------                            |         -------: | --------:   | -------:  | :-------                     |
| L1 cache reference                           0.5 ns   |             |           |                              |
| Branch mispredict                  |           5   ns |             |           |                              |
| L2 cache reference                 |           7   ns |             |           |  14x L1 cache                |
| Mutex lock/unlock                  |          25   ns |             |           |                              |
| Main memory reference              |         100   ns |             |           |  20x L2 cache, 200x L1 cache |
| Compress 1K bytes with Zippy       |       3,000   ns |        3 us |           |                              |
| Send 1K bytes over 1 Gbps network  |      10,000   ns |       10 us |           |                              |
| Read 4K randomly from SSD*         |     150,000   ns |      150 us |           |  ~1GB/sec SSD                |
| Read 1 MB sequentially from memory |     250,000   ns |      250 us |           |                              |
| Round trip within same datacenter  |     500,000   ns |      500 us |           |                              |
| Read 1 MB sequentially from SSD*   |   1,000,000   ns |    1,000 us |   1 ms    |  ~1GB/sec SSD, 4X memory     |
| Disk seek                          |  10,000,000   ns |   10,000 us |  10 ms    |  20x datacenter roundtrip    |
| Read 1 MB sequentially from disk   |  20,000,000   ns |   20,000 us |  20 ms    |  80x memory, 20X SSD         |
| Send packet CA->Netherlands->CA    | 150,000,000   ns |  150,000 us | 150 ms    |                              |

Notes
-----
|  Name        | Time                                           |  
| -----------: | :---------------                               |
| nanosecond   | 1 ns = 10^-9 seconds                           |
| microsecond  | 1 us = 10^-6 seconds = 1,000 ns                |
| millisecond  | 1 ms = 10^-3 seconds = 1,000 us = 1,000,000 ns |

##### Credit: https://gist.github.com/jboner/2841832

### Common utility to know
```java
public static String toBase(int n, int base) {
   if (base < 2 || base > 10) {
       return "-1";
   }

   String result = "";

   while (n > 0) {
       result = (n % base) + result;
       n = n / base;
   }
   return result;
}
```

### Working with string
* Permutation - rearrangement of letters in specific order.
* Anagram - rearranging the letters of a word to produce a new word. i.e listen and silent
* Palindrome - a word which read the same backward or forward. i.e civic

### Working with prime numbers
* Every number can be decomposed into a product of primes
* All non-prime numbers are divisible by a primer number
* All prime numbers are odd

### Tree Data Structures
* Heap is a binary tree where every node holds a value that is at least as large as the values in all children.

### Binary Tree Properties
| Property        |  Value           |
| :-----:         | :--------------: |
| Branches        | N -1             |
| # of nodes      | N = 2^(H+1) -1   |
| Height          | log(N+1) - 1     |
| # leaf nodes    | 2^H              |

#### In a perfect binary tree, almost exactly half of the nodes are leaves and almost exactly half are internal nodes.

### Tree Traversal
#### Preorder Traversal
```java
public static void preOrder(Node node) {
   if (node == null) return

   System.out.println(node.value);
   if (node.left != null) preOrder(node.left);

   if (node.right != null) preOrder(node.right);
}
```

#### Inorder Traversal
```java
public static void inorder(Node node) {
   if (node == null) return

   if (node.left != null) preOrder(node.left);

   System.out.println(node.value);

   if (node.right != null) preOrder(node.right);
}
```

#### Postorder Traversal
```java
public static void postOrder(Node node) {
   if (node == null) return

   if (node.left != null) preOrder(node.left);

   if (node.right != null) preOrder(node.right);

   System.out.println(node.value);
}
```


### General pattern for solving tree related problems:
* Identify base case and return appropriate value
* compute the left side
** Take appropriate action based on result: either stop recursion or continue
* compute the right side
** Take appropriate action based on result: either stop recursion or continue
* Now take action based on the result of both left and right side

### Dynamic Programming - three steps involved in solving a DP problem
* Formulate the answer as a recurrence relation or recursive algorithm
* Ensure the number of different parameter values taken on by recurrence is bounded
* Specify the order of evaluation for the recurrence so partial results are available when needed
