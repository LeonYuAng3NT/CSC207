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
// Author: Yi Jian WANG
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
package driver;

import a2a.exceptions.InvalidCommandException;
import a2a.foundation.FileSystem;
import a2a.operator.Command;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * The JShell uses to receive user input and allocate tasks to different
 * part of the system based on user input.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class JShell {

  /**
   * This is a helper uses to get the relative class name of a command.
   *
   * @param userInput The text entered by user
   * @return The name of the relative class of the command
   * @throws InvalidCommandException if requested command does not exist
   */
  private String getName(String userInput) throws InvalidCommandException {

    Hashtable<String, String> cmdNames = new Hashtable<>();
    String[] command = userInput.trim().split("\\s+");

    cmdNames.put("cd", "a2a.operator.DirectoryNavigator");
    cmdNames.put("history", "a2a.operator.HistoryViewer");
    cmdNames.put("cat", "a2a.operator.FileReader");
    cmdNames.put("echo", "a2a.operator.StringTransponder");
    cmdNames.put("mkdir", "a2a.operator.DirectoryCreator");
    cmdNames.put("ls", "a2a.operator.ContentViewer");
    cmdNames.put("pwd", "a2a.operator.WorkingPathPrinter");
    cmdNames.put("pushd", "a2a.operator.DirectoryStackOperator");
    cmdNames.put("popd", "a2a.operator.DirectoryStackOperator");
    cmdNames.put("man", "a2a.operator.CommandManual");
    cmdNames.put("get", "a2a.operator.NetworkProtocol");
    cmdNames.put("mv", "a2a.operator.PathNavigator");
    cmdNames.put("cp", "a2a.operator.ItemDuplicator");
    cmdNames.put("grep", "a2a.operator.RegexFinder");
    cmdNames.put("!", "a2a.operator.HistoryPerformer");

    if (userInput.startsWith("!")) {
      return cmdNames.get("!");
    } else if (cmdNames.get(command[0]) == null) {
      throw new InvalidCommandException();
    }
    return cmdNames.get(command[0]);
  }

  /**
   * JShell application entry point.
   *
   * @param args The array of command-line arguments passed to this method
   */
  public static void main(String[] args) {

    JShell js = new JShell();
    Scanner in = new Scanner(System.in);
    FileSystem mainSystem = new FileSystem();

    boolean programIsRunning = true;
    do {
      // Print out the current path and record the user input
      System.out.printf("%s# ", mainSystem.getCurrDir().getAddress());
      String userInput = in.nextLine();
      mainSystem.getHistory().record(userInput);

      if (userInput.trim().equals("exit")) {
        programIsRunning = false;

      } else if (!userInput.trim().equals("")) {
        try {
          // Set up the command and execute it
          String cmdName = js.getName(userInput);
          Command cmd = (Command) Class.forName(cmdName).newInstance();
          cmd.setUp(userInput, mainSystem);
          cmd.execute();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    } while (programIsRunning);
    in.close();
  }
}
