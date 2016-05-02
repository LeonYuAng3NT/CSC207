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


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The GoogleHTMLExtractor specifically extract URL string from Google Scholar
 * HTML
 * 
 *
 * @author Yu Ang Zhang
 */
public class GoogleScholarHTMLExtractor implements HTMLExtractor {

  /**
   * Extract the live Google Scholar URL from the Internet and return the URL as
   * a string
   * 
   * @param url A live GoogleScholar URL
   * @return string representation of URL
   */
  public String getHTML(String url) throws Exception {
    // create object to store html source text as it is being collected
    StringBuilder html = new StringBuilder();
    // open connection to given url
    URL newurl = new File(url).toURI().toURL();
    // create BufferedReader to buffer the given url's HTML source
    BufferedReader htmlbr =
        new BufferedReader(new InputStreamReader(newurl.openStream()));
    String line;
    // read each line of HTML code and store in StringBuilder
    while ((line = htmlbr.readLine()) != null) {
      html.append(line);
    }
    htmlbr.close();
    // convert StringBuilder into a String and return it
    return html.toString();
  }
}

