# Algorithm Programming Problems
Contains solutions for problems from Elements of Programming Interviews and Cracking the Coding Interview
books and other problems from other sources


## Common stuffs that one should know about

### Big-O Table
| # Name  |  T(n)              | Examples   |
| -----:     | :-----------------  | :------:|
| Constant    | T(n) = O(1)          | max(n, 100)   |
| Logarithm   | T(n) = O(logn)      | Binary search  |
| Linear      | T(n) = O(n)          | Smallest item  |
| Sublinear   | T(n) = O(nlogn)     | Mergesort  |
| Quadratic   | T(n) = O(n^2)        | Selection sort  |
| Cubic       | T(n) = O(n^3)        | ????   |
| Exponential | T(n) = O(c^n)        | All subsets   |
| Factorial   | T(n) = O(n!)         | Permutation   |

#### Order of growth
1 << log(n) << n << nlogn << n^2 << n3 << 2^n << n!

### Permutations, subsets and substrings
* There are n! permutations of an n-element set
* There are 2^n subsets of an n-element set (because each element will be in or not in the set)
* There are n(n+1)/2 contiguous substrings in a string of length n
* There are n(n-1)/2 possible subtrees in a binary search tree

### Subsets
* The number of subsets of size k of a set of size n is n!/k!*(n-k)!
* This is also known as combinations


### Common steps for solving a technical questions
* ask questions to clarify ambiguity
* try out a small example(s) to explore possible algorithms
* identify needed data structures
* design an algorithm
* write pseudo code first (let interview know)
* write code at moderate pace
* stub out secondary concerns (swapping two numbers)
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

### Characters
| Letters              |  Integer Value   |
| :-----               | :------:         |
| A-Z                  |  65-90   |
| a-z                  |  97-122   |



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
| Internet CA<->NY    | 40,000,000   ns |  40,000 us | 40 ms    |
| Internet CA<->Netherlands    | 150,000,000   ns |  150,000 us | 150 ms    |                              |

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
### Working with bits
* x & ~(x - 1) isolates the lowest bit (0100100 ==> 0000100)
  * Right propagate right most bit
* x & (x - 1) changes the rightmost 1 bit to 0 (0100100 ==> 0100000)
  * Use to count # of bits, detect if n is power of 2
* Integer.parseInt("01100110",2); // to parse an binary string into an integer
* XOR has the property of being associative (grouping) and commutative (ordering)


### Working with string
* Permutation - rearrangement of letters in specific order.
* Anagram - rearranging the letters of a word to produce a new word. i.e listen and silent
* Palindrome - a word which read the same backward or forward. i.e civic

#### Generate substrings
```java
public static List<String> generateSubStrings(String str) {
    List<String> result = new ArrayList<>();

    if (str == null) {
        return result;
    }

    // two for loops to build all the possible substrings
    for (int i = 0; i < str.length(); i++) {
        for (int j = i; j < str.length(); j++) {
            // now print out the substring from i to j
            String subStr = "";
            for (int k = i; k <= j; k++) {
                subStr += str.charAt(k);
            }
            result.add(subStr);
        }
    }
    return result;
}
```
#### Generate sub-sequences
```java
public static List<String> generateSubsequences(String str) {
    List<String> result = new ArrayList<>();
    if (str == null || str.length() == 0) {
        return result;
    }

    double numSequences = Math.pow(2, str.length());

    for (int i = 0; i < numSequences; i++) {
        String subSeqStr = "";
        int index = 0;
        int temp = i;

        while (temp > 0) {
            if ((temp & 1) == 1) {
                subSeqStr += str.charAt(index);
            }
            index++;
            temp = temp >> 1;
        }
        result.add(subSeqStr);
    }
    return  result;
}
```

```java
  // using recursion
  private static void recursionApproach(String remaining, String soFar) {
      if (remaining.isEmpty()) {
          collector.add(soFar);
          return;
      }

      // include first character
      recursionApproach(remaining.substring(1), soFar + remaining.charAt(0));

      // don't include first character
      recursionApproach(remaining.substring(1), soFar);
      }
```

### Working with prime numbers
* Every number can be decomposed into a product of primes
* All non-prime numbers are divisible by a primer number
* All prime numbers are odd

### Tree Data Structures
* Heap is a binary tree where every node holds a value that is at least as large as the values in all children.

#### Priority queue
* Top or max n items requires min-priority queue because we want to replace the smallest element with a bigger element
* Bottom or min n items requires a max priority queue because we want to replace the largest element with a smaller element

### Binary Tree Terminologies
* Level - Defined as 1+ the number connections between a node and the root. Root starts at level 1, meaning the level starts at value of 1 and it goes down from top to bottom.  Level = depth + 1
* Height - number of edges from a node to the deepest leaf. Height of furthest eaf node is 0.  Goes from bottom (0) to top (n).  Height of a tree is the height of the root.
* Depth - number of edges from root to node.  Goes from top to bottom and starts at 0.
* Height and depth should move inversely.

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
   preOrder(node.left);
   preOrder(node.right);
}
```

#### Inorder Traversal
```java
public static void inorder(Node node) {
   if (node == null) return

   inorder(node.left);

   System.out.println(node.value);

   inorder(node.right);
}
```

#### Postorder Traversal
```java
public static void postOrder(Node node) {
   if (node == null) return

   postOrder(node.left);

   postOrder(node.right);

   System.out.println(node.value);
}
```
#### BFS
```java
public static void bfs(Node node) {
    Queue<Node> queue = new LinkedList();
    queue.add(node);
    
    while (!queue.isEmpty()) {
        Node tmpNode = queue.poll();
        
        // perform some processing
        if (tmpNode.left != null) {
            queue.add(tmpNode.next);
        }
        
        if (tmpNode.right != null) {
            queue.add(tmpNode.right);
        }
    }
}

```

### General pattern for solving graph related problems:

#### Approaches
* Classify whether graph is directed or undirected
* Whether each edge has different cost or weight
* BFS -> for shortest path
* DFS -> exhaust search

#### Tracking path with BFS or DFS
* For BFS, use the parent map of (child -> parent) to maintain the path
  such that we can build the path at the end.
* For DFS, use a list to track the path, but make sure to back-track
  or remove the last one when pop up to parent node
  
#### Preventing visiting the node or path
* Maintain a visited set  

#### Data structure for a graph - adjacency list

```java
class Graph {
    private List<Node> vertices;
    private Map<Node, List<Edge>> adjacenyList;
}

class Edge {
    private Node sourceNode;
    private Node desetNode;
    private int weight;
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

### Recursive Backtracking

#### pseudo code

```java
bool solve(conf) {
    if (no more choices) {  // base case
        return; 
    }
    
    for (all available choices) {
        make a choice;
        
        ok = solve(conf with selected choice);
        if (ok) {
            return true;
        }
        
        unmake choice
    }
    
    return false;
}

```

### Questions to ask when working with numbers
* negative numbers, sorted, duplicates

### Binary Search
```java

    public static int binarySearch(int[] input, int val) {
        int left = 0;
        int right = input.length - 1;

        while (left <= right) {
            // there are 2 ways to calculate the mid-index
            // the first one is more safe because no overflow
            // the second may run into overflow
            int mid = left + (right - left) / 2;
            //int mid = (left + right)/2;

            if (input[mid] == val) {
                return mid;
            }

            if (input[mid] < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


```
