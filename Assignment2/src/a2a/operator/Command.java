// **********************************************************
// Assignment2:

// Student1:
// CDF user_name: c5zhanim
// UT Student #: 1001322847
// Author: Yu Ang Zhang
//
// Student2:
// CDF user_name: c4huanhf
// UT Student #: 1000076927
// Author: Yiming Huang
//

// Student3:
// CDF user_name: c4wangyk
// UT Student #: 999980579
// Author: Yi Jian Wang
//
// Student4:
// CDF user_name: c4wangzd
// UT Student #: 1001282319
// Author: Yu Wang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package a2a.operator;

import a2a.foundation.FileSystem;

/**
 * The command interface uses to execute a command which is assigned by user.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public interface Command {

  /**
   * Set up a command class.
   *
   * @param userInput  The text entered by user
   * @param fileSystem The file system uses to set up the command
   * @throws Exception if user input indicate to an invalid action
   */
  void setUp(String userInput, FileSystem fileSystem) throws Exception;

  /**
   * Execute the command.
   */
  void execute();
}
