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

import a2a.foundation.Directory;
import a2a.foundation.FileSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * The file system test uses to test if the methods in
 * FileSystem is working as expected.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 */
public class FileSystemTest {

  private FileSystem fileSystem;

  /**
   * Set up this test.
   */
  @Before public void setUp() {
    this.fileSystem = new FileSystem();
  }

  /**
   * Test if instance can be successfully created.
   */
  @Test public void testFileSystem() {
    try {
      this.fileSystem = new FileSystem();
    } catch (Exception e) {
      fail("Failed to create an instance of file system.");
    }
  }

  /**
   * Test if GetRoot method can be worked as expected.
   */
  @Test public void testGetRoot() {

    String expectedName = "/";
    String actualName = fileSystem.getRoot().getName();

    String expectedAddress = "/";
    String actualAddress = fileSystem.getRoot().getAddress();

    assertEquals(expectedName, actualName);
    assertEquals(expectedAddress, actualAddress);
    assertTrue(fileSystem.getRoot().getContent().size() == 0);
  }


  /**
   * Test if the GetCurrDir method is working as expected.
   */
  @Test public void testGetCurrDir() {

    String expectedAddress = "/";
    String actualAddress = fileSystem.getCurrDir().getAddress();
    assertEquals(expectedAddress, actualAddress);
  }

  /**
   * Test if the SetCurrDir method is working as expected.
   */
  @Test public void testSetCurrDir() {

    Directory parent = fileSystem.getRoot();
    Directory newDirectory = new Directory("Swift", "/Swift", parent);
    parent.getContent().add(newDirectory);
    fileSystem.setCurrDir(newDirectory);

    // Test if the current directory is the same object
    assertSame(newDirectory, fileSystem.getCurrDir());
  }

  /**
   * Test if the GetDirStack method is working as expected.
   */
  @Test public void testGetDirStack() {
    // Test if the directory stack have been initialize
    assertNotNull(fileSystem.getDirStack());
  }

  /**
   * Test if the GetHistory method is working as expected.
   */
  @Test public void testGetHistory() {
    assertNotNull(fileSystem.getHistory());
  }

}


