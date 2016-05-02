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

import a2a.foundation.InputHistory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * The input history test uses to test if the methods are working well.
 *
 * @author Yu Ang Zhang
 * @author Yiming Huang
 * @author Yi Jian Wang
 */
public class InputHistoryTest {

  private InputHistory inputHistory;

  /**
   * Set up this test.
   */
  @Before public void setUp() {
    this.inputHistory = new InputHistory();
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testInputHistory() {
    try {
      this.inputHistory = new InputHistory();
    } catch (Exception e) {
      fail("Failed to create an instance of input history");
    }
  }

  /**
   * Test if the Record method is working as expected.
   */
  @Test public void testRecord() {
    inputHistory.record("working every night");
    inputHistory.record("is tired");
    List<String> output = inputHistory.getUserInputHistory();
    assertEquals(Arrays.asList("working every night", "is tired"), output);
  }

  /**
   * Test if the GetUserInputHistory method is working as expected.
   */
  @Test public void testGetUserInputHistory() {
    List<String> output = inputHistory.getUserInputHistory();
    assertNotNull(output);
  }

}
