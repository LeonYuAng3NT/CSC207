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

import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.FileCreator;
import a2a.operator.FileEditor;
import a2a.operator.Searcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The File Editor Test uses to test if the methods are working
 * as expected.
 *
 * @author Yi Jian Wang
 */
public class FileEditorTest {

  private FileSystem fileSystem;
  private FileEditor editor;

  /**
   * Initially set up this test.
   *
   * @throws Exception if failed to setup the test
   */
  @Before public void setUp() throws Exception {

    this.fileSystem = new FileSystem();
    FileCreator creator = new FileCreator();
    creator.create("file.txt", "first line", fileSystem.getCurrDir());
  }

  /**
   * Test if the FileEditor can be created successfully.
   */
  @Test public void testFileEditor() {
    this.editor = new FileEditor(new String[] {">", "1.txt"}, fileSystem);
  }

  /**
   * Test if the Write method can successfully write contents.
   */
  @Test public void testWrite() {

    this.editor = new FileEditor(new String[] {">", "2.txt"}, fileSystem);
    editor.write("new.txt", "I am the contents");

    Searcher searcher = new Searcher(new String[] {"new.txt"}, fileSystem);
    File found = (File) searcher.searchTarget(true);
    assertEquals("new.txt", found.getName());
    assertEquals("I am the contents", found.getContents());
    assertEquals("/new.txt/", found.getAddress());

    searcher = new Searcher(new String[] {"file.txt"}, fileSystem);
    File origin = (File) searcher.searchTarget(true);
    assertEquals("first line", origin.getContents());

    editor = new FileEditor(new String[] {">>", "file.txt"}, fileSystem);
    editor.write("file.txt", "second line");
    assertEquals("first line\nsecond line", origin.getContents());

    editor = new FileEditor(new String[] {">", "file.txt"}, fileSystem);
    editor.write("file.txt", "new line");
    assertEquals("new line", origin.getContents());
  }

}
