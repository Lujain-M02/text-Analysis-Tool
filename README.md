# text-Analysis-Tool

this tool perform several operations as follow:
(1) An operation to determine the total number of words in a text file.
(2) An operation to determine the total number of unique words in a text file.
(3) An operation to determine the total number of occurrences of a particular word.
(4) An operation to determine the total number of words with a particular length.
(5) An operation to display the unique words and their occurrences sorted by the total occurrences of each word.
(6) An operation to display the locations of the occurrences of a word starting from the top of the text file (i.e., as a list of line and word positions). 
(7) An operation to examine if two words are occurring adjacent to each other in the file.


the implemented data structure:

![image](https://github.com/Lujain-M02/text-Analysis-Tool/assets/119123675/918d0c4b-6663-4a6f-91b0-291b2da45971)

structure description:
1- "ArrayofDifferentLengths" will be used to store words such that each index i of the array will contain a list of words that have length of i characters. 
2- The list of Nodes with "WordInformation" type will contain information about each word such as the word itself, a list of occurrences of that word (LinkedList<WordOccurrence>occList), and the size of the occurrences list.
3- "occList" will contain the different occurrences of the same word in terms of which line and position it occurs in.
