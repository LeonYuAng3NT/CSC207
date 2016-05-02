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
import operator.MockGoogleScholarHTMLExtractor;
import operator.OutputFormatGenerator;

public class OutputFormatGeneratorTest {
  private OutputFormatGenerator generator;

  /**
   * Initially set up the test
   */
  @Before
  public void setUp() {
    try {
      GoogleScholarHTMLExtractor googleExtractor =
          new GoogleScholarHTMLExtractor();
      String[] args = new String[] {"sample1.html"};
      generator = new OutputFormatGenerator(googleExtractor, args);
    } catch (Exception e) {

    }
  }

  /**
   * Test the constructor
   * 
   */
  @Test
  public void testOutputFormatGenerator() {
    try {
      // Use mock object to test the constructor
      MockGoogleScholarHTMLExtractor googleExtractor =
          new MockGoogleScholarHTMLExtractor();
      String[] args = new String[] {"sample1.html"};
      generator = new OutputFormatGenerator(googleExtractor, args);
    } catch (Exception e) {

    }
  }

  /**
   * Test whether the method generates an output
   */

  @Test
  public void testGenerate() {
    try {
      String result = generator.generate();

      // test whether the output of co-authors is correct
      assertTrue(result.contains("Adam Ameur\n" + "Antony John Williams\n"
          + "Christoph Steinbeck\n" + "Egon Willighagen\nJanna Hastings\n"
          + "Jonathan Alvarsson\nKomorowski Jan\nNina Jeliazkova\n"
          + "Noel M. O'Boyle\nRajarshi Guha\nSam Adams\n"
          + "Samuel Lampa\nSean Ekins\n"
          + "Valentin Georgiev\ngilleain torrance\n"));
      // test whether the output of name is correct
      assertTrue(result.contains("Ola Spjuth"));
      // test whether the output of publication is correct
      assertTrue(result.contains("1-  Bioclipse: an open source workbench "
          + "for chemo-and bioinformatics"));
    } catch (Exception e) {
      fail("URL is invalid");
    }
  }

}
