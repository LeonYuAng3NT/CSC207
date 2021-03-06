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
import a2a.foundation.FileSystem;

/**
 * The String Transponder uses to receive the string entered
 * by user, then print out the string or redirect it to a file.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class StringTransponder implements Command, OutputProducer {

  private String[] command;
  private FileSystem fileSystem;
  private String userInput;
  private boolean isRedirectMode;

  /**
   * Construct a StringTransponder.
   */
  public StringTransponder() {

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
      throws Exception {

    this.userInput = userInput;
    this.fileSystem = fileSystem;
    this.command = userInput.trim().split("\\s+");

    // Check the argument
    if (command.length < 2)
      throw new ArgumentDeclareException();

    this.isRedirectMode =
        command[command.length - 2].equals(">") || command[command.length - 2]
            .equals(">>");

    // Check the redirect output file path
    if (isRedirectMode) {
      PathChecker checker = new PathChecker(fileSystem);
      checker.inspectSinglePath(command[command.length - 1]);
    }
  }

  /**
   * Produce the output of this command.
   *
   * @return The output of this command.
   */
  @Override public String produce() {

    // Initialize the start position and end position
    int startIndex = userInput.indexOf("o") + 1;
    int endIndex = userInput.length();
    if (isRedirectMode)
      endIndex = userInput.indexOf('>') - 1;

    // Refine the index of the start position
    while (startIndex < endIndex && userInput.charAt(startIndex) == ' ')
      startIndex++;

    String result = "";
    for (int i = startIndex; i < endIndex; i++)
      result += userInput.charAt(i);

    return result.trim().replaceAll("\"", "");
  }

  /**
   * Execute the command, then print out the string entered by user
   * or redirect the output to a specified file.
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

}
