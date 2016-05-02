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
 * The CitationNumberExtractor takes an URL from htmlExtractor and extract total
 * citations' number
 * 
 * @author Yu Ang Zhang
 * 
 */
public class CitationNumberExtractor extends ContentExtractor {


  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */
  public CitationNumberExtractor(HTMLExtractor htmlExtractor, String url) {
    super(htmlExtractor, url);
    super.setRegex("Citations</a></td><td class="
        + "\"cit-borderleft cit-data\">(.*?)</td>");

  }

  /**
   * Extract the citation number from the given URL
   * 
   * @return the string representation of total citation number
   */
  @Override
  public String extract() {
    return super.extract();
  }

}
