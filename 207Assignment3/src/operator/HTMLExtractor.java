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
 * 
 * The HTMLExtractor takes an HTML and collects the String URL
 * 
 *
 * @author Yu Ang Zhang
 * 
 */

public interface HTMLExtractor {

  /**
   * Extract the live web page from Internet and return the URL as a string
   * 
   * @param url A live GoogleScholar URL
   * @return string representation of URL
   */
  public String getHTML(String url) throws Exception;
}
