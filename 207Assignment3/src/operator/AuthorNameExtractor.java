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
 * The AuthorNameExtractor takes an URL from htmlExtractor and extract author's
 * name from it
 *
 * @author Yu Ang Zhang
 * 
 */

public class AuthorNameExtractor extends ContentExtractor {


  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */

  public AuthorNameExtractor(HTMLExtractor htmlExtractor, String url) {
    super(htmlExtractor, url);
    // Set up the regular expression
    super.setRegex("<span id=\"cit-name-display\" "
        + "class=\"cit-in-place-nohover\">(.*?)</span>");
  }

  /**
   * Extract the author's name from the given URL
   * 
   * @return the string representation of author's name
   */
  @Override
  public String extract() {
    return super.extract();
  }
}
