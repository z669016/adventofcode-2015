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
into a ```record Move(floor,position)```, where the ```floor``` contains the individual characters translated into +1 
or -1, and the position is the position in the stream (using an ```AtomicInteger``` as a static counter).

For part one, you simply need to sum all the first values in the stream of pairs. For part 2, ```reduce``` the stream
while adding the first values of the pairs until the sum becomes < 0. Then return the position (second value of the 
pair) that got you in the basement.

I initially used a do-while loop, but refactored it by adding the position in a Pair (javatuples), and later to a 
```record Move```. 

## Day 2
I've created a ```Box``` record, with the required methods for calculating wrap-size and ribbon per box. The input is
transformed into a ```List<Box>```. For part one the wrap-size per box is summed. Part 2 is similar, 
and sums the ribbon length per box.

## Day 3
This exercise is about counting addresses and visits (where one address could be visited multiple times). From the 
input, the characters are translated into a ```Direction```, and a ```Route``` instance is used to add the direction 
to a route. Each ```record Address(int x, int y)``` is added to the ```Route``` (which remembers the last address 
visited, and updates the visit-count for each address already visited). A ```Map``` is used to keep track of 
visited addresses, using the  address as a key. The number of visited addresses equals 
the size of the map, and the number of visits can be obtained by summing all the visit-counts (map values).

Part two isn't much different, but you need to keep two lists (one for addresses visited by Santa, and one for 
addresses visited by the robot).

## Day 4
Simple brute force ... use a counter and increase it until you found a MD5 hash for the input|counter with the right 
format. Just let the computer do what it's good at (compute).

## Day 5
Again brute force and  very straight forward. Created some utility methods that do the checks on strings (just a simple
separate utility method for every property to be checked). For convenience, I've created an ```Nice.isNice(String)``` 
and a ```Nice.isNicer(String)``` method that combine the required property checks. 

## Day 6
A great puzzle to solve using functional programming techniques. You have a 1000x1000 lightGrid with elements to which an 
instruction must be applied. The instruction can be turn-on, turn-off and toggle (whatever that may mean).
The ```LightInstructionFactory``` can  translate an input line into an ```LightInstruction``` (toggle, turn-off, 
turn-on). The ```LightInstructionProcessor``` can initialize a lightGrid with initial values, and can ```execute(...)``` 
```LightInstructions``` on that lightGrid. As the processor class maintains and changes state, it's not truly functional 
programming, but solving that could be another exercise.

Part 1 can be solved using a ```OnOffLightInstructionFactory```, that creates simple set instructions on boolean values 
(set to true, false, of not-value). Transform the input strings, using that on-off factory, and use the processor to 
execute  the instructions on the lightGrid. At the end, just count the number of lightGrid elements with value ```true```.

Part 2 can be solved using a ```IntensityLightInstructionFactory``` which creates instructions to add 1, subtract 1 
or add 2, to integer lightGrid elements. The processing is the same as for part 1, but using  a  lightGrid with a different type
of elements. After processing, simply sum the value of all elements in  the lightGrid. 

## Day 7
Another challenge to be solved using functional programming techniques. Each instruction can be translated into a 
```Supplier<Integer>``` with a name. A ```Circuit``` can be built from suppliers that get connected using their name. 
Each supplier can have zero, one or two operands, which are suppliers themselves.

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

## Day 11
Agan a straight forward puzzle. I decided to create a ```Password``` class, with a ```next()``` method, which 
generates a next password. You need to increment from right to left (last letter first) and when it becomes > 'z', set 
it to 'a', while increasing the character at it's left (repeat if that one also becomes greater tha 'z'). Repeat this 
process until a new valid password was found and then return that one.
The trick is probably in validating the new password, which can be nasty unless you create separate helper functions 
for each individual check. Then ```isValid``` boils down to ```isLengthEight(password) && isLowerCase(password) 
&& includesStraight(password) && onlyContainsValidCharacters(password) && hasTwoNonOverlappingPairs(password)```

## Day 12
This challenge is highly suitable for using the Visitor pattern, where the visitor simply sums all the integer values
that it visits. So, I loaded the JSON and used the Jackson library  to transform it into a ```Map```, and then use 
the ```MapVisitor``` class to take a ```Visitor``` object and offer it all elements it finds in the map.
For part 1, this is all you need. For part two, I slightly changed the ```MapVisitor```, so it can ignore (on request)
all elements it finds with the name "red" (it simply doesn't pass those elements to the visitor instance, so these are
not visited and their integer values get ignored).

## Day 13
First step is to translate all the input data into ```PersonToPersonHappiness``` records, which you will need for 
calculation. Then a ```Person``` class, which can calculate someone's happiness depending on the person to it's left and 
to it's right. For that purpose the Person class gets populated with a list of appropriate 
```PersonToPersonHappiness``` records using the ```HappinessMap``` factory class.

I solved part 1 using brute-force, by creating all permutations of persons, and then calculating the highest  
happiness score of all permutations. Part two is similar, just have the ```HappinessMap``` add "MYSELF" as a person, 
before creating the permutations. for the rest part 2 is the same as part 1.  

## Day 14
Started with a ```Reindeer``` class/record with a method to get the ```distance``` and ```state``` of a reindeer at any 
point in time, and a ```ReindeerRace``` class that would loop over the time (2503 second) to determine the end score. 
For part 1, that was good enough. Part 2, as expected, changed the game a bit, but not too much. The calculation of the 
scores changes on part 2, so I extracted the ```DistanceScoreSystem``` (Strategy pattern), and implemented an 
additional ```LeadScoreSystem``` for part 2 reusing the unchanged ```Reindeer``` and ```ReindeerRace``` classes.

## Day 15
First an ```Ingredient``` record, and a ```Cookie``` class which takes a ```Map<Ingredient,Integer>``` (list of 100 
teaspoons of different ingredients) as constructor parameter. The Cookie class has a ```score``` method that 
calculates the total score for a cookie from its map of ingredients.
The actual work is done by the ```IngredientsList``` class which takes a list of ```Ingredient```s and can find the
```maxCookie``` from all possible cookies that can get created from 100 teaspoons of ingredients. That solved the 
trick for part 1. This also proofed a right approach for part2, which uses only different criteria for the finding 
the maxCookie. So added a ```Predicate<Long>``` as a parameter to maxCookie, which made the method usable for part 2 
as well.

## Day 16
The description might feel more difficult than it actually is. First I created an ```Aunt``` class with a factor method, 
to translate the input lines into an ```Aunt``` object. Then added a ```couldMatch``` method, that took another 
```Aunt``` object as a parameter, and which performs a property by property match to see if both aunts could be a match.
The ```couldMatch``` method just checks if all property values are equal. For part 1, I now only needed to filter the
input list of aunts to find the one with all "known" properties are matching.
This proofed an approach also usable for part two, as on part 2 only the matching is different. So I added a 
```retroencabulatorMatch``` that performs slightly different matching, to find the right aunt. 

## Day 17
Okay, so find the possible armamentCombinations from containers that add up to 150 liters. It looks difficult, but is quite 
straight forward when using recursion. Start with a sorted list of containers (reverse ordered by size). Then create a 
list of possible solutions from the first of the list plus all armamentCombinations of the rest of the list  that add up to 
the required amount. When you have found all possible solutions with that first container of  the original list, remove 
that first one, and redo the exercise with the remaining list. You can find the implementation in the 
```ContainerCombinations``` helper class. That solves part 1, as you only need to count the number of 
solutions found.
Now part 2 is ridiculous simple. Get the minimum solution size out of the list of all solutions, and then filter the
list of solutions of part 1 to only keep the solutions of that minimum size. The answer to part two, is the number of
solutions after filtering.

## Day 18
Created a ```LightGrid``` class (using ```Grid``` under the hood), to represent the light grid, with a ```next()``` 
method which returns a next generation of the LightGrid (following the animation rules), and a ```burningLights()```
method to count the lights which are ON (have a value of '#' in the grid). This is enough for part 1.
Part two proved relatively easy, using a ```BrokenLightGrid``` class, which extends ```LightGrid```, and which "breaks"
the corner light on the initial ```LightGrid``` and after every animation cycle. That only requires minor additional
functionality on the factory method and the animation method (```next()```).

## Day 19
Part 1, I have solved on my own, but part 2 I just couldn't see the solution, so I studied the solution on Reddit by 
[askalski](https://www.reddit.com/r/adventofcode/comments/3xflz8/day_19_solutions/). You'd better check his article to 
understand the workings of the ```Tokenizer```used for part 2.
For part 1, I created a ```Transformation``` class, with an ```apply``` method that would take a molecule and returns
a list of all possible transformations (e.g. when multiple new molecules can be created as the 'from' element occurs 
multiple times in the starting molecule). And I created a ```Transformations``` class, which holds a list of 
```Transformation```s, with an ```apply``` method which returns a list of the result of applying all individual 
```Transformation```s in the list.

## Day 20
The puzzle looks more difficult than it actually is. I used a brute force method, just start with house number 1 and 
keep increasing until a number was found that received the right number of presents given the "visit strategy" of the 
elves. The trick is in calculating which elves will visit a particular house number using ```factors```.
For part 1, loop over all house numbers starting with 1, and simply sum all the factors (the numbers of the elves that 
visited the house) and multiply by 10 (as each elf delivers 10 presents, see ```presentsInfinite()```) to find the 
right house number. 
Part 2 is very similar, but uses a different strategy ```presentsFinite()``` to calculate the number of presents, as 
now each elf visits max 50 houses and delivers 11 presents.

## Day 21
Started with a ```Armament``` record which can be a ```WEAPON```, ```ARMOR``` or a ```RING```, containing the details 
of a piece of armament (like costs, damage, protection), and an ```Armory``` class which knows about all  types of 
```Armament``` and is capable of creating a list of all possible ```ArmamentCombination```s for a brute force  
approach to find the solution.
A ```Player``` class represents a player in the game which can be you, or the boss. A player can pick a list of 
```Armament```s, and has the ability to ```attack(Player opponent)```, forcing the opponent to ```defend(damage)``` 
against the damage caused by the used armament combination. 
To find the lowest cost to win (part 1), just take the list of ```ArmamentCombination```s, sort it from lowest to 
highest cost, and battle using them one by onw, until you found the first winning combination.
For part 2, you need to find the most expensive ArmamentCombination that will make you loose. Which is pretty similar 
to part 1, if you first sort the ```ArmamentConbination```s in reversed order of costs (most expensive first).

## Day 22
This may look like a slight variation to day 21, but it is a completely different ballgame. Finding the winner now has
become an actual breadths-search-first problem. You need to follow different paths of battle simultaneously to find  
the fastest solution. BSF search uses a queue to enable simultaneous search  (explore all 'next' possibilities before 
moving on). Using a ```PriorityQueue``` over a ```Queue``` ensures you explore the cheapest possible solution next.
First the ground works, a ```Player``` class as the base for a ```Boss``` class and a ```Wizard``` class.
Next a ```Drain```, ```MagicMissile```, ```Poison```, ```Recharge```, and ```Shield``` spell class, which, when cast,
reduce the mana of the wizard (payment) and activate some  ```Effect``` (interface).
Finally, a ```Game``` class to run the show. 

Finding the answer for part 1, means to start with an initial game (with a configured ```Wizard``` and  ```Boss```) 
and take turns until the game has ended.  When a ```Game``` takes a ```turn()```, it first applies all the active 
```Effect```s to the Wizard and the Boss. The effect timer will be updated and effects that have run out, are removed 
from the list of active effects. When the game isn't ```done``` (wizard or boss has lost), then the boss will strike 
it's attack on the wizard. Finally, the ```turn``` method returns the possible next Game states, which is the current 
state plus one possible additional spell cast. All possible next Game states are added to the priority queue, and the 
loop runs again (taking the first game from the ```PriorityQueue``` and calling it's ```turn()```). Once you see how 
the queue is being used for the search, it's fairly simple.

Part 2 isn't much more difficult. I simply added a parameter to the Game constructor (```hard```) which, makes that 
at the beginning of each turn, the wizard receives 1 damage. For the rest, everything is the same.

## Day 23
Yes, build a virtual processor running some program. I started ```Processor``` class which  implements ```Runnable```, 
that could load and ```compile```a list of instructions (puzzle input). The processor has a ```Map``` of ```Register```s 
called A, B, and IP (Instruction Pointer), which can be written to and read from (a Register is a 
```Consumer<Integer>```, and a ```Supplier<Integer>```). The program is compiled into```Instruction```s which  can be 
run (```Instruction``` implements ```Runnable```). When an instruction runs, it updates the required registers (A, B, 
and or IP). The processor supports HLF, INC, JIE, JIO, JMP, and TPL instructions (see  puzzle description).

The Instruction classes are very small and easy to read. Their toString method allows you to print the compiled 
program to text again. Debugging and stepping through the implementation is easy.

To solve part 1, load the input into the processor and run it, after which the registers can be read from. For part 2,
load the input into the processor, set register A to value 1, and run the program again.

## Day 24
Start with ```SumCombinations```, a utility class that can create all possible combinations of integers in a list that
sum up to a provided value. Then a ```WeightBalancer``` class which can split a list of integers in a number of groups
that all sum up to the same value (using ```SumCombinations```).

It might not be the fastest approach, but I started with all possible combinations that add up to 1/3 of the total 
weight for the first container. Then ordered that list based on size (as we need to find the "smallest" container for 
the passenger compartment and lowest Quantum Entanglement). Then find the possible second and third container grouping to match 
the first container (in weight) to make sure the total adds up correctly.
For part 2, I hoped I could make solution for part 1 more generic (not just 3, but any number of compartments), but 
that wasn't that easy, so I just created another weight balancer method for four compartments (slight change of the 3 
compartment method).

## Day 25
From the first part of the description (the low numbers table) you can find the algorithm to get the index of the value
at a certain (row, column) coordinate. You see the first index of the rows start with 1, for the second add 1 to the 
index, for the third add 2 to the previous index, etc. The value to add for every subsequent row increases with on. 
Knowing this pattern, you can calculate the value index of position 1 of each row. This is implemented in 
```CodeId.rowStar(row)```.
The horizontal values in each row have a similar pattern, however the first increase is equal to row-number + 1, and 
increases with 1 for every position you move to the right. This is implemented in ```CodeId.cell(row,column)```.
This means that having a (row, column) pair, you can calculate the index of the value to find, e.g. the value at (4, 3)
has index 18. Starting with the first value, calculate 18 times a "next" value to find the right answer.
```CodeId.valueAtCell(row,column)``` uses these methods to get the index and then calls 
```CodeId.valueAtIndex(index)``` which just loops to calculate the right value. And of course, given the high numbers 
it is probably wise to use long values instead of integers. 