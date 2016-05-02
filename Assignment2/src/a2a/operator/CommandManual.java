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

import a2a.exceptions.QueriedCommandNotExistException;
import a2a.exceptions.ArgumentDeclareException;
import a2a.exceptions.InvalidTargetNameException;
import a2a.exceptions.NameConflictException;
import a2a.exceptions.PathDoesNotExistException;
import a2a.foundation.FileSystem;

import java.util.Hashtable;


/**
 * The Command Manual uses to inquiry the usage and specified
 * documentation of a command.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class CommandManual implements Command, OutputProducer {

  private boolean isRedirectMode;

  // The file system uses to execute this command
  private FileSystem fileSystem;

  // The string array of user input
  private String[] command;

  // The documentations of command
  private Hashtable<String, String> manual;

  /**
   * Construct a CommandManual.
   */
  public CommandManual() {

    manual = new Hashtable<>();
    manual.put("exit", "Usage: exit\nQuit the program");
    manual.put("mkdir", "Usage: mkdir DIR\nCreate directories in the "
        + "current directory or in a full path.");
    manual.put("cd", "Usage: cd DIR\nChange directory to DIR or a full path\n.."
        + " means a parent directory and . means the current directory.");
    manual.put("ls", "Usage: ls [-R][PATH …]\nList out the content of "
        + "directory\n-R will recursively list out all subdirectories");
    manual.put("pwd", "Usage: pwd\nPrint out the path of current directory");
    manual.put("pushd", "Usage: pushd DIR\nSaves the current working directory"
        + " onto the directory stack and then change directory to DIR.");
    manual.put("popd", "Usage: popd Remove the top entry from the directory "
        + "stack, and change current working directory into it.");
    manual.put("history", "Usage: history [number]\nPrint out input history");
    manual.put("cat", "Usage: cat FILE1[FILE2]\nDisplay the contents of FILE1");
    manual.put("mv", "Usage: mv OLDPATH NEWPATH\nMove from old to new path");
    manual.put("cp", "Usage: cp OLDPATH NEWPATH\nCopy from old to new path");
    manual.put("get", "Usage: get URL\nDownload the file to current directory");
    manual.put("echo", "Usage: echo STRING [MODE OUTFILE]\nPrint out STRING");
    manual.put("man", "Usage: man CMD\nPrint documentation of CMD");
    manual.put("!", "Usage: !number\nexecute the command from input history");
    manual.put("grep", "Usage: grep [-R] REGEX PATH …\n print any lines "
        + "containing REGEX in PATH\n or print contents of all files in path"
        + " if -R is presented.");
  }

  /**
   * Inspect the validity of the action which indicated by user input,
   * and set up the command before execute.
   *
   * @param userInput  The text entered by user
   * @param fileSystem The fileSystem which may be uses to execute command
   * @throws QueriedCommandNotExistException if queried command does not exist
   * @throws ArgumentDeclareException        if command have invalid argument
   * @throws PathDoesNotExistException       if output file path is invalid
   * @throws NameConflictException           if output file name is occupied
   * @throws InvalidTargetNameException      if output file name is invalid
   */
  @Override public void setUp(String userInput, FileSystem fileSystem)
      throws QueriedCommandNotExistException, ArgumentDeclareException,
      PathDoesNotExistException, NameConflictException,
      InvalidTargetNameException {

    this.command = userInput.trim().split("\\s+");
    this.fileSystem = fileSystem;
    this.isRedirectMode = (command.length == 4);

    if (command.length != 2 && command.length != 4) {
      throw new ArgumentDeclareException();
    } else if (!manual.containsKey(command[1])) {
      throw new QueriedCommandNotExistException();
    }

    // Check the path of redirect output file
    if (isRedirectMode) {
      PathChecker checker = new PathChecker(fileSystem);
      checker.inspectSinglePath(command[command.length - 1]);
    }
  }

  /**
   * Produce the output.
   *
   * @return The output of this command.
   */
  @Override public String produce() {

    return manual.get(command[1]);
  }

  /**
   * Execute the command, it will print out the documentation
   * of a command or redirect the output then write it to a file.
   */
  @Override public void execute() {

    // Produce the output of this command
    String result = this.produce();

    if (isRedirectMode) {
      FileEditor editor = new FileEditor(command, fileSystem);
      String outputFilePath = command[command.length - 1];
      editor.write(outputFilePath, result);

    } else {
      System.out.println(result);
    }
  }

}
