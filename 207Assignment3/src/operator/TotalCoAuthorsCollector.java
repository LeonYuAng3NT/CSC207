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
 * The TotalCoAuthorsCollect takes an URL from htmlExtractor and collect the
 * total number of co-authors
 *
 * @author Yu Ang Zhang
 * 
 */
public class TotalCoAuthorsCollector extends ContentInfoCollector {


  /**
   * Call the super class constructor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param url a live URL
   */

  public TotalCoAuthorsCollector(HTMLExtractor htmlExtractor, String url) {
    super(htmlExtractor, url);
    super.setRegex("<a class=\"cit-dark-link\" "
        + "href=\"http://scholar.google.ca/citations(.*?)\" "
        + "title=\"(.*?)\">(.*?)</a><br>");
  }
  




  /**
   * Collect the total number of co-authors
   * 
   */
  @Override
  public void collectInfo() {
    String name = null;
    String title = null;

    Pattern patternObject = Pattern.compile(super.getRegex());
    Matcher matcherObject = patternObject.matcher(super.getHTMLString());

    while (matcherObject.find()) {
      title = matcherObject.group(3);
      name = matcherObject.group(2);
      // check the similarity of the second and last string
      // add to ContentInfo if they are the same
      if (name.equals(title)) {
        //do nothing if the ContentInfo already contained name
        if(!super.getContentInfo().contains(name)){
          super.getContentInfo().add(name);
        }
      }
    }
  }

}
