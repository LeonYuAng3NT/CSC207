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
 * The FirstThreePublicationCollector takes an URL from htmlExtractor and
 * collects the title of first three publications
 *
 * @author Yu Ang Zhang
 * 
 */
public class FirstThreePublicationCollector extends ContentInfoCollector {

  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */
  public FirstThreePublicationCollector(HTMLExtractor htmlExtractor,
      String url) {
    super(htmlExtractor, url);
    super.setRegex("<td id=\"col-title\"><a href=\"(.*?)\" "
        + "class=\"cit-dark-large-link\">(.*?)</a><br>");
  }

  /**
   * Collect the title of first three publications from given URL
   * 
   */
  @Override
  public void collectInfo() {
    String number = null;

    Pattern patternObject = Pattern.compile(super.getRegex());
    Matcher matcherObject = patternObject.matcher(super.getHTMLString());

    while (matcherObject.find()) {
      number = matcherObject.group(2);
      super.getContentInfo().add(number);
    }
  }

}
