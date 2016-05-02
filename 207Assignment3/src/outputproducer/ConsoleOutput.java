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
package outputproducer;

import operator.OutputFormatGenerator;

/**
 * The ConsoleOutput produce and print the formatted content on console
 *
 * @author Yu Ang Zhang
 * 
 */
public class ConsoleOutput {
  private String finalOutput;

  /**
   * Construct the ConsoleOutput object with a OutputFormatGenerator object
   * 
   * @param format OutputFormatGenerator object to generate String output
   */
  public ConsoleOutput(OutputFormatGenerator format) {
    finalOutput = format.generate();
  }

  /**
   * Produce and print the output on console
   * 
   */
  public void produceOutput() {
    System.out.println(finalOutput);
  }

}
