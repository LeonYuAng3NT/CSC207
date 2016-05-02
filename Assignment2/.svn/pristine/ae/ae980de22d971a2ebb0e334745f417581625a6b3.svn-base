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
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.Searcher;
import a2a.operator.StringTransponder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The string transponder test uses to test if the methods in the
 * StringTransponder are working as expected.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 */
public class StringTransponderTest {

  private StringTransponder echo;
  private FileSystem fileSystem;

  /**
   * Set up the StringTransponder test.
   */
  @Before public void setUp() {
    fileSystem = new FileSystem();
    echo = new StringTransponder();
  }

  /**
   * Test if the instance can be created successfully.
   */
  @Test public void testStringTransponder() {
    try {
      echo = new StringTransponder();
    } catch (Exception e) {
      fail("Failed to create an instance of StringTransponder");
    }
  }

  /**
   * Test if the method successfully set up or throw exception in suitable
   * cases.
   */
  @Test public void testSetUp() {
    try {
      echo.setUp("echo Life is not easy", fileSystem);
      echo.setUp("echo Sometimes", fileSystem);
      echo.setUp("echo You have to work hard", fileSystem);
      echo.setUp("echo But it does not means that> 1.txt", fileSystem);
      echo.setUp("echo You must get a good comeback >> 1.txt", fileSystem);
      echo.setUp("echo Misunderstanding >> 1.txt ", fileSystem);
      echo.setUp("echo \"is everywhere\"", fileSystem);
      echo.setUp("echo \"But be kind to everyone\" >> 1.txt", fileSystem);
      echo.setUp("echo \"Because finally we all get\" >> 1.txt", fileSystem);
      echo.setUp("echo Life = null; >> 1.txt", fileSystem);
      echo.setUp("echo \"\" > 1.txt", fileSystem);
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
      echo.setUp("echo", fileSystem);
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
      echo.setUp("echo \"Haha\" > /NOTEXISTED/ABC", fileSystem);
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
      echo.setUp("echo Have a good day > .", fileSystem);
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
      echo.setUp("echo nice weather > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the produce method can produce correct string.
   */
  @Test public void testProduce() {

    try {
      echo.setUp("     echo   This line      without quotation", fileSystem);
      assertEquals("This line      without quotation", echo.produce());

      echo.setUp("echo        \"Yes\"        > 1.txt", fileSystem);
      assertEquals("Yes", echo.produce());

      echo.setUp("echo Get ready    ", fileSystem);
      assertEquals("Get ready", echo.produce());

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown");
    }
  }

  /**
   * Test if the execute method is working as expected.
   */
  @Test public void testExecute() {

    try {
      echo.setUp("echo        \"Yes\"        > 1.txt", fileSystem);
      echo.execute();

      Searcher searcher = new Searcher(new String[] {"1.txt"}, fileSystem);
      File output = (File) searcher.searchTarget(true);
      assertEquals("1.txt", output.getName());
      assertEquals("Yes", output.getContents());

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown");
    }
  }


}


