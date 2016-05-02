// **********************************************************
// Assignment3:
// CDF user_name: c5zhanim
//
// Author: Yu Ang Zhang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import operator.MockGoogleScholarHTMLExtractor;


public class GoogleScholarHTMLExtractorTest {
  // Use mock object since the raw HTML string is hard to read
  private MockGoogleScholarHTMLExtractor extractor;

  /**
   * Initially set up the test
   */
  @Before
  public void setUp() {
    try {
      extractor = new MockGoogleScholarHTMLExtractor();
    } catch (Exception e) {
      fail("Failed to construct an object");
    }
  }

  /**
   * Test whether the method works as expected
   */
  @Test
  public void testGetHTML() {
    try {
      String first = extractor.getHTML("sample1.html");
      assertEquals("sample1.html", first);
      String second = extractor.getHTML("sample2.html");
      assertEquals("sample2.html", second);

    } catch (Exception e) {
      fail("Failed to obtain info");
    }
  }

}
