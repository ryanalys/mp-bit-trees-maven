package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;

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
   * The length of the string that will be given
   */
  int stringSize;
  BitTreeNode root;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   *
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
   *
   */
  public void set(String bits, String value) {
    if(bits.length() != stringSize) {
      throw new IndexOutOfBoundsException();
    } ///if

    BitTreeNode current = this.root;
    for(int i = 0; i < stringSize; i++) {
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
   * Gets the 
   */
  public String get(String bits) {
    if (bits.length() != stringSize) {
      throw new IndexOutOfBoundsException();
    } //if

    BitTreeNode current = this.root;
    for(int i = 0; i < stringSize; i++) {
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
   *
   */
  public void dump(PrintWriter pen) {
    // STUB
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    // STUB
  } // load(InputStream)

} // class BitTree
