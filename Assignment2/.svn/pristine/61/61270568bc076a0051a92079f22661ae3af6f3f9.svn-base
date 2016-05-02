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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The directory creator test uses to test if the methods are working as
 * expected.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 * @author Yu Wang
 */
public class DirectoryCreatorTest {

  private FileSystem fileSystem;
  private String[] passed;
  private String[] failed;
  private DirectoryCreator dirCreator;

  /**
   * Set up Directory Creator test.
   */
  @Before public void setUp() {
    this.fileSystem = new FileSystem();
    this.passed = new String[] {"mkdir aec", "     mkdir adc", "mkdir file1",
        "mkdir cs > 1.txt", "mkdir objc >> 1.txt"};
    this.failed =
        new String[] {"mkdir", "mkdir aec", "mkdir .", "mkdir abc/abc abc/abc",
            "mkdir #new", "mkdir @123dada", "mkdir ..", "mkdir /",
            "mkdir ? > 1.txt", "mkdir good > aec"};
    this.dirCreator = new DirectoryCreator();
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testDirectoryCreator() {
    try {
      this.dirCreator = new DirectoryCreator();
    } catch (Exception e) {
      fail("Failed to create an instance of directory creator.");
    }
  }

  /**
   * Test if the method successfully set up or throw exception in suitable
   * cases.
   */
  @Test public void testSetUp() {

    try {
      for (String command : passed)
        dirCreator.setUp(command, fileSystem);
    } catch (Exception e) {
      fail("Cases should be passed but failed");
    }

    try {
      for (String command : failed)
        dirCreator.setUp(command, fileSystem);
      fail("Exception should be thrown.");
    } catch (Exception e) {
      // Empty catch statement
    }
  }

  /**
   * Test if the execute method for Directory Creator is working as expected.
   */
  @Test public void testExecute() {
    try {
      // File system should be empty now
      assertEquals(0, fileSystem.getCurrDir().getContent().size());
      for (String command : passed) {
        dirCreator.setUp(command, fileSystem);
        dirCreator.execute();
        dirCreator = new DirectoryCreator();
      }
      // After creation, it should have five directories now
      assertEquals(5, fileSystem.getCurrDir().getContent().size());

    } catch (Exception e) {
      fail("Failed to execute the command.");
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
      dirCreator.setUp("mkdir", fileSystem);
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
      dirCreator.setUp("mkdir /NOTEXISTED/ABC", fileSystem);
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
      dirCreator.setUp("mkdir newDIR > .", fileSystem);
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
      dirCreator.setUp("mkdir newDIR > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }
}
