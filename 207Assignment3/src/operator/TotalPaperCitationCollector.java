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

import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**
 * The TotalPaperCitationCollectors takes an URL from htmlExtractor and collects
 * the total number of paper citations
 *
 * @author Yu Ang Zhang
 * 
 */
public class TotalPaperCitationCollector extends ContentInfoCollector {

  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */

  public TotalPaperCitationCollector(HTMLExtractor htmlExtractor, String url) {
    super(htmlExtractor, url);

    super.setRegex("<td id=\"col-citedby\"><a class=\"cit-dark-link\" "
        + "href=\"(.*?)>(.*?)</a></td>");
  }

  /**
   * Collect the total number of paper citations
   * 
   */
  @Override
  public void collectInfo() {

    Pattern patternObject = Pattern.compile(super.getRegex());
    Matcher matcherObject = patternObject.matcher(super.getHTMLString());
    while (matcherObject.find()) {
      // Find and add the string of paper citation number to the collection
      String number = matcherObject.group(2);
      super.getContentInfo().add(number);

    }

  }
}
