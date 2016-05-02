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

import a2a.foundation.Directory;
import a2a.foundation.File;
import a2a.foundation.FileSystem;

import java.util.List;

/**
 * The Searcher provides different way to search an target.
 * It supports to search the location directory of a target,
 * and search a specified target.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class Searcher {

  // The string array of path after analyzed from a string path
  private String[] path;

  // The file system which uses to search a target
  private FileSystem fileSystem;

  /**
   * Construct a Searcher.
   *
   * @param path       The analyzed string array of a path
   * @param fileSystem The file system which uses to search a target
   */
  public Searcher(String[] path, FileSystem fileSystem) {

    this.path = path;
    this.fileSystem = fileSystem;
  }

  /**
   * Search for a specified target item.
   *
   * @param isSearchingFile True if the searching target is a File
   * @return The target item or null if it does not exist
   */
  public Object searchTarget(boolean isSearchingFile) {

    if (searchLocation() == null)
      return null;

    // Special target directory name
    String name = path[path.length - 1];
    if (!isSearchingFile && (name.equals(".") || name.equals("/"))) {
      return searchLocation();
    } else if (!isSearchingFile && name.equals("..")) {
      return searchLocation().getParent();
    }

    // Search for the target item in the directory location
    for (Object item : searchLocation().getContent()) {
      if (isSearchingFile && item instanceof File) {
        String currName = ((File) item).getName();
        if (currName.equals(name))
          return item;

      } else if (!isSearchingFile && item instanceof Directory) {
        String currName = ((Directory) item).getName();
        if (currName.equals(name))
          return item;
      }
    }
    // Target is not found
    return null;
  }

  /**
   * The purpose of this method is to find the directory location
   * of the target but not the target itself. If the path contain
   * invalid part, then it will return null.
   *
   * @return The directory location or null if it does not exist
   */
  public Directory searchLocation() {

    if (path.length == 0)
      return null;

    // Set up the starting directory
    if (path.length == 1 && path[0].equals("/")) {
      return fileSystem.getRoot();
    } else if (path.length == 1) {
      return fileSystem.getCurrDir();
    }

    Directory curr = fileSystem.getCurrDir();
    for (int i = 0; i < path.length && curr != null; i++) {
      if (i == path.length - 1) {
        return curr;
      } else if (i == 0 && path[i].equals("/")) {
        curr = fileSystem.getRoot();
      } else if (i != 0 && path[i].equals("/")) {
        curr = null;
      } else if (path[i].equals("..")) {
        curr = curr.getParent();
      } else if (!path[i].equals(".")) {
        // Call helper to search in the content of current location
        curr = deepSearch(i, curr);
      }
    }
    return curr;
  }

  /**
   * This is a helper uses to make a deep search
   * in the content of current location.
   *
   * @param index The index of the current position
   * @param curr  The starting point of this deep search
   * @return The directory location or null if it does not exist
   */
  private Directory deepSearch(int index, Directory curr) {

    List<Object> content = curr.getContent();

    boolean isFound = false;
    for (int i = 0; i < content.size() && !isFound; i++) {
      // Found file will terminate the for loop and return null
      if (content.get(i) instanceof File) {
        isFound = ((File) content.get(i)).getName().equals(path[index]);

      } else {
        isFound = ((Directory) content.get(i)).getName().equals(path[index]);
        // Directory is needed
        if (isFound)
          return (Directory) content.get(i);
      }
    }
    return null;
  }

}
