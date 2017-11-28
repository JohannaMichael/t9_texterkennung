package t12.spinphone.implement;

import t12.spinphone.*;
import t12.t9Tree.T9Tree;
import t12.util.KeyConverter;
import t12.util.T9TreeSaver;

import java.io.File;
import java.util.ArrayList;

import t12.lexicon.*;

/**
 * <p>
 * Hier wird das Interface T12Interpreter implementiert.
 * </p>
 */
public class T12InterpreterImplement implements T12Interpreter{
	
	public LexiconGenerator lexiconGenerator = new LexiconGenerator();
	public WordCounter wordCounter = new WordCounter();
	public T9Tree t9tree = new T9Tree();
	
	/**
	 *Das aktuelle Wort, welches in der Methode buttonPressed() eingegeben wird. 
	 */
	public String currentWord = "";
	
	/**
	 *True, wenn statt Buchstaben, Zahlen in der Anwendung eingegeben werden soll. 
	 */
	public boolean letterOrNumber = false;
	
	/**
	 *True, wenn statt Kleinbuchstaben, nur Großbuchstaben verwendet werden sollen.
	 */
	public boolean capitalLetter = false;

	/**
	 *Die Methode findet Wörter im T9 Baum. currentWord wird hier durch die Zahleingabe in der Anwendung bestimmt.
	 *Gleichzeitig wird hier entschieden, ob der Wortvorschlag (wordSuggestion) zum Beispiel groß 
	 *geschrieben werden soll, oder nur in Zahlen ausgegeben werden soll. 
	 *
	 *Ich habe zwei verschiedene Funktionsweisen für die Klein/Großbuchstaben Taste geschrieben. Einmal so, dass 
	 *immer der erste Buchstabe groß geschrieben wird, und einmal so, dass alle Buchstaben groß geschrieben werden. 
	 */
	@Override
	public String buttonPressed(int number) {
		
		if(letterOrNumber == true){
			String numberSuggestion = String.valueOf(number);
			currentWord = currentWord + numberSuggestion;
			return currentWord;
		}else{
		String currentLetter = KeyConverter.convertToKey(number);
		currentWord = currentWord + currentLetter;
		String wordSuggestion = t9tree.findTheWord(currentWord);
		if(capitalLetter == true){
			
			// 1:
			if(wordSuggestion != null){
				char upperLetter = Character.toUpperCase(wordSuggestion.charAt(0));
				if(wordSuggestion.length() > 1){
					return upperLetter + wordSuggestion.substring(1);
				} else{
					return "" + upperLetter;
				}
			} else{
				return wordSuggestion;
			}
			
			// 2:
//			if(wordSuggestion != null){
//			return wordSuggestion.toUpperCase();
//			} else{
//				return wordSuggestion;
//			}
		}else{
		return wordSuggestion;
		}
		}
	}

	/**
	 *Das Lexikon wird durch die Klasse LexikonGenerator generiert. 
	 *@see t12.lexicon.LexiconGenerator
	 */
	@Override
	public void generateLexicon(String pathToTexts, String lexFileDestination) { 
		// TODO Auto-generated method stub
		if(pathToTexts == null || lexFileDestination == null) { 
			throw new IllegalArgumentException("Die übergebenen Parameter dürfen nicht null sein!");
		}
		File sourceFile = new File(pathToTexts);
		lexiconGenerator.setAllFileData(sourceFile, lexFileDestination); 
	}

	/**
	 *Das generierte Lexikon wird eingelesen und die Wörter gezählt. Die gezählten Wörter werden anschließend in den T9 Baum 
	 *gespeichert.  
	 *@see t12.lexicon.WordCounter
	 */
	@Override
	public void loadLexicon(String lexFilePath) {
		// TODO Auto-generated method stub
		if(lexFilePath == null) {
			throw new IllegalArgumentException("Der Pfad zur Lexikondatei darf nicht null sein!");
		}
		
		String lexText = lexiconGenerator.getLoadedFileData(lexFilePath);
		ArrayList<WordEntry> textArrayList = wordCounter.getReducedArray(lexText);
		
		for (WordEntry entry : textArrayList)
		{
		    t9tree.insertWordEntry(entry);
		    
		}
		
	}

	/**
	 *Alternative des aktuellen Wortes. 
	 *@see t12.t9Tree.T9Tree
	 */
	@Override
	public String getAlternative() {
		// TODO Auto-generated method stub
		String alternativeWord = t9tree.getAlternative();
		return alternativeWord;
	}

	/**
	 *Lernt das eingegebene Wort. Dazu benötigt es nur die schon vorhandene Methode, die ein Wort im T9 Baum speichert. 
	 *@see t12.t9Tree.T9Tree
	 */
	@Override
	public void learn(String newWord) {
		// TODO Auto-generated method stub
		t9tree.insertWordEntry(new WordEntry(newWord, 1));
		
	}

	/**
	 *Setzt die Variable capitalLetter auf true oder false. 
	 */
	@Override
	public void asteriskButtonPressed() {
		// TODO Auto-generated method stub
		if(capitalLetter == false){
			capitalLetter = true;
		}else{
			capitalLetter = false;
		}
	}

	/**
	 *Setzt die Variable letterOrNumber auf true oder false. 
	 */
	@Override
	public void numberSignButtonPressed() {
		// TODO Auto-generated method stub
		if(letterOrNumber == false){
			letterOrNumber = true;
		}else{
			letterOrNumber = false;
		}
		
	}

	/**
	 *Ändert das aktuelle Wort so, dass der letzte Buchstabe fehlt. Das neue aktuelle Wort wird wiederum im T9 Baum gesucht 
	 *und ausgegeben. 
	 */
	@Override
	public String delButtonPressed() {
		currentWord = currentWord.substring(0, currentWord.length()-1);
		String wordSuggestion = t9tree.findTheWord(currentWord);
		return wordSuggestion;
	}

	@Override
	public void wordCompleted() {
		currentWord = "";
		t9tree.setWordSuggestionBack();
	}

	@Override
	public String getAuthorName() {
		return "Johanna Michael";
	}
	
	/**
	 *Der aktuelle T9 Baum kann gespeichert werden. 
	 *@see t12.util.T9TreeSaver
	 */
	@Override
	public void saveT9Tree(){
		T9TreeSaver.save(t9tree);
	}
	
	/**
	 *Jener Baum kann wiederum auch geladen werden. 
	 *@return den zuvor gespeicherten Baum. 
	 *@see t12.util.T9TreeSaver
	 */
	@Override
	public T9Tree loadT9Tree(){
		return T9TreeSaver.loadT9Tree();
	}
	

}
