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

## Day 5
Again brute force and  very straight forward. Create some utility methods that do the checks on strings (just a simple
separate utility method for every property to be checked). For convenience, I've created an ```isNice``` and 
```isNicer``` method that combined the property checks. 

## Day 6
A great puzzle to solve using functional programming techniques. You have a 1000x1000 grid with elements to which an 
instruction must be applied. The instruction can be turn-on, turn-off and toggle (whatever that may mean).
The ```LightInstructionFactory``` can  translate an input line into an ```LightInstruction``` (toggle, turn-off, 
turn-on). The ```LightInstructionProcessor``` can initialize a grid with initial values, and can ```execute(...)``` 
```LightInstructions``` on that grid. As the processor class maintains and changes state, it's not truly functional 
programming, but solving that could be another exercise.

Part 1 can be solved using a ```OnOffLightInstructionFactory```, that creates simple set instructions on boolean values 
(set to true, false, of not-value). Transform the input strings, using that on-off factory, and use the processor to 
execute  the instructions on the grid. At the end, just count the number of grid elements with value ```true```.

Part 2 can be solved using a ```IntensityLightInstructionFactory``` which creates instructions to add 1, subtract 1 
or add 2, to integer grid elements. The processing is the same as for part 1, but using  a  grid with a different type
of elements. After processing, simply sum the value of all elements in  the grid. 

## Day 7
Another challenge to be solved using functional programming techniques. Each instruction can be translated into a 
```Supplier<Integer>``` with a name. A ```Circuit``` can be build from suppliers that get connected using their name. 
Each supplier can have zero, one or two operants, which are suppliers themselves.

For part 1, you build a ```Circuit```, by literally wiring the ```Supplier<Integer>``` instances, and then request the
circuit for the value on the supplier named 'a'. On part two, you replace the coding  instruction for wire 
'b' to the constant from part 1, then reset the circuit and again obtain  the value for wire 'a'.

It's actually not that difficult, but remember to first read all encodings (instructions) and then build the 
circuit on demand, from the named wire that is required. As a result, some encodings might not even be used, and you 
don't need to create instructions for unused wires. 

(Reading it this way maybe doesn't make things more clear, better look at the real code)

## Day 8
Quite  similar to day 5. Load the input in a list, and do some stream processing using utility methods that manipulate
the string. You  can then calculate the results from part 1 and 2 from the results of the stream processing. 
Very straight forward. The biggest challenge is in the helper methods that do the string manipulation, which need
proper testing as well.

## Day 9
Multiple ways to solve this (of course) but as the list of cities isn't too big, a brute force will do (just calculate
the length of all possible routes). Brute force is relatively easy, as all cities get visited only once, so I used a 
list of all possible permutations of the cities. First step is to load  the input in a ```DistanceMap``` which holds the
distance between city-1 to city-2, and it can get you a ```Set``` of all (unique) cities. Then create a list of 
permutations of all cities, and the length of the trip for each permutation. From that list it's easy to get the 
minimum (part 1) and maximum values (part 2).

## Day 10
My basic approach for this puzzle is to split the input string into blocks of the same character ("3113322113" becomes
["3","11","33","22","11","3"]), and then join them again while inserting the count in front of each part. Using the
right algorithm is probably key to prevent use of much memory slowing things down.  
The approach works like a charm, part 1 is less than a second, and  even part two doesn't take more than two seconds.  


