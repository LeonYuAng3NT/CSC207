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

import a2a.foundation.File;
import a2a.foundation.FileSystem;

/**
 * The File Editor uses to edit a file. It will automatically
 * detect the user is attempt to override a file or append the
 * new contents instead of override it.
 *
 * @author Yi Jian Wang
 */
public class FileEditor {

  // Append the contents to the file
  private boolean isAppend;
  private FileSystem fileSystem;

  /**
   * Construct a FileEditor.
   *
   * @param command    The command have the information of override or append
   * @param fileSystem The file system uses to store the edited file
   */
  public FileEditor(String[] command, FileSystem fileSystem) {

    this.fileSystem = fileSystem;
    this.isAppend = command[command.length - 2].equals(">>");
  }

  /**
   * Edit a existed file or create a new file with new contents.
   *
   * @param pathString  The string of the full path
   * @param newContents The new contents of the file
   */

  public void write(String pathString, String newContents) {

    PathAnalyzer analyzer = new PathAnalyzer(pathString);
    String[] path = analyzer.convert();
    Searcher searcher = new Searcher(path, fileSystem);

    boolean hasFile = (searcher.searchTarget(true) != null);

    // Edit an existed file
    if (hasFile) {
      File targetFile = (File) searcher.searchTarget(true);

      if (isAppend) {
        String originalContent = targetFile.getContents();
        targetFile.setContents(originalContent + "\n" + newContents);
      } else {
        targetFile.setContents(newContents);
      }

    } else {
      // Create new file in the location
      FileCreator creator = new FileCreator();
      String fileName = path[path.length - 1];
      creator.create(fileName, newContents, searcher.searchLocation());
    }
  }

}
