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

import operator.FirstThreePublicationCollector;
import operator.GoogleScholarHTMLExtractor;
import operator.HTMLExtractor;
import operator.MockGoogleScholarHTMLExtractor;

public class FirstThreePublicationCollectorTest {
  private FirstThreePublicationCollector extractor;
  private GoogleScholarHTMLExtractor htmlExtractor;


  /**
   * Initially set up the test
   *
   */
  @Before
  public void setUp() {
    try {
      htmlExtractor = new GoogleScholarHTMLExtractor();
      extractor =
          new FirstThreePublicationCollector(htmlExtractor, "sample1.html");
    } catch (Exception e) {
      fail("failed to set up");
    }
  }

  /**
   * Test whether the method work as expected or not
   * 
   */
  @Test
  public void testCollectInfo() {
    try {
      extractor.collectInfo();
      assertEquals(extractor.getContentInfo().get(0),
          "Bioclipse: an open source workbench for chemo-and bioinformatics");
      extractor.getContentInfo().clear();
      extractor = new FirstThreePublicationCollector(htmlExtractor, "rs.html");
      extractor.collectInfo();
      assertEquals(extractor.getContentInfo().get(0),
          "On fast exploration in 2D and 3D terrains with multiple robots");

    } catch (Exception e) {
      fail("Argument is not a valid URL");
    }

  }

  /**
   * Test the constructor
   */
  @Test
  public void testFirstThreePublicationCollector() {
    try {
      HTMLExtractor htmlExtractor = new MockGoogleScholarHTMLExtractor();
      extractor =
          new FirstThreePublicationCollector(htmlExtractor, "sample1.html");
    } catch (Exception e) {
      fail("failed to construt an object");
    }
  }

}
