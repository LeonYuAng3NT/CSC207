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

import operator.AuthorNameExtractor;
import operator.GoogleScholarHTMLExtractor;
import operator.HTMLExtractor;
import operator.MockGoogleScholarHTMLExtractor;

public class AuthorNameExtractorTest {
  private AuthorNameExtractor extractor;
  private GoogleScholarHTMLExtractor htmlExtractor;

  /**
   * Initially set up the test
   * 
   */
  @Before
  public void setUp() {
    htmlExtractor=
        new GoogleScholarHTMLExtractor();
    extractor = new AuthorNameExtractor(htmlExtractor,"sample1.html");

  }
  
  /**
   * Test whether the extract method works as expected
   * 
   */
  @Test
  public void testExtract(){
    try {
      String result = extractor.extract();

      assertTrue(result.equals("Ola Spjuth"));
      extractor = new AuthorNameExtractor(htmlExtractor,"rs.html");
      String newresult = extractor.extract();

      assertTrue(newresult.equals("rahul sawhney"));
    } catch (Exception e) {
      fail("not a valid URL");
    }
  }
/**  Test the constructor
 *  
 */
  @Test
  public void testAuthorNameExtractor() {
    try {
      //Use mock object to test the constructor
      //No exception should be thrown
      HTMLExtractor htmlExtractor =
          new MockGoogleScholarHTMLExtractor();
      extractor = new AuthorNameExtractor(htmlExtractor,"sample1.html");
    } catch (Exception e) {
      fail("failed to Construt an object");
    }
  }

}
