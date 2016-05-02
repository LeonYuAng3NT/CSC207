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
import operator.GoogleScholarHTMLExtractor;
import operator.HTMLExtractor;
import operator.Indexi10Extractor;
import operator.MockGoogleScholarHTMLExtractor;

public class Indexi10ExtractorTest {
  private Indexi10Extractor extractor;
  private HTMLExtractor htmlExtractor;

  /**
   * Initially set up the test
   * 
   */
  @Before
  public void setUp() {
    try{
      htmlExtractor=
        new GoogleScholarHTMLExtractor();
      extractor = new Indexi10Extractor(htmlExtractor,"sample1.html");
    }catch(Exception e){
      fail("Invalid Extractor");
    }
  }

  /**
   * Test whether the extract method works as expected
   * 
   */
  @Test
  public void testExtract() {
    try {
      String result = extractor.extract();
      assertTrue(result.equals("12"));
      extractor = new Indexi10Extractor(htmlExtractor,"rs.html");
      String newresult = extractor.extract();

      assertTrue(newresult.equals("1"));
    } catch (Exception e) {
      fail("not a valid URL");
    }
  }

  /**
   * Test whether the constructor is functioning
   * 
   */
  @Test
  public void testIndexi10Extractor() {
    try {
      HTMLExtractor htmlExtractor = new MockGoogleScholarHTMLExtractor();
      extractor = new Indexi10Extractor(htmlExtractor,"sample1.html");
    } catch (Exception e) {
      fail("failed to Construt an object");
    }
  }

}
