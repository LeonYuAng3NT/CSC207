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
import a2a.foundation.File;
import a2a.foundation.FileSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * The File reader use to print out the contents of a file.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class FileReader implements Command, OutputProducer {

  private boolean isRedirectMode;

  private String[] command;
  private FileSystem fileSystem;

  // The target files
  private List<File> targetFiles;

  // The invalid paths
  private List<String> failedPath;

  /**
   * Construct a ContentViewer by passing a FileSystem object.
   */
  public FileReader() {

    this.targetFiles = new ArrayList<>();
    this.failedPath = new ArrayList<>();
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

    this.command = userInput.trim().split("\\s+");
    this.fileSystem = fileSystem;
    this.isRedirectMode =
        (command.length >= 4 && (command[command.length - 2].equals(">")
            || command[command.length - 2].equals(">>")));
    if (command.length < 2)
      throw new ArgumentDeclareException();

    // Check the redirect output file path
    if (isRedirectMode) {
      PathChecker checker = new PathChecker(fileSystem);
      checker.inspectSinglePath(command[command.length - 1]);
    }

    int endIndex = (isRedirectMode) ? command.length - 2 : command.length;
    for (int i = 1; i < endIndex; i++) {
      PathAnalyzer analyzer = new PathAnalyzer(command[i]);
      Searcher searcher = new Searcher(analyzer.convert(), fileSystem);
      if (searcher.searchTarget(true) == null) {
        // File is not found, record the failed path
        this.failedPath.add(command[i]);
      } else {
        // File is found, add it into the target files
        this.targetFiles.add((File) searcher.searchTarget(true));
      }
    }
  }

  /**
   * Execute the command, then start to read the files.
   * If redirect mode is enable, then it will redirect
   * the output to a specified file.
   */
  @Override public void execute() {

    String result = this.produce();

    if (isRedirectMode) {
      FileEditor editor = new FileEditor(command, fileSystem);
      editor.write(command[command.length - 1], result);

    } else {
      System.out.println(result);
    }
  }

  /**
   * Produce the output of this command.
   *
   * @return The output of this command.
   */
  @Override public String produce() {

    String result = "";
    for (File file : targetFiles) {
      if (result.equals("")) {
        result = file.getContents();
      } else {
        result += "\n\n\n" + file.getContents();
      }
    }
    // Avoid adding standard error message
    if (isRedirectMode) {
      return result;
    } else if (failedPath.size() != 0) {
      // Detected failed path
      String errorMsg = "The following does not exist or it is a directory: ";
      for (String path : failedPath) {
        errorMsg += path + " ";
      }
      result += "\n" + errorMsg;
    }
    return result;
  }

}

