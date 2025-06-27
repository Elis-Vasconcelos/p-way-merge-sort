# Step-by-step guide on how to compile, execute, and properly test your `PWayMergeSort.java` program

### Step 1: Set Up Your Environment

1.  **Save the Code:** Make sure the latest version of the code is saved as `PWayMergeSort.java` in a folder on your computer.

2.  **Create the Test Input File:** In the same folder, create a new text file named `input.txt`. Copy and paste the 25 numbers from the assignment PDF into this file. Each number should be on a new line.

    **input.txt:**

    ```
    18
    7
    3
    24
    15
    5
    20
    25
    16
    14
    21
    19
    1
    4
    13
    9
    22
    11
    23
    8
    17
    6
    12
    2
    10
    ```

3.  **Open a Terminal/Command Prompt:** Navigate your terminal to the folder where you saved `PWayMergeSort.java` and `input.txt`.

### Step 2: Compile the Java Code

Run the Java compiler (`javac`) to create the executable `.class` file.

```bash
javac --release 8 PWayMergeSort.java
```

```bash
javac --release 8 HeapNode.java
```

If there are no errors, this command will silently create a `PWayMergeSort.class` file in your directory.

### Step 3: Execute the Program with Test Cases

Now, run the program using the exact examples provided in the assignment PDF. The command structure is: `java PWayMergeSort <p> <input_file> <output_file>`

#### Test Case 1: p = 3

  * **Command:**
    ```bash
    java PWayMergeSort 3 input.txt output_p3.txt
    ```
  * **Expected Console Output:**
    ```
    LOG: Phase 1: Generating initial runs...
    LOG: Generated 5 initial runs.
    LOG: Phase 2: Merging runs...
    LOG: Starting merge pass #1 with 5 runs.
    LOG: Starting merge pass #2 with 2 runs.
    LOG: Merge complete.

    Sorting completed in ... ms.

    --- FINAL STATISTICS ---
    #Regs   Ways    #Runs   #Passes
    25      3       5       2
    ```

#### Test Case 2: p = 2

  * **Command:**
    ```bash
    java PWayMergeSort 2 input.txt output_p2.txt
    ```
  * **Expected Console Output:**
    ```
    LOG: Phase 1: Generating initial runs...
    LOG: Generated 7 initial runs.
    LOG: Phase 2: Merging runs...
    LOG: Starting merge pass #1 with 7 runs.
    LOG: Starting merge pass #2 with 4 runs.
    LOG: Starting merge pass #3 with 2 runs.
    LOG: Merge complete.

    Sorting completed in ... ms.

    --- FINAL STATISTICS ---
    #Regs   Ways    #Runs   #Passes
    25      2       7       3
    ```

#### Test Case 3: p = 4

  * **Command:**
    ```bash
    java PWayMergeSort 4 input.txt output_p4.txt
    ```
  * **Expected Console Output:**
    ```
    LOG: Phase 1: Generating initial runs...
    LOG: Generated 4 initial runs.
    LOG: Phase 2: Merging runs...
    LOG: Starting merge pass #1 with 4 runs.
    LOG: Merge complete.

    Sorting completed in ... ms.

    --- FINAL STATISTICS ---
    #Regs   Ways    #Runs   #Passes
    25      4       4       1
    ```

### Step 4: Verify the Results

After running each test case, you need to check two things:

1.  **The Final Statistics:** Compare the console output from your program with the expected statistics shown above (which match the PDF). The `#Regs`, `Ways`, `#Runs`, and `#Passes` should all match.

2.  **The Sorted Output File:** Open the generated output files (`output_p3.txt`, `output_p2.txt`, `output_p4.txt`). Each file should contain the exact same content: the 25 numbers sorted in ascending order.

    **Expected content for all output files:**

    ```
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    24
    25
    ```

By following these steps, you can thoroughly test the program and be confident that it works correctly according to all specifications.