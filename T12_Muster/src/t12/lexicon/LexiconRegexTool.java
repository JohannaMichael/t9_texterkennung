package t12.lexicon;

/**
 * <p>
 * Diese Klasse kann die eingelesenen Textdaten von Zeichen wie zum Beispiel ".,:", reinigen.
 * </p>
 */
public class LexiconRegexTool {
	
	/**
	 * Dieser Regex kann alle Satz und Sonderzeichen erkennen und durch replaceAll mit einem Leerzeichen ersetzen.
	 */
	private String regexPunctuation = "\\p{Punct}|\\´|\\«|\\»";
	
	/**
	 * Hier können alle Zahlen erkannt werden.
	 */
	private String regexDigits = "\\p{Digit}";
	
	/**
	 * Erkennt Wörter die nur einen oder mehr als 25 Buchstaben haben. 
	 */
	private String regexWordLength = "\\b\\w{1}\\b||\\b\\w{26,}\\b";
	
	/**
	 * Hier werden Wörter erkannt die mehr als zwei gleiche Buchstaben hintereinander haben. Wie zum Beispiel hhhdddgggll oder iii.
	 */
	private String regexThreeLetters = "\\b[a-z]*(([a-z\u00e4\u00f6\u00fc\u00df])\\2\\2+)[a-z]*\\b";
	
	/**
	 * Es stellte sich raus, dass in der lex Datei leider die "new lines" nicht beachtet und Sätze 
	 * wie "betritt den RaumEr sieht Cali" entstehen. Daher müssen die new lines, wie in diesem Regex, 
	 * noch einmal durch einen tab ersetzt werden.
	 */
	private String regexNewLine = "\\n";
	
	/**
	 * Reinigt den übergebenen String (also die data Textdaten), indem es jeden gefundenen Regex mit einem Leerzeichen, 
	 * oder einem leeren String ersetzt. 
	 * @param allTexts alle Textdaten aus data
	 * @return die gereinigten Textdaten 
	 */
	public String getRegexCleaner(String allTexts){
		
		String cleanedTexts = regexCleaner(allTexts);
		return cleanedTexts;
	}
	
	private String regexCleaner(String allTexts){
			String cleanedTexts1 = allTexts.toLowerCase().replaceAll(regexPunctuation," ");
			String cleanedTexts2 = cleanedTexts1.replaceAll(regexDigits, "");
			String cleanedTexts3 = cleanedTexts2.replaceAll(regexWordLength, "");
			String cleanedTexts4 = cleanedTexts3.replaceAll(regexThreeLetters, "");
			String cleanedTexts5 = cleanedTexts4.replaceAll(regexNewLine, " ");
		
			
		return cleanedTexts5;
	}
}
