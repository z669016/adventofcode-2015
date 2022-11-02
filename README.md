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
I've created a ```Submarine``` class, with a ```move``` method taking a ```Command``` record as an input. The command 
has a direction and a distance attribute. This approach made it easy to process the input. For part 1 the input is 
transformed into commands and the commands were used to move the submarine.
For part 2, I inherited from the initial ```Submarine``` class and changed the ```move``` method according to 
instructions, then processed the command list again... pretty straight forward.

## Day 3
Solved the puzzles using counting and filtering streams (just used ```List<String>``` and ```charAt()```).


