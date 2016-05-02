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

import a2a.operator.PathAnalyzer;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * The Path Analyzer Test uses to test if the methods
 * are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yu Wang
 */
public class PathAnalyzerTest {

  private PathAnalyzer analyzer;

  /**
   * Test if an instance can be successful created.
   */
  @Test public void testPathAnalyzer() {
    try {
      this.analyzer = new PathAnalyzer("/ad");
    } catch (Exception e) {
      fail("Failed to create an instance of PathAnalyzer");
    }
  }

  /**
   * Test if Convert method can be functioned as it describes.
   */
  @Test public void testConvert() {

    this.analyzer = new PathAnalyzer("desktop/csc207");
    assertArrayEquals(new String[] {"desktop", "csc207"}, analyzer.convert());

    this.analyzer = new PathAnalyzer("/jeep");
    assertArrayEquals(new String[] {"/", "jeep"}, analyzer.convert());

    this.analyzer = new PathAnalyzer(".");
    assertArrayEquals(new String[] {"."}, analyzer.convert());

    this.analyzer = new PathAnalyzer("..");
    assertArrayEquals(new String[] {".."}, analyzer.convert());

    this.analyzer = new PathAnalyzer("/");
    assertArrayEquals(new String[] {"/"}, analyzer.convert());

    this.analyzer = new PathAnalyzer("/b/a/c/");
    assertArrayEquals(new String[] {"/", "b", "a", "c"}, analyzer.convert());

    this.analyzer = new PathAnalyzer("../a");
    assertArrayEquals(new String[] {"..", "a"}, analyzer.convert());
  }

}
