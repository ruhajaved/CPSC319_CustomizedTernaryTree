# CPSC319_CustomizedTernaryTree

## Introduction

### Problem Statement

Design and implement a ternary tree in Java, with requirements and functionality that customize it. Refer to the attached description for more details on these additional requirements and the required functionality.

### Project Objectives

The project objectives included:

1. Further understanding of Java and its capabilities.
2. Solidify understanding of tree data structures and recursive algorithms.
3. Practice and develop software design and architecture skills.

## Technologies

The technologies used include:

1. Java version 13.0.2 (note this is compatible with versions down to and including 1.8.0_161).

## Launch

To launch the project on your machine, first ensure that you hava a compatible Java compiler installed. 

Then ensure that all the files, namely IO.java, Operation.java, OpType.java, Utility.java, TernaryTree.java, TreeNode.java, and Runner.java, are in the same directory and at the same level - i.e. not below or above in reference to the current working directory. Also, while this is obvious, do not change the names of the source files.

To run the program, follow these steps:

1. Ensure the working directory contains all the source files (IO.java, Operation.java, OpType.java, Utility.java, TernaryTree.java, TreeNode.java, and Runner.java).
2. Execute the command "javac Runner.java".
3. Execute the command "java Runner myinput myoutput". Here, myinput is the name/address of the input file to read from, and myoutput is the name/address of the output file to either create or overwrite for program output. Note that you MUST provide 2 command line arguments and in this specific order, as specified in the command.

To be completely explicit, a set of successful commands to run the program would be:

javac Runner.java
java Runner input.txt output.txt

Upon execution, you can expect the program to appropriately output to the output file, as specified by the assignment handout. If there is any problem creating a connection with the input file (for example, if it doesn't exist) or the output file, an appropriate error will be printed to the terminal and the program will terminate. This will also happen if there's a problem writing to the output file.

## Things to Note

Note the following additional implicit requirements implemented:
1. No blank spaces in the input file are tolerated.
2. No empty lines in the input file are tolerated.
3. Input is parsed line by line. If there is invalid input halfway through, all valid commands are processed before outputting “Input error.”
4. 5. A test .txt file (test.txt) is included with example input, as well as the corresponding output file, output.txt, which demostrates the correct and expected output from the program.
