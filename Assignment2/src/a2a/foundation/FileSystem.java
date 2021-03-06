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
// Author: YI JIAN WANG
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
package a2a.foundation;

import java.util.Stack;

/**
 * The file system uses to store the directory structure, directory stack and
 * the input history. It is the most fundamental database.
 *
 * @author Yi Jian Wang
 */
public class FileSystem {

  private static FileSystem reference = null;

  // Initially establish the root directory as the foundation
  private Directory root;

  // It may be continuously change during executing command
  private Directory currDirectory;

  // The directory stack uses to store the directory
  private Stack<Directory> directoryStack;

  // The input history uses to store the user input
  private InputHistory inputHistory;

  /**
   * Construct a file system.
   */
  public FileSystem() {

    this.root = new Directory();

    // Initially point to root
    this.currDirectory = root;

    this.directoryStack = new Stack<>();
    this.inputHistory = new InputHistory();
  }

  /**
   * Get the instance of FileSystem.
   *
   * @return The previous or new instance of FileSystem.
   */
  public static FileSystem getInstance() {

    if (reference == null) {
      reference = new FileSystem();
    }
    return reference;
  }

  /**
   * Get the root directory
   *
   * @return The root directory
   */
  public Directory getRoot() {

    return root;
  }

  /**
   * Get the current directory
   *
   * @return The current directory
   */
  public Directory getCurrDir() {

    return currDirectory;
  }

  /**
   * Update the current directory.
   * 
   * @param currDirectory The current Directory
   */
  public void setCurrDir(Directory currDirectory) {

    this.currDirectory = currDirectory;
  }

  /**
   * Get the directory stack.
   * 
   * @return The current directory stack
   */
  public Stack<Directory> getDirStack() {

    return directoryStack;
  }

  /**
   * Get the input history.
   *
   * @return The input history
   */
  public InputHistory getHistory() {

    return inputHistory;
  }



}
