package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 *
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   *
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String output = "";
    String translate = args[1];

    if (args.length != 2) {
      pen.println("Invalid number of elements");
    } //if

    if (args[0].equals("braille")) {
      for (int i = 0; i < translate.length(); i++) {
        // If we're translating to braille, the user must have entered ascii
        output = output + BrailleAsciiTables.toBraille(translate.charAt(i));
      } //for
    } else if (args[0].equals("ascii")) {
      //If we're translating to ascii, the user must have entered braille
      for (int i = 0; i < translate.length(); i+=6) {
        output = output + BrailleAsciiTables.toAscii(translate.substring(i, i+5));
      } //for
    } else if (args[0].equals("unicode")) {
      //The user could have given us braille or ascii
      String unicodeTranslate = "";
      if ((translate.charAt(0) != '0') && (translate.charAt(1) != '1')) {
        //The user gave us ascii, so we translate to braille, then to unicode
        for (int i = 0; i < translate.length(); i++) {
          unicodeTranslate = unicodeTranslate + BrailleAsciiTables.toBraille(translate.charAt(i));
        } //for
      } else {
        unicodeTranslate = translate;
      }
      for (int i = 0; i < translate.length(); i+=6) {
        output = output + BrailleAsciiTables.toUnicode(unicodeTranslate.substring(i, i+5));
      } //for
    } else {
      pen.println("Invalid target character set.");
    } //if
    pen.println(output);
    pen.close();
  } // main(String[]

} // class BrailleASCII
