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
import a2a.operator.FileCreator;
import a2a.operator.FileReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The file reader test uses to test if the methods in the FileReader
 * are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 */
public class FileReaderTest {

  private FileSystem fileSystem;
  private FileReader reader;

  /**
   * Set up the FileReader test.
   */
  @Before public void setUp() {
    this.fileSystem = new FileSystem();
    this.reader = new FileReader();
    FileCreator creator = new FileCreator();
    creator.create("first.txt", "happy new year", fileSystem.getCurrDir());
    creator.create("second.txt", "have a good day", fileSystem.getCurrDir());
    creator.create("third.txt", "nice to meet ya", fileSystem.getCurrDir());
  }

  /**
   * Test if the instance can be created successfully.
   */
  @Test public void testFileReader() {
    try {
      this.reader = new FileReader();
    } catch (Exception e) {
      fail("Failed to create an instance of FileReader");
    }
  }

  /**
   * Test if the method successfully set up the command.
   */
  @Test public void testSetUp() {
    try {
      reader.setUp("cat first.txt", fileSystem);
      reader.setUp("cat /second.txt", fileSystem);
      reader.setUp("cat ../third.txt", fileSystem);
      reader.setUp("cat first.txt > first.txt", fileSystem);
      reader.setUp("cat /second.txt >> second.txt", fileSystem);
      reader.setUp("cat ../third.txt > new.txt", fileSystem);
    } catch (Exception e) {
      fail("Failed to set up the CommandManual.");
    }
  }

  /**
   * Test if the method can successfully produce the output.
   */
  @Test public void testProduce() {
    try {
      reader.setUp("cat first.txt", fileSystem);
      String expected = "happy new year";
      String actual = reader.produce();
      assertEquals(expected, actual);

    } catch (Exception e) {
      fail("Unhandled exception have been thrown");
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
      reader.setUp("cat", fileSystem);
    } catch (ArgumentDeclareException a) {
      throw a;
    } catch (Exception e) {
      fail("Unhandled Exception have benn thrown.");
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
      reader.setUp("cat first.txt > /NOTEXIST/1.txt", fileSystem);
    } catch (PathDoesNotExistException p) {
      throw p;
    } catch (Exception e) {
      fail("Unhandled Exception have benn thrown.");
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
      reader.setUp("cat first.txt > .", fileSystem);
    } catch (NameConflictException n) {
      throw n;
    } catch (Exception e) {
      fail("Unhandled Exception have benn thrown.");
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
      reader.setUp("cat first.txt > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have benn thrown.");
    }
  }

}
