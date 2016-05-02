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
package operator;


/**
 * The Indexi10Extractor takes an URL from htmlExtractor and extract the number
 * i10-index after 2009
 *
 * @author Yu Ang Zhang
 * 
 */
public class Indexi10Extractor extends ContentExtractor {



  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */
  public Indexi10Extractor(HTMLExtractor htmlExtractor, String url) {
    super(htmlExtractor, url);
    // Set the regular expression
    super.setRegex("i10-index</a></td><td class=\"cit-borderleft cit-data"
        + "\">" + "(.*?)</td>");
  }


  /**
   * Extract the index i-10 number since 2009 from the given URL
   * 
   * @return the index i-10 number in string representation
   */
  @Override
  public String extract() {
    return super.extract();
  }

}
