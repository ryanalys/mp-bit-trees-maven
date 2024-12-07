package edu.grinnell.csc207.util;

public class BitTreeNode {
  /**
   * The value of this node, if it is a leaf
   */
  String value = null;

  /**
   * The left child of this node, if it is an interior node
   */
  BitTreeNode left = null;
  /**
   * The right child of this node, if it is an interior node
   */
  BitTreeNode right = null;

  public BitTreeNode() {
    this.value = null;
    this.left = null;
    this.right = null;
  } //BitTreeNode()
} //class BitTreeNode
