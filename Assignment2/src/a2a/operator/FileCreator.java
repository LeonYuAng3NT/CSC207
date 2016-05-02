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
package a2a.operator;

import a2a.foundation.Directory;
import a2a.foundation.File;

/**
 * The File Creator uses to create a new file under the specify location.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class FileCreator {

  /**
   * Construct a FileCreator.
   */
  public FileCreator() {

  }

  /**
   * Create a new file with given name and contents to target directory.
   *
   * @param name     The name of the new file
   * @param contents The contents of the new file
   * @param location The target directory use to store the new file
   */
  public void create(String name, String contents, Directory location) {

    String newAddress = location.getAddress() + name + "/";
    File file = new File(name, contents, newAddress);

    // Add file to the content of the target location
    location.getContent().add(file);
  }

}
