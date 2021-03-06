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
package test;

import a2a.exceptions.*;
import a2a.foundation.Directory;
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.CommandManual;
import a2a.operator.FileCreator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The Command Manual Test uses to test if the methods
 * are working as described.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yu Wang
 */
public class CommandManualTest {

  private CommandManual commandManual;
  private FileSystem fileSystem;

  // The cases that should be passed
  private String[] passed;

  // The cases that should be failed
  private String[] failed;

  /**
   * Set up the Command Manual Test.
   */
  @Before public void setUp() {

    this.fileSystem = new FileSystem();
    this.commandManual = new CommandManual();

    // Use to create file in the file system
    FileCreator creator = new FileCreator();
    creator.create("first.txt", "c", fileSystem.getRoot());
    creator.create("second.txt", "c", fileSystem.getRoot());

    fileSystem.getCurrDir().getContent()
        .add(new Directory("dirName", "/dirName", fileSystem.getRoot()));

    this.passed =
        new String[] {"man exit", "man mkdir", "man cd", "man ls", "man pwd",
            "man pushd", "man popd", "man history", "man cat", "man mv",
            "man cp", "man get", "man echo", "man man", "man !", "man grep",
            "man cd > first.txt", "man cd >> second.txt", "man cd > new.txt",
            "man cd >> new.txt"};

    this.failed = new String[] {"man", "man notExistCommand", "man", "cd",
        "noOperator.txt", "man", "cd", "> /NotExist/file.txt",
        "man cd > /NotExist/file.txt", "man cd > dirName"};
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testCommandManual() {
    try {
      commandManual = new CommandManual();
    } catch (Exception e) {
      fail("Failed to created an instance of command manual.");
    }
  }

  /**
   * Test if the method successfully set up or throw
   * exception in suitable cases.
   */
  @Test public void testSetUp() {

    // Test cases that should be passed
    try {
      for (String command : passed) {
        commandManual.setUp(command, fileSystem);
      }
    } catch (Exception e) {
      fail("Failed to set up the CommandManual.");
    }

    // Test cases that should be failed
    try {
      for (String command : failed) {
        commandManual.setUp(command, fileSystem);
      }
      fail("Case which should be failed is accidentally passed.");
    } catch (Exception e) {
      // Empty catch statement
    }
  }

  /**
   * Test if the method can throw out ArgumentDeclareException.
   *
   * @throws ArgumentDeclareException if argument is not correct
   */
  @Test(expected = ArgumentDeclareException.class)
  public void testSetUpWithArgumentDeclareException()
      throws ArgumentDeclareException {

    try {
      commandManual.setUp("man", fileSystem);
    } catch (ArgumentDeclareException a) {
      throw a;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out PathDoesNotExistException.
   *
   * @throws PathDoesNotExistException if output file path is not exist
   */
  @Test(expected = PathDoesNotExistException.class)
  public void testSetUpWithPathDoesNotExistException()
      throws PathDoesNotExistException {

    try {
      commandManual.setUp("man cd > /NOTEXIST/output.txt", fileSystem);
    } catch (PathDoesNotExistException p) {
      throw p;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out NameConflictException.
   *
   * @throws NameConflictException if the output file name is conflict
   */
  @Test(expected = NameConflictException.class)
  public void testSetUpWithNameConflictException()
      throws NameConflictException {

    try {
      commandManual.setUp("man history > ..", fileSystem);
    } catch (NameConflictException n) {
      throw n;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out InvalidTargetNameException.
   *
   * @throws InvalidTargetNameException if the output filename is invalid
   */
  @Test(expected = InvalidTargetNameException.class)
  public void testSetUpWithInvalidTargetNameException()
      throws InvalidTargetNameException {

    try {
      commandManual.setUp("man ls > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out InvalidTargetNameException.
   *
   * @throws QueriedCommandNotExistException if the queried command not exist
   */
  @Test(expected = QueriedCommandNotExistException.class)
  public void testSetUpWithQueriedCommandNotExistException()
      throws QueriedCommandNotExistException {

    try {
      commandManual.setUp("man format", fileSystem);
    } catch (QueriedCommandNotExistException q) {
      throw q;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the produce method in Command Manual works as it describes.
   */
  @Test public void testProduce() {
    String[] expected = new String[] {"Usage: exit\nQuit the program", "Usage: "
        + "mkdir DIR\nCreate directories in the current directory or in a full"
        + " path.", "Usage: cd DIR\nChange directory to DIR or a full path\n.."
        + " means a parent directory and . means the current directory.",
        "Usage: ls [-R][PATH …]\nList out the content of directory\n-R will "
            + "recursively list out all subdirectories",
        "Usage: pwd\nPrint out the path of current directory", "Usage: pushd"
        + " DIR\nSaves the current working directory onto the directory stack"
        + " and then change directory to DIR.", "Usage: popd Remove the top "
        + "entry from the directory stack, and change current working directory"
        + " into it.", "Usage: history [number]\nPrint out input history",
        "Usage: cat FILE1[FILE2]\nDisplay the contents of FILE1",
        "Usage: mv " + "OLDPATH NEWPATH\nMove from old to new path",
        "Usage: cp OLDPATH " + "NEWPATH\nCopy from old to new path",
        "Usage: get URL\nDownload the " + "file to current directory",
        "Usage: echo STRING [MODE OUTFILE]\n" + "Print out STRING",
        "Usage: man CMD\nPrint documentation of CMD",
        "Usage: !number\nexecute the command from input history", "Usage: grep"
        + " [-R] REGEX PATH …\n print any lines containing REGEX in PATH\n or "
        + "print contents of all files in path if -R is presented."};
    try {
      for (int i = 0; i < expected.length; i++) {
        commandManual.setUp(passed[i], fileSystem);
        assertEquals(expected[i], commandManual.produce());
      }
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown");
    }
  }

  /**
   * Test if the method can be executed without any exception.
   */
  @Test public void testExecute() {

    fileSystem = new FileSystem();
    try {
      commandManual.setUp("man exit > document.txt", fileSystem);
      commandManual.execute();

      // Test redirect output
      File output = (File) fileSystem.getCurrDir().getContent().get(0);
      assertEquals("Usage: exit\nQuit the program", output.getContents());

      // Test append
      commandManual.setUp("man cat >> document.txt", fileSystem);
      commandManual.execute();
      String expected = "Usage: exit\nQuit the program\nUsage: cat FILE1[FILE2]"
          + "\nDisplay the contents of FILE1";
      assertEquals(expected, output.getContents());

      // Test override
      commandManual.setUp("man exit > document.txt", fileSystem);
      commandManual.execute();
      assertEquals("Usage: exit\nQuit the program", output.getContents());

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown");
    }
  }
}
