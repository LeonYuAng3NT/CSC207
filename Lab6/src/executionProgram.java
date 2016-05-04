
import java.util.*;
public class executionProgram {

	/**
	 * @param args
	 * @throws ClassNoFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	public static void main(String[] args) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException{

		Hashtable<String, String> classNames = new Hashtable<String, String>();
		
		classNames.put("pdf", "openPDF");
        classNames.put("Word", "OpenWord");
        classNames.put("Txt", "openTxt");
        String objectpdf = "pdf"; 
        String objectWord = "Word";
        String objectTxt = "Txt";
        String valueofkeypdf =classNames.get(objectpdf);
        String valueofkeyWord =classNames.get(objectWord);
        String valueofkeyTxt =classNames.get(objectTxt);
        filee object1 = (filee) Class.forName(valueofkeypdf).newInstance();
        object1.NameOfFile();
        object1.readSingleLine();
        filee object2 = (filee) Class.forName(valueofkeyWord).newInstance();
        object2.NameOfFile();
        object2.readSingleLine();
        filee object3 = (filee) Class.forName(valueofkeyTxt).newInstance();
        object3.NameOfFile();
        object3.readSingleLine();

	}
}
