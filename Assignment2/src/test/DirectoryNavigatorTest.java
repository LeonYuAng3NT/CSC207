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

import a2a.exceptions.ArgumentDeclareException;
import a2a.exceptions.InvalidTargetNameException;
import a2a.exceptions.NameConflictException;
import a2a.exceptions.PathDoesNotExistException;
import a2a.foundation.FileSystem;
import a2a.operator.DirectoryCreator;
import a2a.operator.DirectoryNavigator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The directory Navigator test uses to test if the methods
 * are working as expected.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class DirectoryNavigatorTest {

  private FileSystem fileSystem;
  private DirectoryNavigator dirNavigator;
  private String[] passed;
  private String[] failed;

  /**
   * Set up the test.
   */
  @Before public void setUp() {

    this.fileSystem = new FileSystem();
    this.dirNavigator = new DirectoryNavigator();
    this.passed =
        new String[] {"cd ..", "cd .", "cd /", "cd /.", "cd /..", "cd ../",
            "cd ./", "cd please", "cd /please/let/me/get/enrolled", "cd ..",
            "cd ..", "cd ..", "cd /please/..", "cd ./please/.", "cd . > 1.txt"};
    this.failed =
        new String[] {"cd", "cd not", "cd / > please", "cd / > please/let",
            "cd / > .", "cd / > ..", "cd / > /"};

    DirectoryCreator dirCreator = new DirectoryCreator();
    try {
      String[] temp = new String[] {"mkdir please", "mkdir please/let",
          "mkdir please/let/me", "mkdir please/let/me/get",
          "mkdir please/let/me/get/enrolled"};
      for (String command : temp) {
        dirCreator.setUp(command, fileSystem);
        dirCreator.execute();
      }
    } catch (Exception e) {
      fail("Failed to set up the test");
    }
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testDirectoryNavigator() {

    try {
      this.dirNavigator = new DirectoryNavigator();
    } catch (Exception e) {
      fail("Failed to create an instance of directory navigator.");
    }
  }

  /**
   * Test if the set up method working as expected.
   */
  @Test public void testSetUp() {

    try {
      for (String command : passed)
        dirNavigator.setUp(command, fileSystem);
    } catch (Exception e) {
      fail("Failed to set up the command");
    }

    try {
      for (String command : failed)
        dirNavigator.setUp(command, fileSystem);
      fail("Some failed cases were passed");
    } catch (Exception e) {
      // empty catch statement
    }
  }

  /**
   * Test if execute method works as expected.
   */
  @Test public void testExecute() {

    String[] expected =
        new String[] {"/", "/", "/", "/", "/", "/", "/", "please", "enrolled",
            "get", "me", "let", "/", "please", "please"};

    try {
      String startPoint = fileSystem.getCurrDir().getName();
      assertEquals("/", startPoint);
      for (int i = 0; i < passed.length; i++) {
        dirNavigator.setUp(passed[i], fileSystem);
        dirNavigator.execute();
        assertEquals(expected[i], fileSystem.getCurrDir().getName());
      }

    } catch (Exception e) {
      fail("Failed to set up the command");
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
      dirNavigator.setUp("cd", fileSystem);
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
      dirNavigator.setUp("cd /NOTEXIST", fileSystem);
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
      dirNavigator.setUp("cd / > /", fileSystem);
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
      dirNavigator.setUp("cd / > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }
}


