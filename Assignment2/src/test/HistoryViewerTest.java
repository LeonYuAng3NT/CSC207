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
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.foundation.InputHistory;
import a2a.operator.HistoryViewer;
import a2a.operator.Searcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The History Viewer Test uses to test if the methods are working as expected.
 *
 * @author Yiming Huang
 * @author Yi Jian Wang
 */
public class HistoryViewerTest {

  private FileSystem fileSystem;
  private HistoryViewer viewer;

  // The cases that should be passed
  private String[] passed;
  // The cases that should be failed
  private String[] failed;

  /**
   * Set up the HistoryViewer test.
   */
  @Before public void setUp() {

    fileSystem = new FileSystem();
    viewer = new HistoryViewer();
    InputHistory inputHistory = fileSystem.getHistory();
    inputHistory.record("cd root");
    inputHistory.record("man cd");
    inputHistory.record("mkdir abc");
    inputHistory.record("ls");

    passed = new String[] {"history", "history 1", "history 2", "history 3"};
    failed = new String[] {"history 200", "history -5", "history AA"};
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testHistoryViewer() {

    try {
      this.viewer = new HistoryViewer();
    } catch (Exception e) {
      fail("Failed to created the instance of history viewer.");
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
        viewer.setUp(command, fileSystem);
    } catch (Exception e) {
      fail("Unhandled exception have been thrown.");
    }

    // Test cases that should be failed
    try {
      for (String command : failed)
        viewer.setUp(command, fileSystem);
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
      viewer.setUp("history 0 k k k", fileSystem);
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
      viewer.setUp("history > /NOTEXIST/f.txt", fileSystem);
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
      viewer.setUp("history > ..", fileSystem);
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
      viewer.setUp("history > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out NoEnoughHistoryException.
   *
   * @throws NoEnoughHistoryException if no enough history record
   */
  @Test(expected = NoEnoughHistoryException.class)
  public void testSetUpWithNoEnoughHistoryException()
      throws NoEnoughHistoryException {
    try {
      viewer.setUp("history 220", fileSystem);
    } catch (NoEnoughHistoryException n) {
      throw n;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if method can correctly produce the output.
   */
  @Test public void testProduce() {
    try {
      viewer.setUp("history > result.txt", fileSystem);
      String expected = "1. cd root\n2. man cd\n3. mkdir abc\n4. ls\n";
      String actual = viewer.produce();
      assertEquals(expected, actual);

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if Execute method is working as expected.
   */
  @Test public void testExecute() {
    try {
      viewer.setUp("history > result.txt", fileSystem);
      viewer.execute();

      Searcher searcher = new Searcher(new String[] {"result.txt"}, fileSystem);
      String expected = "1. cd root\n2. man cd\n3. mkdir abc\n4. ls\n";
      String actual = ((File) searcher.searchTarget(true)).getContents();
      assertEquals(expected, actual);

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

}

