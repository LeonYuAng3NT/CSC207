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
import a2a.foundation.Directory;
import a2a.foundation.File;
import a2a.foundation.FileSystem;

import java.util.Arrays;

/**
 * The Path Navigator is used to move item to specific location or
 * to overwrite a directory or file
 *
 * @author Yi Jian Wang
 */
public class PathNavigator implements Command {

  private boolean firstIsFile;
  private boolean secondExist;
  private boolean secondIsDir;

  private Searcher firstSearcher;
  private Searcher secondSearcher;

  private String newName;
  private String[] command;

  /**
   * Construct an PathNavigator.
   */
  public PathNavigator() {

  }

  /**
   * Inspect the validity of the action which indicated by user input, and set
   * up the command before execute.
   *
   * @param fileSystem The file system use to execute this command
   * @throws PathDoesNotExistException  if path is invalid
   * @throws NameConflictException      if name has been occupied
   * @throws InvalidTargetNameException if name contain invalid character
   * @throws TypeNotMatchException      if the target's type is invalid
   * @throws ConflictPathException      if two paths conflict with each other
   * @throws ArgumentDeclareException   if the argument is wrongly declared
   */
  @Override public void setUp(String userInput, FileSystem fileSystem)
      throws InvalidTargetNameException, PathDoesNotExistException,
      NameConflictException, TypeNotMatchException, ConflictPathException,
      ArgumentDeclareException {

    this.command = userInput.trim().split("\\s+");
    boolean isRedirectMode =
        (command.length == 5 && (command[3].equals(">>") || command[3]
            .equals(">")));
    if (command.length != 3 && command.length != 5 || (command.length == 5
        && !isRedirectMode))
      throw new ArgumentDeclareException();

    // Inspect double path
    PathChecker checker = new PathChecker(fileSystem);
    boolean[] result = checker.inspectDoublePath(command[1], command[2]);
    this.firstIsFile = result[0];
    this.secondExist = result[1];
    this.secondIsDir = result[2];

    // Call helper to continue set up the command
    this.deepSetUp(fileSystem);

    if (!firstIsFile) {
      Directory origin = (Directory) firstSearcher.searchTarget(false);
      if (Arrays.asList("/", "..", ".").contains(origin.getName()))
        throw new ConflictPathException();
    }
    if (isRedirectMode)
      checker.inspectSinglePath(command[command.length - 1]);
  }

  /**
   * This is a helper uses to continue set up the command.
   *
   * @param fileSystem The file system uses to execute this command
   */
  private void deepSetUp(FileSystem fileSystem) {

    String[] firstPath = new PathAnalyzer(command[1]).convert();
    String[] secondPath = new PathAnalyzer(command[2]).convert();

    String firstName = firstPath[firstPath.length - 1];
    String secondName = secondPath[secondPath.length - 1];

    this.newName = (firstName.equals(secondName)) ? firstName : secondName;
    this.firstSearcher = new Searcher(firstPath, fileSystem);
    this.secondSearcher = new Searcher(secondPath, fileSystem);
  }

  /**
   * Execute the command, then allow user to move directory with specific path
   * to certain path that is different from the original one.
   */
  @Override public void execute() {

    if (firstIsFile) {
      File origin = (File) firstSearcher.searchTarget(true);
      Directory oldLocation = firstSearcher.searchLocation();

      if (secondExist && secondIsDir) {
        // Add original file to new location then remove it from old location
        Directory newLocation = (Directory) secondSearcher.searchTarget(false);
        oldLocation.getContent().remove(origin);
        origin.setAddress(newLocation.getAddress() + origin.getName() + "/");
        newLocation.getContent().add(origin);

      } else if (secondExist) {
        // New path is file, then override the contents
        File newFile = (File) secondSearcher.searchTarget(true);
        newFile.setContents(origin.getContents());
        oldLocation.getContent().remove(origin);

      } else {
        // Rename the file at the location of the new path
        Directory newLocation = secondSearcher.searchLocation();
        FileCreator creator = new FileCreator();
        creator.create(newName, origin.getContents(), newLocation);
        oldLocation.getContent().remove(origin);
      }
    } else {
      // First is Directory
      this.helpExecute();
    }
  }

  /**
   * This is helper uses to execute the command when the first path
   * is a directory.
   */
  private void helpExecute() {

    Directory origin = (Directory) firstSearcher.searchTarget(false);
    Directory oldLocation = firstSearcher.searchLocation();

    // Set up the new location
    Directory newLocation;
    if (secondExist && secondIsDir) {
      newLocation = (Directory) secondSearcher.searchTarget(false);
    } else {
      newLocation = secondSearcher.searchLocation();
      origin.setName(newName);
    }
    // Set up the new address and parent of the original directory
    origin.setAddress(newLocation.getAddress() + origin.getName() + "/");
    origin.setParent(newLocation);

    // Add the origin to the new location and remove it from old location
    newLocation.getContent().add(origin);
    oldLocation.getContent().remove(origin);

    // Call helper to keep set up the address of the content items
    this.updateAddress(origin);
  }

  /**
   * This is a helper use to recursively update address of the moved
   * directory.
   *
   * @param moved The original directory which is moved to new location
   */
  private void updateAddress(Directory moved) {

    for (Object item : moved.getContent()) {
      if (item instanceof File) {
        File curr = (File) item;
        curr.setAddress(moved.getAddress() + curr.getName() + "/");

      } else {
        Directory curr = (Directory) item;
        curr.setAddress(moved.getAddress() + curr.getName() + "/");
        this.updateAddress(curr);
      }
    }
  }

}
