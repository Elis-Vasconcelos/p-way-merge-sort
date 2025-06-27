# External p-way Merge Sort in Java

This project is an implementation of an external, p-way balanced merge sort algorithm in Java. It was developed as a requirement for the MATA54 - Data Structures and Algorithms II course at UFBA.

The program is designed to sort files containing integers that are too large to fit into main memory, using the "Selection by Substitution" method to generate initial sorted runs and a min-heap for the merging phase.

## How to Compile and Run

### Prerequisites

  * Java Development Kit (JDK) version 8 or higher.

### Step 1: Clone the Repository

First, clone this repository to your local machine. This will create a folder containing the necessary `PWayMergeSort.java` and `input.txt` files.

```bash
git clone https://github.com/your-username/p-way-merge-sort.git
cd p-way-merge-sort
```

*(Remember to replace `your-username` with your actual GitHub username)*

### Step 2: Compile the Code

Navigate into the project directory with your terminal and run the `javac` command. To ensure compatibility, we will compile for Java 8.

```bash
# This single command compiles all necessary .java files
javac --release 8 PWayMergeSort.java
```

*Note: You only need to compile the main file. The Java compiler will automatically find and compile any other required classes (like `MergeNode`).*

### Step 3: Execute and Test

Run the program from the command line, providing `p` (ways), an input file, and an output file name. The following examples use the `input.txt` provided in this repository.

#### Test Case 1 (p = 3)

  * **Command:**
    ```bash
    java PWayMergeSort 3 input.txt output_p3.txt
    ```
  * **Expected Statistics:**
    ```
    #Regs   Ways    #Runs   #Passes
    25      3       5       2
    ```

#### Test Case 2 (p = 2)

  * **Command:**
    ```bash
    java PWayMergeSort 2 input.txt output_p2.txt
    ```
  * **Expected Statistics:**
    ```
    #Regs   Ways    #Runs   #Passes
    25      2       7       3
    ```

#### Test Case 3 (p = 4)

  * **Command:**
    ```bash
    java PWayMergeSort 4 input.txt output_p4.txt
    ```
  * **Expected Statistics:**
    ```
    #Regs   Ways    #Runs   #Passes
    25      4       4       1
    ```

### Step 4: Verify the Output

After running any test case, a new `output_*.txt` file will be created. This file should contain the numbers from 1 to 25, sorted in ascending order.