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

/**
 * The Path Analyzer uses to analyze the string path and split
 * into an array of string. The string path may be invalid, but
 * it will be found out by other class.
 * 
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class PathAnalyzer {

  private String pathString;

  /**
   * Construct an PathAnalyzer.
   *
   * @param pathString The string of the path
   */
  public PathAnalyzer(String pathString) {

    this.pathString = pathString;
  }

  /**
   * Convert path string into an useful string array.
   *
   * @return The string array of path information
   */
  public String[] convert() {

    String[] path;

    if (pathString.equals("/")) {
      path = new String[1];
      path[0] = "/";

    } else {
      path = pathString.trim().split("/");

      // Return an empty array if path is invalid
      if (path.length == 0) {
        return path;

      } else if (path[0].equals("")) {
        // The path is start from root
        path[0] = "/";
      }
    }
    return path;
  }
}
