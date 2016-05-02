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

import driver.JShell;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * The JShell test uses to test if the JShell are working as described.
 *
 * @author Yi Jian Wang
 */
public class JShellTest {

  /**
   * Test to create an instance of JShell.
   */
  @Test public void testJShell() {

    try {
      JShell jShell = new JShell();
      assertNotNull(jShell);

    } catch (Exception e) {
      fail("Failed to create an instance of JShell.");
    }
  }
}
