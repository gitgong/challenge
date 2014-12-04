Challenge Code Readme file
Author: Blake Rogers


This repository contains mostly Java code solutions for interesting programming challenges, code snippets and samples such as API usage. Feel free to reuse any part of this code. 


Algorithms
----------
An algorithm to find the closest pairs in a number list. Performance improved by half from N^2 to (N^2)/2.


LostRover
----------
RoverController - based upon text string input, models a navigational controller for a mars rover, which knows its own location and direction on a simple grid.


ParticleChamber
----------
Two programs show different strategies for caching move history in a model particle chamber. see particle_readme for further information. illustrates caching strategy's impact on performance.


TCPMsgServer
----------
Consists of a Server which is tested by the Clients sending it messages. See the project readme for more information.


MailClient
----------
This program shows a simple implementation / usage of Javamail API circa 2001. Requires Javamail support libraries. See readme for details.


Complexity
----------
The bit encoding is generated in the binary trees, and map structures store the codes and are used in the decoding. I also calculate the information content, and the program outputs analytics.


StringManipulation
-------------------

AnagramMultimap - uses a map of lists to store alphabetically sorted words matching particular alphabet sequences

Pangram - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. Uses a map to store true/false values if a letter is in the sentence or not.

PangramImproved - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. The sentence is placed in a set, compared to alphabet set. The difference between sets is found using removeAll()

StringComparatorSort - provides an example of Comparator implementation for String, to sort by String length (not alphabetically). This implementation is used when we are not overriding string object's implementation, but rather creating a special compare method of the implenting class, which then takes Strings as parameters.

StringDuplicates - two methods for determining whether or not a given sentence contains duplicate. One method simply loops through itself and compares itself to itself, with N2 performance. The other method puts the word array into a set, which will toss out any duplicates. The set is then compared to the original sentence word array.

ReverseString - shows two methods for reversing a string, one which uses a stack and the other a reversed loop. I show two variations of the reverse looping, one in which no extra char array object is created.

WordCount - uses a map to store word count frequency, obtains word list from file in classpath



