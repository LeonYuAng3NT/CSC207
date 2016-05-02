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

import a2a.exceptions.*;
import a2a.foundation.FileSystem;

import java.util.List;
import java.util.regex.Pattern;

/**
 * The HistoryViewer uses to view history record of user input.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class HistoryViewer implements Command, OutputProducer {

  private String[] command;
  private FileSystem fileSystem;

  // View specified number of history record only
  private boolean isPartlyViewMode;

  // Redirect the output
  private boolean isRedirectMode;

  /**
   * Construct a HistoryViewer.
   */
  public HistoryViewer() {

  }

  /**
   * Inspect the validity of the action which indicated by user input,
   * and set up the command before execute.
   *
   * @param userInput  The text entered by user
   * @param fileSystem The file system uses to store the edited file
   * @throws NoEnoughHistoryException   if no enough history recorded
   * @throws ArgumentDeclareException   if command have invalid argument
   * @throws PathDoesNotExistException  if path is invalid
   * @throws NameConflictException      if output file name has been occupied
   * @throws InvalidTargetNameException if output name is invalid
   */
  @Override public void setUp(String userInput, FileSystem fileSystem)
      throws NoEnoughHistoryException, ArgumentDeclareException,
      PathDoesNotExistException, InvalidTargetNameException,
      NameConflictException {

    this.fileSystem = fileSystem;
    this.command = userInput.trim().split("\\s+");
    this.isPartlyViewMode = (command.length == 2 || command.length == 4);
    this.isRedirectMode = (command.length == 3 || command.length == 4);

    // Check the argument
    if (command.length > 4 || (isRedirectMode && !command[command.length - 2]
        .equals(">") && !command[command.length - 2].equals(">>"))) {
      throw new ArgumentDeclareException();

    } else if (isPartlyViewMode) {
      // Check the number entered by user
      if (!Pattern.compile("[0-9]*").matcher(command[1]).matches()) {
        throw new ArgumentDeclareException();
      }
      // Check the actual number of history recorded
      int size = fileSystem.getHistory().getUserInputHistory().size();
      if (Integer.parseInt(command[1]) > size) {
        throw new NoEnoughHistoryException();
      }

    } else if (isRedirectMode) {
      PathChecker checker = new PathChecker(fileSystem);
      checker.inspectSinglePath(command[command.length - 1]);
    }
  }

  /**
   * Produce the output of the history record.
   *
   * @return The output of the history.
   */
  @Override public String produce() {

    String result = "";

    // The input history stored in file system
    List<String> history = fileSystem.getHistory().getUserInputHistory();

    // The actual amount of history record
    int amount = history.size();

    for (int i = 0; i < amount; i++) {
      // The number of history is ignored until the reached the number
      if (isPartlyViewMode && i < (amount - Integer.parseInt(command[1]))) {
        continue;
      }
      result += String.format("%d. %s%n", i + 1, history.get(i));
    }
    return result;
  }

  /**
   * Execute the command, then print out the history
   * record or redirect the output to a specified file.
   */
  @Override public void execute() {

    String result = this.produce();

    // Redirect the result or print out the result
    if (isRedirectMode) {
      FileEditor editor = new FileEditor(command, fileSystem);
      editor.write(command[command.length - 1], result);

    } else {
      System.out.print(result);
    }
  }

}
