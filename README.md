# External p-way Merge Sort in Java

This project is an implementation of an external, p-way balanced merge sort algorithm in Java. It was developed as a requirement for the MATA54 - Data Structures and Algorithms II course at UFBA.

The program is designed to sort files containing integers that are too large to fit into main memory, using the "Selection by Substitution" method to generate initial sorted runs and a min-heap for the merging phase.

---

## Cloud-Based Execution (Gitpod)

To facilitate grading and ensure compatibility, this project is configured to run in the cloud using Gitpod.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#/https://github.com/Elis-Vasconcelos/p-way-merge-sort)

### Testing Instructions on Gitpod

1.  Click the **Open in Gitpod** button above. You may need to authenticate with your GitHub account.
2.  Wait for the environment to load. This might take a minute the first time.
3.  A terminal will appear at the bottom of the screen with a "✅ Environment ready!" message. The code will already be compiled.
4.  Use this terminal to run the test cases. For example:

    ```bash
    # Test with p = 3
    java PWayMergeSort 3 input.txt output_p3.txt
    ```
5.  After execution, the output files (like `output_p3.txt`) will appear in the file explorer on the left and can be opened to verify the result.

### Local Execution (Alternative)

If you prefer to run the project locally, follow these steps.

#### Prerequisites
* Java Development Kit (JDK) version 8 or higher.

#### Steps
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/Elis-Vasconcelos/p-way-merge-sort.git](https://github.com/Elis-Vasconcelos/p-way-merge-sort.git)
    cd p-way-merge-sort
    ```
2.  **Compile the Code:**
    ```bash
    # This single command compiles all necessary .java files
    javac --release 8 PWayMergeSort.java
    ```
3.  **Execute and Test:**
    ```bash
    # Example for p = 2
    java PWayMergeSort 2 input.txt output_p2.txt
    ```
