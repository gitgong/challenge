
Code Challenge Readme file


This repository contains Java code and accompanying resources for some of my job interview "code challenges". Code challenges are provided by companies as part of their candidate screening process.  



StringManipulation
-------------------

AnagramMultimap - uses a map of lists to store alphabetically sorted words matching particular alphabet sequences

Pangram - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. Uses a map to store true/false values if a letter is in the sentence or not.

PangramImproved - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. The sentence is placed in a set, compared to alphabet set. The difference between sets is found using removeAll()

StringComparatorSort - provides an example of Comparator implementation for String, to sort by String length (not alphabetically). This implementation is used when we are not overriding string object's implementation, but rather creating a special compare method of the implenting class, which then takes Strings as parameters.

StringDuplicates - two methods for determining whether or not a given sentence contains duplicate. One method simply loops through itself and compares itself to itself, with N2 performance. The other method puts the word array into a set, which will toss out any duplicates. The set is then compared to the original sentence word array.

ReverseString - shows two methods for reversing a string, one which uses a stack and the other a reversed loop. I show two variations of the reverse looping, one in which no extra char array object is created.

WordCount - uses a map to store word count frequency, obtains word list from file in classpath

