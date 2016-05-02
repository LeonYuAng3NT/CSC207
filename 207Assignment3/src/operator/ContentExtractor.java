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
// *********************************************************
package operator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * The ContentExtractor is an abstract class that takes an URL extracted from
 * HTMLExtractor and extracts info from it
 *
 * @author Yu Ang Zhang
 * 
 */

public abstract class ContentExtractor {

  private HTMLExtractor htmlExtractor;
  private String htmlString;
  private String regex;

  /**
   * Construct the ContentExtractor object
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */
  public ContentExtractor(HTMLExtractor htmlExtractor, String url) {
    try {
      this.htmlExtractor = htmlExtractor;
      // Extract the HTML String and store it
      htmlString = this.htmlExtractor.getHTML(url);
      htmlString = StringEscapeUtils.unescapeHtml3(this.htmlString);


    } catch (Exception e) {
      System.out.println("invalid URL");
    }
  }

  /**
   * Extract specified content from given URL
   * 
   * @return the string representation of specified content in string form
   *
   */
  public String extract() {
    String info = null;
    Pattern patternObject = Pattern.compile(regex);
    Matcher matcherObject = patternObject.matcher(htmlString);

    while (matcherObject.find()) {
      info = matcherObject.group(1);
    }
    return info;
  };

  /**
   * Obtain the HTMLExtractor for further use
   * 
   * @return HTMLExtractor within the ContentExtractor
   */
  public HTMLExtractor getHTMLExtractor() {
    return htmlExtractor;
  }

  /**
   * Obtain the htmlString for further use
   * 
   * @return HTMLString within the ContentExtractor
   */
  public String getHTMLString() {
    return htmlString;
  }

  /**
   * Set the regular expression for further use
   * 
   */
  public void setRegex(String regex) {
    this.regex = regex;
  }

}
