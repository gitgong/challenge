Information Theory and Huffman Coding

This is from coding expiriments in my course on Complexity at Santa Fe institute. 

The basic idea in the Huffman code is that ordinarily characters are represented as 16 bit or 8 bit, but you can reduce that further by generating a custom bit encoding, with higher frequency characters represented by much shorter codes. The bit encoding is generated in the binary trees, and map structures store the codes and are used in the decoding. I also calculate the information content, and the program outputs analytics.

The program uses many data-structures including binary tree and priorityqueue, as well as sets and maps.

Resources

http://en.wikipedia.org/wiki/Entropy_(information_theory)
http://en.wikipedia.org/wiki/Huffman_coding
https://www.cs.duke.edu/csed/poop/huff/info/
http://rosettacode.org/wiki/Huffman_coding#Java
http://www.complexityexplorer.org