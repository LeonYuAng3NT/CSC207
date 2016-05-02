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
import a2a.operator.WorkingPathPrinter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * The working path printer test uses to test if the methods in the class are
 * working as expected.
 *
 * @author Yiming Huang
 * @author Yi Jian Wang
 */
public class WorkingPathPrinterTest {

  private WorkingPathPrinter printer;
  private FileSystem fileSystem;
  // The cases that should be passed
  private String[] passed;
  // The cases that should be failed
  private String[] failed;

  /**
   * Set up the WorkingPathPrinter test.
   */
  @Before public void setUp() {
    this.fileSystem = new FileSystem();
    this.printer = new WorkingPathPrinter();
    passed = new String[] {"pwd", "    pwd", "pwd     ", "pwd > 1.txt"};
    failed =
        new String[] {"pwd abc", "pwd > /", "pwd > ..", "pwd > .", "pwd > !"};
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testWorkingPathPrinter() {
    try {
      this.printer = new WorkingPathPrinter();
    } catch (Exception e) {
      fail("Failed to created the instance of working path printer.");
    }
  }

  /**
   * Test if the method successfully set up or throw exception in suitable
   * cases.
   */
  @Test public void testSetUp() {
    // Test cases that should be passed
    try {
      for (String command : passed)
        printer.setUp(command, fileSystem);
    } catch (Exception e) {
      fail("Unhandled exception have been thrown.");
    }

    // Test cases that should be failed
    try {
      for (String command : failed)
        printer.setUp(command, fileSystem);
      fail("Exception should be thrown.");
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
      printer.setUp("pwd 123", fileSystem);
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
      printer.setUp("pwd > /DOES/NOT/EXIST/1.TXT", fileSystem);
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
      printer.setUp("pwd >> /..", fileSystem);
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
      printer.setUp("pwd > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method successfully produce the correct output.
   */
  @Test public void testProduce() {

    String expected = "/";
    try {
      printer.setUp("pwd", fileSystem);
    } catch (Exception e) {
      fail("Failed to set up the command.");
    }
    assertEquals(expected, printer.produce());
    assertEquals(fileSystem.getCurrDir().getAddress(), printer.produce());
  }

  /**
   * Test if the Execute method is working as expected.
   */
  @Test public void testExecute() {

    try {
      assertTrue(fileSystem.getCurrDir().getContent().size() == 0);
      printer.setUp("pwd > 1.txt", fileSystem);
      printer.execute();
      assertEquals("/", fileSystem.getCurrDir().getAddress());
      assertTrue(fileSystem.getCurrDir().getContent().size() == 1);
    } catch (Exception e) {
      fail("Unexpected exception have been thrown");
    }
  }

}
