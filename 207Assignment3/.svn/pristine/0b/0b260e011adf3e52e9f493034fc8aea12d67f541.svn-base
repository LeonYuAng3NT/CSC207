// **********************************************************
// Assignment3:
// CDF user_name: c5zhanim
//
// Author: Yu Ang Zhang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************
package driver;

/**
 * MyParser parse the given argument and parse it to OutputFormatGenerator
 *
 * @author Yu Ang Zhang
 * 
 */
import outputproducer.ConsoleOutput;
import outputproducer.FileOutput;
import operator.GoogleScholarHTMLExtractor;
import operator.OutputFormatGenerator;

public class MyParser {

  /**
   * Parse the HTMLExtractor into OutputFormatGenerator and determine whether
   * the command indicates printing content on console or write on file
   * 
   * @param args input argument for main functions
   */

  public static void main(String[] args) {
    try {
      GoogleScholarHTMLExtractor googleExtractor =
          new GoogleScholarHTMLExtractor();
      OutputFormatGenerator format =
          new OutputFormatGenerator(googleExtractor, args);
      if (args[args.length - 1].endsWith(".html")) {
        // print content on console
        format = new OutputFormatGenerator(googleExtractor, args);
        ConsoleOutput output = new ConsoleOutput(format);
        output.produceOutput();
      } else {
        // write on file
        FileOutput fileoutput = new FileOutput(format, args[args.length - 1]);
        fileoutput.produceOutput();
      }

    } catch (Exception e) {
      System.out.println("Did you change the run configuration in"
          + "Eclipse for argument0 and argument 1?");
    }

  }

}

