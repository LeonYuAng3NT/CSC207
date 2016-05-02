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
import a2a.foundation.FileSystem;
import a2a.operator.HistoryPerformer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The history performer test uses to test if the methods in the
 * HistoryPerformer are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 */
public class HistoryPerformerTest {

  private HistoryPerformer historyPerformer;
  private FileSystem fileSystem;

  /**
   * Set up the HistoryPerformer test.
   */
  @Before public void setUp() throws Exception {

    historyPerformer = new HistoryPerformer();
    fileSystem = new FileSystem();
    fileSystem.getHistory().record("mkdir swift");
    fileSystem.getHistory().record("cd swift");
    fileSystem.getHistory().record("cd ..");
    fileSystem.getHistory().record("mkdir swift/slower");
    fileSystem.getHistory().record("cd swift/slower");
    fileSystem.getHistory().record("!6");
  }

  /**
   * Test if the instance can be created successfully.
   */
  @Test public void testHistoryPerformer() {
    try {
      historyPerformer = new HistoryPerformer();
    } catch (Exception e) {
      fail("Failed to create an instance of HistoryPerformer");
    }
  }

  /**
   * Test if the method successfully set up or throw
   * exception in suitable cases.
   */
  @Test public void testSetUp() {
    try {
      historyPerformer.setUp("!1", fileSystem);
      historyPerformer.setUp("!2", fileSystem);
      historyPerformer.setUp("!3", fileSystem);
      historyPerformer.setUp("!4", fileSystem);
      historyPerformer.setUp("!5", fileSystem);
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown");
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
      historyPerformer.setUp("!", fileSystem);
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
      historyPerformer.setUp("!1 > /NOTEXIST/f.txt", fileSystem);
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
      historyPerformer.setUp("!3 > ..", fileSystem);
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
      historyPerformer.setUp("!1 > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out InfiniteExecutionException.
   *
   * @throws InfiniteExecutionException if command get in infinite re-execution
   */
  @Test(expected = InfiniteExecutionException.class)
  public void testSetUpWithInfiniteExecutionException()
      throws InfiniteExecutionException {
    try {
      historyPerformer.setUp("!6", fileSystem);
    } catch (InfiniteExecutionException i) {
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
      historyPerformer.setUp("!100", fileSystem);
    } catch (NoEnoughHistoryException n) {
      throw n;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the execute method is working as expected.
   */
  @Test public void testExecute() {

    try {
      historyPerformer.setUp("!1", fileSystem);
      historyPerformer.execute();
      historyPerformer.setUp("!2", fileSystem);
      historyPerformer.execute();
      historyPerformer.setUp("!3", fileSystem);
      historyPerformer.execute();
      historyPerformer.setUp("!4", fileSystem);
      historyPerformer.execute();
      historyPerformer.setUp("!5", fileSystem);
      historyPerformer.execute();
      assertEquals("slower", fileSystem.getCurrDir().getName());

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

}
