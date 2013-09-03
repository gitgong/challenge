
Code Challenge Readme file
Author: Blake Rogers


This repository contains Java code and accompanying resources for solutions to some of my job interview "code challenges". Code challenges are provided by companies as part of their candidate screening process. No correctness of technique or methodology is implied by these examples. Solutions may contain code fragments obtained from the internet, api guides, etc. 



StringManipulation
-------------------

AnagramMultimap - uses a map of lists to store alphabetically sorted words matching particular alphabet sequences

Pangram - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. Uses a map to store true/false values if a letter is in the sentence or not.

PangramImproved - Checks if a sentence is a pangram; rather checks what letters a sentence *doesn't* use. The sentence is placed in a set, compared to alphabet set. The difference between sets is found using removeAll()

StringComparatorSort - provides an example of Comparator implementation for String, to sort by String length (not alphabetically). This implementation is used when we are not overriding string object's implementation, but rather creating a special compare method of the implenting class, which then takes Strings as parameters.

StringDuplicates - two methods for determining whether or not a given sentence contains duplicate. One method simply loops through itself and compares itself to itself, with N2 performance. The other method puts the word array into a set, which will toss out any duplicates. The set is then compared to the original sentence word array.

ReverseString - shows two methods for reversing a string, one which uses a stack and the other a reversed loop. I show two variations of the reverse looping, one in which no extra char array object is created.

WordCount - uses a map to store word count frequency, obtains word list from file in classpath


LostRover
RoverController - based upon text string input, models a navigational controller for a mars rover, which knows its own location and direction on a simple grid.


ParticleChamber
Two programs show different strategies for caching move history in a model particle chamber. see particle_readme for further information. illustrates caching strategy's impact on performance.


TCPMsgServer
Consists of a Server which is tested by the Clients sending it messages. See the project readme for more information.