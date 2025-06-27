import java.io.*;
import java.nio.file.*;
import java.util.*;

// A helper class for the merging phase heap
class HeapNode implements Comparable<HeapNode> {
    int value;
    int runIndex; // The index of the file from which this value was read

    public HeapNode(int value, int runIndex) {
        this.value = value;
        this.runIndex = runIndex;
    }

    @Override
    public int compareTo(HeapNode other) {
        return Integer.compare(this.value, other.value);
    }
}