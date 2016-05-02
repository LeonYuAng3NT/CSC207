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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Network Protocol uses to communicate with website and
 * then download the contents from the target website.
 *
 * @author Yi Jian Wang
 */
public class NetworkProtocol implements Command {

  private String[] command;
  private FileSystem fileSystem;

  // The name of the contents from website
  private String fileName;

  // The contents of the website
  private String contents;

  /**
   * Construct a NetworkProtocol.
   */
  public NetworkProtocol() {

  }

  /**
   * Inspect the validity of the action which indicated
   * by user input, and set up the command before execute.
   *
   * @param userInput  The text entered by user
   * @param fileSystem The fileSystem which may be uses to execute command
   * @throws ArgumentDeclareException   if the command have incorrect argument
   * @throws PathDoesNotExistException  if a path in this command is invalid
   * @throws NameConflictException      if output file name is occupied
   * @throws InvalidTargetNameException if output file name is invalid
   * @throws MalformedURLException      if the entered url is invalid
   * @throws IOException                if the target website does not exist
   */
  @Override public void setUp(String userInput, FileSystem fileSystem)
      throws ArgumentDeclareException, PathDoesNotExistException,
      NameConflictException, InvalidTargetNameException, IOException {

    this.command = userInput.trim().split("\\s+");
    this.fileSystem = fileSystem;

    // Exception may be passed from this helper method
    this.inspect();

    try {
      URL url = new URL(command[1]);
      InputStreamReader webStream = new InputStreamReader(url.openStream());
      BufferedReader urlReader = new BufferedReader(webStream);

      // Read contents from website get fill the contents
      String curr;
      while ((curr = urlReader.readLine()) != null) {
        if (contents == null) {
          contents = curr;
        } else {
          contents += "\n" + curr;
        }
      }
    } catch (MalformedURLException e) {
      throw new MalformedURLException("Invalid URL form: " + command[1]);
    } catch (IOException i) {
      throw new IOException("Target website does not exist: " + command[1]);
    }
  }

  /**
   * This is a helper uses to set up the command.
   *
   * @throws ArgumentDeclareException   if the command have incorrect argument
   * @throws PathDoesNotExistException  if a path in this command is invalid
   * @throws InvalidTargetNameException if the output file name is invalid
   * @throws NameConflictException      if the output file name is occupied
   */
  private void inspect()
      throws ArgumentDeclareException, PathDoesNotExistException,
      InvalidTargetNameException, NameConflictException {

    boolean isRedirectMode =
        (command.length == 4 && (command[2].equals(">") || command[2]
            .equals(">>")));

    // Check the argument and the output file path
    if (command.length != 2 && command.length != 4)
      throw new ArgumentDeclareException();

    PathChecker checker = new PathChecker(fileSystem);
    PathAnalyzer analyzer = new PathAnalyzer(command[1]);

    // Check if the fileName of the network contents
    // is not been occupied and it is in valid format
    this.fileName = analyzer.convert()[analyzer.convert().length - 1];
    checker.inspectSinglePath(fileName);

    // Check the redirect output file path
    if (isRedirectMode)
      checker.inspectSinglePath(command[command.length - 1]);
  }

  /**
   * Execute the command, then download the contents from website.
   * Since the editor operator is not detected by FileEditor, it
   * will trigger the FileEditor to override the file if existed.
   */
  @Override public void execute() {

    FileEditor editor = new FileEditor(command, fileSystem);

    // Writes contents to a file
    editor.write(fileName, contents);
  }
}
