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
package a2a.exceptions;

/**
 * A Name Conflict Exception happened whenever an user attempt
 * to redirect the output of a command to a specified file but
 * the file name is conflict with a directory in the location.
 *
 * @author Yi Jian Wang
 */
public class NameConflictException extends Exception {

  /**
   * Construct the NameConflictException with no argument.
   */
  public NameConflictException() {

    super ("Unfortunately the target name have been occupied.");
  }

  /**
   * Construct a NameConflictException.
   *
   * @param path The path which cause this exception
   * @param name The name of the target which cause this exception
   */
  public NameConflictException(String path, String name) {

    super("The path: " + path + " already contain"
        + " a target share with the same named of : " + name);
  }
}
