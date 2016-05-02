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
import operator.MockGoogleScholarHTMLExtractor;
import operator.TotalPaperCitationCollector;

public class TotalPaperCitationCollectorTest {
  private TotalPaperCitationCollector collector;
  private HTMLExtractor htmlExtractor;
  /**
   * Initially set up the test
   */
  @Before
  public void setUp() {
    try{
    htmlExtractor =
        new GoogleScholarHTMLExtractor();
     collector = new TotalPaperCitationCollector(htmlExtractor,"sample1.html");
    }catch(Exception e){
      fail("Invalid Extractor set up");
    }
  }
  /** Test whether the method works as expected or not
   *  
   */
  @Test
  public void testCollectInfo() {
    try {
      collector.collectInfo();
      String result = collector.getContentInfo().get(0);
      assertEquals(result,"88");

      collector = new TotalPaperCitationCollector(htmlExtractor,"rs.html");
      collector.collectInfo();
      String secondResult = collector.getContentInfo().get(0);
      assertEquals(secondResult,"16");
    } catch (Exception e) {
      fail("not a valid URL");
    }
  }
  /** Test the constructor with Mock object
   * 
   */
  @Test
  public void testTotalPaperCitationCollector() {
    try {
      HTMLExtractor htmlExtractor = new MockGoogleScholarHTMLExtractor();
      collector = new TotalPaperCitationCollector(htmlExtractor,"rs.html");
    } catch (Exception e) {
      fail("failed to Construt an object");
    }
  }

}
