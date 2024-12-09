package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Alyssa Ryan
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The length of the string that will be given.
   */
  int stringSize;
  /**
   * The root of the BitTree.
   */
  BitTreeNode root;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for the BitTree.
   * @param n : The number of bits that will be in the inputted strings
   */
  public BitTree(int n) {
    this.stringSize = n;
    this.root = new BitTreeNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets a specific value at the correct node given by the path 'bits'.
   * @param bits The path to where the value should be placed
   * @param value The value that should be placed at the node
   */
  public void set(String bits, String value) {
    if (bits.length() != stringSize) {
      throw new IndexOutOfBoundsException();
    } //if

    BitTreeNode current = this.root;
    for (int i = 0; i < stringSize; i++) {
      if (bits.charAt(i) == '0') {
        if (current.left == null) {
          BitTreeNode left = new BitTreeNode();
          current.left = left;
        } // if
        current = current.left;
      } else {
        if (current.right == null) {
          BitTreeNode right = new BitTreeNode();
          current.right = right;
        } // if
        current = current.right;
      } // if
    } // for
    current.value = value;
  } // set(String, String)

  /**
   * Gets the value from the node that can be found using the path 'bits'.
   * @param bits The path to the node we want
   * @return The value at the node we want
   */
  public String get(String bits) {
    if (bits.length() != stringSize) {
      throw new IndexOutOfBoundsException();
    } //if

    BitTreeNode current = this.root;
    for (int i = 0; i < stringSize; i++) {
      if ((current.left == null) && (current.right == null)) {
        PrintWriter pen = new PrintWriter(System.out, true);
        pen.println("Error: Trouble translating because character not found.");
        return "";
      } //if
      
      if (bits.charAt(i) == '0') {
        if (current.left == null) {
          throw new IndexOutOfBoundsException();
        } // if
        current = current.left;
      } else {
        if (current.right == null) {
          throw new IndexOutOfBoundsException();
        } // if
        current = current.right;
      } // if
    } // for

    if (current.value == null) {
      throw new IndexOutOfBoundsException();
    } else {
      return current.value;
    } // if
  } // get(String, String)

  /**
   * Recursively calls itself to trace every branch of the tree, printing all values in CSV format.
   * @param path The path we have traversed so far, in bit string form
   * @param current The current node we are at
   * @return The string we will print out, in CSV format
   */
  public String dumpHelper(String path, BitTreeNode current) {
    String output = path;
    if (current.value == null) {
      if (current.left != null) {
        String left = path + "0";
        output = dumpHelper(left, current.left);
      } // if
      if (current.left != null) {
        String left = path + "0";
        output = dumpHelper(left, current.left);
      } // if
    } else {
      return current.value + "," + output;
    } // if
    return "";
  } // dumpHelp(String, BitTreeNode)

  /**
   * Prints out all values stored in the bitTree and the path to get there.
   * @param pen The PrintWriter used to print out
   */
  public void dump(PrintWriter pen) {
    dumpHelper("", root);
  } // dump(PrintWriter)

  /**
   * Creates a bitTree based off of the given InputStream.
   * @param source The InputStream we are reading from to create the bitTree
   */
  public void load(InputStream source) {
    BufferedReader read = new BufferedReader(new InputStreamReader(source));
    String line;
    try {
      while ((line = read.readLine()) != null) {
        String[] splitLine = line.split(",");
        set(splitLine[0], splitLine[1]);
      } //while
    } catch (IOException e) {
      PrintWriter error = new PrintWriter(System.err);
      error.println("IOException detected");
    } //catch
  } // load(InputStream)

} // class BitTree
