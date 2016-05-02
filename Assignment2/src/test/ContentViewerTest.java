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
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.ContentViewer;
import a2a.operator.DirectoryCreator;
import a2a.operator.PathAnalyzer;
import a2a.operator.Searcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The Content Viewer test uses to test if the methods are
 * working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yu Wang
 */
public class ContentViewerTest {

  private FileSystem fileSystem;
  private ContentViewer viewer;

  /**
   * Set up for before every single test.
   */
  @Before public void setUp() {
    this.fileSystem = new FileSystem();
    this.viewer = new ContentViewer();
    try {
      DirectoryCreator directoryCreator = new DirectoryCreator();
      directoryCreator.setUp("mkdir jeep benz", fileSystem);
      directoryCreator.execute();

      directoryCreator = new DirectoryCreator();
      directoryCreator.setUp("mkdir jeep/useful benz/design", fileSystem);
      directoryCreator.execute();
    } catch (Exception e) {
      fail("Failed to set uo the ContentViewerTest");
    }
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testContentViewer() {
    try {
      this.viewer = new ContentViewer();
    } catch (Exception e) {
      fail("Failed to create an instance of ContentViewer.");
    }
  }

  /**
   * Test if the method can be successfully set up the command.
   */
  @Test public void testSetUp() {
    try {
      viewer.setUp("ls", fileSystem);
      viewer.setUp("ls jeep", fileSystem);
      viewer.setUp("ls jeep/useful", fileSystem);
      viewer.setUp("ls benz/../.", fileSystem);
      viewer.setUp("ls -R", fileSystem);
      viewer.setUp("ls -R jeep", fileSystem);
      viewer.setUp("ls /", fileSystem);
      viewer.setUp("ls . > 1.txt.", fileSystem);
      viewer.setUp("ls .", fileSystem);
    } catch (Exception e) {
      fail("Cases which should be passed but failed");
    }

    try {
      viewer.setUp("ls > jeep", fileSystem);
      viewer.setUp("ls >> jeep", fileSystem);
      viewer.setUp("ls -R > .", fileSystem);
      viewer.setUp("ls -R > ..", fileSystem);
      viewer.setUp("ls -R jeep > .", fileSystem);
      viewer.setUp("ls -R jeep > ..", fileSystem);
      viewer.setUp("ls -R jeep >> new!F.txt", fileSystem);
      viewer.setUp("ls . >> /", fileSystem);
      fail("Cases should be failed but passed");
    } catch (Exception expected) {
      // Empty catch statement
    }
  }

  /**
   * Test if the produce method can produce correct string.
   */
  @Test public void testProduce() {
    try {
      viewer.setUp("ls", fileSystem);
      assertEquals("jeep\n" + "benz\n", viewer.produce());

      viewer = new ContentViewer();
      viewer.setUp("ls jeep", fileSystem);
      assertEquals("jeep:\n" + "useful\n", viewer.produce());

      viewer = new ContentViewer();
      viewer.setUp("ls ..", fileSystem);
      assertEquals("/:\n" + "jeep\n" + "benz\n", viewer.produce());

      viewer = new ContentViewer();
      viewer.setUp("ls .", fileSystem);
      assertEquals("/:\n" + "jeep\n" + "benz\n", viewer.produce());

      viewer = new ContentViewer();
      String expected = "/:\n" + "jeep\n" + "benz\n" + "jeep:\n" + "useful\n"
          + "useful:\nbenz:\n" + "design\n" + "design:\n";
      viewer.setUp("ls -R", fileSystem);
      assertEquals(expected, viewer.produce());

      viewer = new ContentViewer();
      viewer.setUp("ls -R jeep", fileSystem);
      assertEquals("jeep:\n" + "useful\n" + "useful:\n", viewer.produce());
    } catch (Exception e) {
      fail("Unhandled exception have been thrown");
    }
  }

  /**
   * Test if the execute method for Content Viewer is working as expected.
   */
  @Test public void testExecute() {
    try {
      viewer.setUp("ls > 1.txt", fileSystem);
      viewer.execute();
      PathAnalyzer analyzer = new PathAnalyzer("1.txt");
      Searcher searcher = new Searcher(analyzer.convert(), fileSystem);
      String actual = ((File) searcher.searchTarget(true)).getContents();
      assertEquals("jeep\n" + "benz\n", actual);

    } catch (Exception e) {
      fail("Unhandled exception have been thrown");
    }

    try {
      viewer.setUp("ls > jeep", fileSystem);
      fail("Should be failed, since jeep is a directory");
    } catch (Exception expected) {
      // Empty catch statement
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
      viewer.setUp("ls > /NOTEXIST/f.txt", fileSystem);
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
      viewer.setUp("ls -R > ..", fileSystem);
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
      viewer.setUp("!ls -R > out!put.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

}
