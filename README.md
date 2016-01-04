# Algorithm Programming Problems
Contains solutions for problems from Elements of Programming Interviews and Cracking the Coding Interview
books and other problems from other sources


## Common stuffs that one should know about

### Permutations, subsets and substrings
* There are n! permutations of an n-element set
* There are 2^n subsets of an n-element set
* There are n(n-1)/2 contiguous substrings in a string of length n (n^2)
* There are n(n-1)/2 possible subtrees in a binary search tree (n^2)


### Common steps for solving a technical questions
* ask questions to clarify ambiguity
* design an algorithm
* write pseudo code first (let interview know)
* write code at moderate pace
* test code and carefully fix mistakes


### Binary values
| # bits  |  Value              | Bytes   |   
| :-----: | :-----------------: | :------:|
| 7       | 128                 |         |
| 8       | 256                 |         |
| 10      | 1024                |  1K     |
| 16      | 65,536              |  64K    |
| 20      | 1,048,536           |  1MB    |
| 30      | 1,073,741,824       |  1GB    |
| 32      | 4,294,967,296       |  4GB    |
| 40      | 1,099, 511,627,776  |  1TB    |


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