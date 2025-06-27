import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * A helper class for the merging phase heap. It holds a value
 * and the reader from which the value was read, allowing us to pull the next
 * value from the correct run file.
 */
class MergeNode implements Comparable<MergeNode> {
    int value;
    BufferedReader reader;

    public MergeNode(int value, BufferedReader reader) {
        this.value = value;
        this.reader = reader;
    }

    @Override
    public int compareTo(MergeNode other) {
        return Integer.compare(this.value, other.value);
    }
}

/**
 * Implements an external p-way merge sort.
 * Key features:
 * - Phase 1: Generates initial sorted runs using the "Selection by Substitution" algorithm.
 * - Phase 2: Merges the runs in passes using a min-heap until a single sorted file remains.
 */
public class PWayMergeSort {

    private final int p;
    private final String inputFile;
    private final String outputFile;
    private final Path tempDir;

    private int totalRecords = 0;
    private int initialRuns = 0;
    private int totalPasses = 0;

    public PWayMergeSort(int p, String inputFile, String outputFile) {
        this.p = p;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.tempDir = Paths.get("temp_runs");
    }

    /**
     * Executes the full sorting process.
     */
    public void sort() throws IOException {
        long startTime = System.currentTimeMillis();
        setupTempDirectory();

        try {
            // Phase 1: The "Run Generator"
            System.out.println("LOG: Phase 1: Generating initial runs...");
            List<Path> runFiles = generateInitialRuns();
            this.initialRuns = runFiles.size();
            System.out.println("LOG: Generated " + this.initialRuns + " initial runs.");

            // Phase 2: The "Merger"
            if (!runFiles.isEmpty()) {
                System.out.println("LOG: Phase 2: Merging runs...");
                Path finalFile = mergeRuns(runFiles);
                 // Move the final sorted file to the specified output location
                Files.move(finalFile, Paths.get(this.outputFile), StandardCopyOption.REPLACE_EXISTING);
            } else {
                // If the input file was empty, create an empty output file.
                Files.createFile(Paths.get(this.outputFile));
            }

        } finally {
            // Cleanup and print results
            deleteTempDirectory();
            long endTime = System.currentTimeMillis();
            System.out.println("\nSorting completed in " + (endTime - startTime) + " ms.");
            printStatistics();
        }
    }

    /**
     * PHASE 1: The Run Generator.
     * Implements the "Selection by Substitution" algorithm to create initial sorted runs.
     * @return A list of Paths to the generated run files.
     */
    private List<Path> generateInitialRuns() throws IOException {
        List<Path> runFiles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            PriorityQueue<Integer> heap = new PriorityQueue<>(p);
            List<Integer> frozen = new ArrayList<>();
            String line;
            int runCounter = 0;

            // Prime the heap with the first 'p' numbers from the file.
            for (int i = 0; i < p && (line = reader.readLine()) != null; i++) {
                heap.offer(Integer.parseInt(line.trim()));
                this.totalRecords++;
            }

            // Main loop to generate runs
            while (!heap.isEmpty() || !frozen.isEmpty()) {
                if (heap.isEmpty()) { // End of a run
                    // Start a new run with the frozen elements
                    heap.addAll(frozen);
                    frozen.clear();
                }

                runCounter++;
                Path runFile = tempDir.resolve("run_0_" + runCounter + ".tmp");
                runFiles.add(runFile);
                
                // Use try-with-resources to ensure the writer is closed automatically
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(runFile.toFile()))) {
                    while (!heap.isEmpty()) {
                        int minValue = heap.poll();
                        writer.write(minValue + "\n");

                        line = reader.readLine();
                        if (line != null) {
                            this.totalRecords++;
                            int newValue = Integer.parseInt(line.trim());
                            if (newValue >= minValue) {
                                heap.offer(newValue);
                            } else {
                                frozen.add(newValue);
                            }
                        }
                    }
                }
            }
        }
        return runFiles;
    }

    /**
     * PHASE 2: The Merger.
     * Merges the runs in passes until a single sorted file remains.
     * @param runFiles The initial list of run files from Phase 1.
     * @return The Path to the final, single, sorted file.
     */
    private Path mergeRuns(List<Path> runFiles) throws IOException {
        List<Path> currentRuns = new ArrayList<>(runFiles);
        
        while (currentRuns.size() > 1) {
            this.totalPasses++;
            System.out.println("LOG: Starting merge pass #" + this.totalPasses + " with " + currentRuns.size() + " runs.");
            List<Path> nextPassRuns = new ArrayList<>();
            
            // Process runs in groups of size 'p'
            for (int i = 0; i < currentRuns.size(); i += p) {
                int end = Math.min(i + p, currentRuns.size());
                List<Path> groupToMerge = currentRuns.subList(i, end);

                Path mergedFile = tempDir.resolve("run_" + this.totalPasses + "_" + (i / p) + ".tmp");
                mergeGroup(groupToMerge, mergedFile);
                nextPassRuns.add(mergedFile);
            }

            // Delete the files from the previous pass
            for (Path file : currentRuns) {
                Files.delete(file);
            }
            currentRuns = nextPassRuns;
        }
        
        // The last remaining file is the fully sorted result
        System.out.println("LOG: Merge complete.");
        return currentRuns.get(0);
    }
    
    /**
     * Merges a single group of run files into one larger run file.
     * @param group A list of up to 'p' sorted run files.
     * @param outputFile The file to write the merged result to.
     */
    private void mergeGroup(List<Path> group, Path outputFile) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();
        PriorityQueue<MergeNode> mergeHeap = new PriorityQueue<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.toFile()))) {
            // Open readers and prime the heap with the first element from each file
            for (Path file : group) {
                BufferedReader reader = new BufferedReader(new FileReader(file.toFile()));
                readers.add(reader);
                String line = reader.readLine();
                if (line != null) {
                    mergeHeap.offer(new MergeNode(Integer.parseInt(line.trim()), reader));
                }
            }

            // Continuously pull the smallest element from the heap and write it to the output
            while (!mergeHeap.isEmpty()) {
                MergeNode minNode = mergeHeap.poll();
                writer.write(minNode.value + "\n");

                // Read the next element from the same file and add it to the heap
                String nextLine = minNode.reader.readLine();
                if (nextLine != null) {
                    mergeHeap.offer(new MergeNode(Integer.parseInt(nextLine.trim()), minNode.reader));
                }
            }
        } finally {
            // Ensure all readers are closed
            for (BufferedReader reader : readers) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Log or ignore error on close
                }
            }
        }
    }


    // --- Helper and Main Methods ---

    private void setupTempDirectory() throws IOException {
        if (Files.exists(tempDir)) {
            deleteTempDirectory();
        }
        Files.createDirectory(tempDir);
    }

    private void deleteTempDirectory() throws IOException {
        if (!Files.exists(tempDir)) return;
        // This is a more robust way to delete a directory and its contents
        try (Stream<Path> walk = Files.walk(tempDir)) {
            walk.sorted(java.util.Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        System.err.println("Failed to delete " + path + ": " + e.getMessage());
                    }
                });
        }
    }

    private void printStatistics() {
        System.out.println("\n--- FINAL STATISTICS ---");
        System.out.println("#Regs\tWays\t#Runs\t#Passes");
        System.out.printf("%-8d%-8d%-8d%-8d\n",
                this.totalRecords, this.p, this.initialRuns, this.totalPasses);
    }
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java PWayMergeSort <p> <input_file> <output_file>");
            return;
        }
        try {
            int p = Integer.parseInt(args[0]);
            if (p < 2) {
                System.err.println("Error: p must be >= 2.");
                return;
            }
            String inputFile = args[1];
            String outputFile = args[2];
            new PWayMergeSort(p, inputFile, outputFile).sort();
        } catch (NumberFormatException e) {
            System.err.println("Error: <p> must be an integer.");
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
