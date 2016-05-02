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

import a2a.exceptions.ArgumentDeclareException;
import a2a.exceptions.InvalidTargetNameException;
import a2a.exceptions.NameConflictException;
import a2a.exceptions.PathDoesNotExistException;
import a2a.foundation.Directory;
import a2a.foundation.FileSystem;

/**
 * The Directory Navigator uses to navigate the user transfer
 * from current directory to the target directory.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class DirectoryNavigator implements Command {

  // The file system uses to execute the command
  private FileSystem fileSystem;

  // The target directory
  private Directory targetDirectory;

  /**
   * Construct a DirectoryNavigator.
   */
  public DirectoryNavigator() {

  }

  /**
   * Inspect the validity of the action which indicated
   * by user input, and set up the command before execute.
   *
   * @param userInput  The text entered by user
   * @param fileSystem The fileSystem which may be uses to execute command
   * @throws ArgumentDeclareException   if the command have incorrect argument
   * @throws PathDoesNotExistException  if a path in this command is invalid
   * @throws NameConflictException      if output filename has been occupied
   * @throws InvalidTargetNameException if output file have invalid name
   */
  @Override public void setUp(String userInput, FileSystem fileSystem)
      throws ArgumentDeclareException, PathDoesNotExistException,
      NameConflictException, InvalidTargetNameException {

    String[] command = userInput.trim().split("\\s+");
    boolean isRedirectMode = (command.length == 4);

    // Check the arguments include redirect mode or not
    if (command.length != 2 && !isRedirectMode || (isRedirectMode && !command[
        command.length - 2].equals(">") && !command[command.length - 2]
        .equals(">>"))) {
      throw new ArgumentDeclareException();
    }

    PathAnalyzer analyzer = new PathAnalyzer(command[1]);
    Searcher searcher = new Searcher(analyzer.convert(), fileSystem);

    this.fileSystem = fileSystem;
    this.targetDirectory = (Directory) searcher.searchTarget(false);

    // Check the path of target directory and the redirect output file path
    if (this.targetDirectory == null) {
      throw new PathDoesNotExistException(command[1]);
    } else if (isRedirectMode) {
      PathChecker checker = new PathChecker(fileSystem);
      checker.inspectSinglePath(command[command.length - 1]);
    }
  }

  /**
   * Execute the command, then navigate the user
   * from current directory to target directory.
   */
  @Override public void execute() {

    fileSystem.setCurrDir(targetDirectory);
  }

}
