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
 * The OutputFormatGenerator generates the formatted final output
 *
 * @author Yu Ang Zhang
 * 
 */
public class OutputFormatGenerator {
  private String[] args;
  private ContentExtractor contentExtractor;
  private ContentInfoCollector infoCollector;
  private CoAuthorListGenerator listGenerator;
  private HTMLExtractor htmlExtractor;

  /**
   * Construct an OutputFormatGeneartor and store the HTMLExtractor within it
   * 
   * @param htmlExtractor a HTMLExtractor object for extracting a HTML string
   * @param args argument given from the system
   */
  public OutputFormatGenerator(HTMLExtractor htmlExtractor, String[] args) {
    this.args = args;
    listGenerator = new CoAuthorListGenerator();
    this.htmlExtractor = htmlExtractor;
  }

  /**
   * Generate the string representation of the whole output
   * 
   * @return the output of the whole content
   */

  public String generate() {
    String result = "";
    try {
      String URLS[] = args[0].split(",");
      for (String url : URLS) {
        result += "--------------------------------------"
            + "---------------------------------\n";
        // extract Author's name
        contentExtractor = new AuthorNameExtractor(htmlExtractor, url);
        String authorsName = "1. Name of Author: \n" + "        "
            + contentExtractor.extract() + "\n";

        // extract number of Citation
        contentExtractor = new CitationNumberExtractor(htmlExtractor, url);
        String numberOfCitation = "2. Name of All Citaions:\n" + "        "
            + contentExtractor.extract() + "\n";

        // extract number of i10-index after 2009;
        contentExtractor = new Indexi10Extractor(htmlExtractor, url);
        String i10Index = "3. Number of i10-index after 2009: \n" + "        "
            + contentExtractor.extract() + "\n";
        // final format per scholars
        result += authorsName + numberOfCitation + i10Index + deepGenerate(url);

      }
      // present the whole co-author list
      result += "\n------------------------------------"
          + "-----------------------------------\n"
          + listGenerator.produceAllCoAuthors();
    } catch (Exception e) {
      System.out.println("Did you change the run configuration in"
          + "Eclipse for argument0 and argument 1?");
    }
    return result;

  }

  /**
   * Helper method for generate(); It obtains the collection of info and present
   * them in string representation
   * 
   * @param url String representation of a given URL
   * @return the string representation of specified information stored in
   *         infoCollector
   * 
   */

  private String deepGenerate(String url) {
    String result = "";

    // collect number of total co-authors
    infoCollector = new TotalCoAuthorsCollector(htmlExtractor, url);
    infoCollector.collectInfo();
    for (int i = 0; i < infoCollector.getContentInfo().size(); i++) {
      listGenerator.getCoAuthorList()
          .add(infoCollector.getContentInfo().get(i));

    }
    String totalCoAuthors = "6. Total Co-Authors: \n" + "        "
        + infoCollector.getContentInfo().size() + "\n";
    result = generatePublicationsString(url) + generatePaperCitationString(url)
        + totalCoAuthors;

    return result;
  }

  /**
   * A helper method for generating the String representation of first three
   * publications
   *
   * @param url String representation of a given URL
   * @return the string representation of first three publications
   */
  private String generatePublicationsString(String url) {
    // Collect titles for first 3 publications
    infoCollector = new FirstThreePublicationCollector(htmlExtractor, url);
    infoCollector.collectInfo();
    String publications = "4. Title of the first 3 publications:  \n";
    if (infoCollector.getContentInfo().size() >= 3) {
      for (int i = 0; i < 3; i++) {
        publications += "        " + (i + 1) + "-  "
            + infoCollector.getContentInfo().get(i) + "\n";
      }
    } else
      // present every publications if the total is less than 3
      for (int i = 0; i < infoCollector.getContentInfo().size(); i++) {
        publications += "        " + (i + 1) + "-  "
            + infoCollector.getContentInfo().get(i) + "\n";
      }
    return publications;
  }

  /**
   * A helper method for generating the String representation of total number of
   * paper citations
   * 
   * @param url String representation of a given URL
   * @return the string representation of first three publications
   */
  private String generatePaperCitationString(String url) {
    // collect first 5 paper citations
    infoCollector = new TotalPaperCitationCollector(htmlExtractor, url);
    infoCollector.collectInfo();
    int PaperCitationNumber = 0;
    String totalPaperCitation = "5. Total paper citation ( first 5 papers): \n";
    // add first 5 paper citations number together
    if (infoCollector.getContentInfo().size() >= 5) {
      for (int i = 0; i < 5; i++) {
        int number = Integer.parseInt(infoCollector.getContentInfo().get(i));
        PaperCitationNumber += number;
      }
    } else
      // add every citations if the total is less than 5
      for (int i = 0; i < infoCollector.getContentInfo().size(); i++) {
        int number = Integer.parseInt(infoCollector.getContentInfo().get(i));
        PaperCitationNumber += number;
      }
    totalPaperCitation += "        " + PaperCitationNumber + "\n";
    return totalPaperCitation;
  }

}

