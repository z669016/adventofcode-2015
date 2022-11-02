# Advent of code 2015

## Convenience methods and classes
I have a small library with some convenience methods used for other AoC exercises. Like the ```ResourceLines``` class 
to read a resource file and transform the content into a ```List<String>```, or the CSV class to read a resource 
file containing comma separated values and returning a List of these values, optionally after transformation from 
```String``` to ```Integer```.

Also uses the algorithm library, which contains generic classes for addressing classic compute problems (from the book 
**Classic Computer Science Problems In Java** (c) Manning.com - 2020) 

It was never my intention to create the shortest program possible. I did try to create clear and simple implementations.

## Day 1
Quite straight forward for part one. Translate the input stream into a stream characters, and then map each character 
into a ```Pair<Integer,Integer>```, where the first value contains the individual characters translated into +1 or -1,
and the second value contains  the position in the stream (using an ```AtomicInteger```).

For part one, you simply need to sum all the first values in the stream of pairs. For part 2, ```reduce``` the stream
while adding the first values of the pairs until the sum becomes < 0. Then return the position (second value of the 
pair)  that got you in the basement.

I initially used a do-while loop, but refactored it by adding the position in a Pair. Just as an exercise.

## Day 2
I've created a ```Box``` class, with the required methods for calculating wrap-size and ribbon per box. 
For part 1 the input is transformed into ```Box``` objects and the wrap-size per box is summed. Part 2 is similar, 
and adds up all the ribbon length  required per box.

## Day 3
This exercise is about counting addresses and visits (where one address could be visited multiple times). From the 
input, the characters are translated into a ```Direction```, and a ```Route``` instance is used to add the direction 
to a route. A unique ```Address``` (basically an X,Y coordinate pair) is added to the list (remember the last one 
visited), and the visit-count is updated if an address is already in the list. A ```Map``` is used to keep track of 
visited addresses, using the  address as a key. The number of visited addresses equals 
the size of the list, and the number of visits can be obtained by summing all the visit-counts (map values) per address.
Part two isn't much different, but you need to keep two lists (one for addresses visited by Santa, and one for 
addresses visited by the robot).

## Day 4
Simple brute force ... use a counter and increase it until you found a MD5 hash for the input|counter with the right 
format. Just let the computer do what it's good at (compute).




