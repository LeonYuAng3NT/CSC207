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

import operator.CitationNumberExtractor;
import operator.GoogleScholarHTMLExtractor;
import operator.HTMLExtractor;
import operator.MockGoogleScholarHTMLExtractor;

public class CitationNumberExtractorTest {
  private CitationNumberExtractor extractor;
  private GoogleScholarHTMLExtractor htmlExtractor;

  /**
   * Initially set up the test
   * 
   */
  @Before
  public void setUp() throws Exception {
   htmlExtractor=
        new GoogleScholarHTMLExtractor();
    extractor = new CitationNumberExtractor(htmlExtractor,"sample1.html");
  }

  /**
   * Test whether the extract method works as expected
   * 
   */
  @Test
  public void testExtract() {
    try {
      String result = extractor.extract();
      assertTrue(result.equals("437"));
      
      extractor = new CitationNumberExtractor(htmlExtractor,"rs.html");
      String newresult = extractor.extract();

      assertTrue(newresult.equals("33"));
    } catch (Exception e) {
      fail("not a valid URL");
    }
  }

  /**
   * Test whether the constructor is functioning
   * 
   */
  @Test
  public void testCitationNumberExtractor() {
    try {
      HTMLExtractor htmlExtractor = new MockGoogleScholarHTMLExtractor();
      extractor = new CitationNumberExtractor(htmlExtractor,"sample1.html");
    } catch (Exception e) {
      fail("failed to Construt an object");
    }
  }

}
