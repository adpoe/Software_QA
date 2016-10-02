# CS1632 - Deliverable 2 - University of Pittsburgh, Fall 2016
@author Anthony (Tony) Poerio
@email adp59@pitt.edu

## CitySim9003
CitySim9003 is a simple simulation program that simulates a driver's path through a city--modeled as a Graph. The program outputs the driver's path and how many times she visited the Node called "coffee", during the simulation.

For this assignment, I built the program itself, and also wrote all of the unit tests to test each class.

There are 4 Classes that are used to model the simulation. Each class has its own set of Unit Tests, ensure that each public non-void function is tested for validity.

The classes are:
* Graph
* Node
* Edge
* Driver

The unit tests are:
* GraphTests
* NodeTests
* EdgeTests
* DriverTests

## Running the Tests
All tests are run as a full suite, by invoking the TestRunner.java class.

I've created a small shell script to make running the tests easier. 

To invoke the test suite, you just need to run:

`sh run.sh`

## Running the Simulation Itself
The simulation itself MUST be seeded with an integer value. This integer is used as the seed for the Random Number Generator which powers the program.

To run simulation do the following:


The simulation itself MUST be seeded with an integer value. This integer is used as the seed for the Random Number Generator which powers the program.

To run simulation do the following:

* First confirm tests are passing and compile the code, using the run script:
`sh run.sh`

* Second, run the following command:
`java CitySim9003 [Integer]`

The integer can be any value in the range [-2147483648, 2147483647] (-2^31 to 2^31)

These are the values that Java recognizes as an Integer.


## Code Example

Here's an example run:


>sh run.sh
>java CitySim9003 1
>
>Driver 1 heading from Hotel to Diner via Fourth Ave.
>Driver 1 heading from Diner to Outside City via Fourth Ave.
>Driver 1 has gone to Philadelphia!
>Driver 1 got 0 cup(s) of coffee.
>-----
>Driver 2 heading from Library to Outside City via Fifth Ave.
>Driver 2 has gone to Cleveland!
>Driver 2 got 0 cup(s) of coffee.
>-----
>Driver 3 heading from Diner to Coffee via Phil St.
>Driver 3 heading from Coffee to Library via Fifth Ave.
>Driver 3 heading from Library to Hotel via Bill St.
>Driver 3 heading from Hotel to Diner via Fourth Ave.
>Driver 3 heading from Diner to Coffee via Phil St.
>Driver 3 heading from Coffee to Diner via Phil St.
>Driver 3 heading from Diner to Outside City via Fourth Ave.
>Driver 3 has gone to Philadelphia!
>Driver 3 got 2 cup(s) of coffee.
>-----
>Driver 4 heading from Library to Hotel via Bill St.
>Driver 4 heading from Hotel to Library via Bill St.
>Driver 4 heading from Library to Outside City via Fifth Ave.
>Driver 4 has gone to Cleveland!
>Driver 4 got 0 cup(s) of coffee.
>-----
>Driver 5 heading from Diner to Outside City via Fourth Ave.
>Driver 5 has gone to Philadelphia!
>Driver 5 got 0 cup(s) of coffee.
>-----

## Installation
This library should run immediately just by cloning the repository and running the commands above

## Dependencies
- JUNit
- Hamcrest
- Mockito

All Dependencies are distributed as .jar files and included in this repository. It's fully packaged and ready to run.

## Thanks!
-tony

