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

import a2a.exceptions.InvalidTargetNameException;
import a2a.exceptions.NameConflictException;
import a2a.exceptions.PathDoesNotExistException;
import a2a.foundation.FileSystem;
import a2a.operator.PathChecker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * The path checker test uses to test if the methods in the PathChecker
 * are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 */
public class PathCheckerTest {

  private PathChecker pathChecker;
  private FileSystem fileSystem;

  /**
   * Set up the PathChecker test.
   */
  @Before public void setUp() {
    fileSystem = new FileSystem();
    pathChecker = new PathChecker(fileSystem);
  }

  /**
   * Test if the instance can be created successfully.
   */
  @Test public void testPathChecker() {
    try {
      pathChecker = new PathChecker(fileSystem);
    } catch (Exception e) {
      fail("Failed to create an instance of PathChecker");
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
      pathChecker.inspectSinglePath("/SWIFT/IS/SLOWER/THAN/OBJECTIVE/C");
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
      pathChecker.inspectSinglePath("..");
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
      pathChecker.inspectSinglePath("!Invalid@");
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

}


