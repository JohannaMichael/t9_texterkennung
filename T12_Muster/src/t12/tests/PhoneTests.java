package t12.tests;

import java.io.File;

import java.util.ArrayList;

import org.junit.Assert;
import junit.framework.TestCase;
import t12.lexicon.LexiconGenerator;
import t12.lexicon.LexiconRegexTool;
import t12.lexicon.WordCounter;
import t12.lexicon.WordEntry;
import t12.phones.console.ConsolePhone;
import t12.spinphone.T12Interpreter;
import t12.spinphone.implement.T12InterpreterImplement;
import t12.t9Tree.T9Tree;
import t12.util.T9TreeSaver;

/**
 * <p>
 * Eine Klasse mit JUni-Tests. Wählen Sie den Test, den Sie ausführen möchten,
 * im Package Explorer oder im Outline View von Eclipse aus (z.B.
 * testCreateLexicon()), und wählen Sie im Kontextmenu den Punkt <br>
 * "Run As... &gt; JUnit Test". Daraufhin wird die setUp()-Methode dieser Klasse
 * und anschließend der gewählte Test ausgeführt. So können Sie schnell prüfen,
 * ob Ihre Implementation arbeitet, wie gewünscht. Wenn Sie statt einer der
 * test...-Methoden die Klasse selbst auswählen, werden alle Tests der Klasse
 * ausgeführt.
 * </p>
 * 
 * <p>
 * Eigene Tests können Sie ebenfalls hinzufügen, diese müssen lediglich mit
 * "test" beginnen, um von JUnit als Tests erkannt zu werden.
 * </p>
 * 
 * <p>
 * Als Telefon wird die Klasse ConsolePhone benutzt, als T12Interpreter ist in
 * der Vorlage der ExampleT12Interpreter eingestellt - dies können Sie in der
 * Methode "setUp" ändern und Ihre Implementation testen lassen.
 * </p>
 * 
 * <p>
 * Contributors: <br>
 * Stephan Schwiebert - Initial API and implementation <br>
 * Mihail Atanassov - Refactoring
 * </p>
 * 
 * @see t12.phones.console.ConsolePhone
 * 
 */
public class PhoneTests extends TestCase {

	private ConsolePhone phone;
	private T12Interpreter interpreter;
	private LexiconGenerator lexicongenerator;
	private LexiconRegexTool lexiconRegexTool;
	private WordCounter wordCounter;
	private T9Tree t9Tree;
	private T9Tree t9Tree2;

	/**
	 * In der Setup-Methode wird ein neues ConsolePhone erzeugt und mit einem
	 * ExampleT12Interpreter verbunden. Damit Sie Ihre Implementation von
	 * T12Interpreter testen können, sollten Sie hier eine Instanz Ihrer eigenen
	 * Klasse erzeugen lassen.
	 */
	
	
	public void setUp() {
		phone = new ConsolePhone();
		lexicongenerator = new LexiconGenerator();
		lexiconRegexTool = new LexiconRegexTool();
		wordCounter = new WordCounter();
		t9Tree = new T9Tree();
		
		// Hier den eigenen T12Interpreter zuweisen:
		interpreter = new T12InterpreterImplement();
		phone.connectToT12(interpreter);
		System.out.println("ConsolePhone und Interpreter verbunden.");
		
	}
	/**
	 * Test für den Crawler des SMS Korpus'. Testet ob der übergebene String, der die Texte aller txt Dateien 
	 * enthält, nicht null ist. Testet auch, ob die Anzahl der Dateien 91 ist. 
	 */
	
	public void testlexGen(){
		File source = new File("data");
		String allTexts = lexicongenerator.getAllFileData(source);
		super.assertNotNull(allTexts);
		int size =lexicongenerator.size();
		super.assertEquals(91, size);
	}
	
	/**
	 * Test für die LexiconRegexTool Klasse. Es wird getestet, ob der zurückgegebene String nicht null ist. 
	 */
	public void testRegexTool(){
		File source = new File("data/Chat Korpus/Professionelle Chats/Beratung/Studienberatung RUB");
		String allTexts = lexicongenerator.getAllFileData(source);
		super.assertNotNull(allTexts);
		int size =lexicongenerator.size();
		super.assertEquals(7, size);
		String cleanedTexts = lexiconRegexTool.getRegexCleaner(allTexts);
		super.assertNotNull(cleanedTexts);
		
	}
	
	/**
	 * Testmethode für 't12.ConsolePhone.createLexicon(String, String)' Zunächst
	 * wird die Lexikon-Datei "SpinPhone.lex" gelöscht, falls sie existiert.
	 * Anschließend wird die Methode createLexikon von ConsolePhone aufgerufen.
	 * Der Test schlägt fehl, wenn nach dem Aufruf keine Lexikondatei namens
	 * "SpinPhone.lex" existiert, oder wenn die erzeugte Datei 0 Bytes groß ist.
	 */
	public void testCreateLexicon() {
		File lexFile = new File("SpinPhone.lex");
		if (lexFile.exists()) {
			lexFile.delete();
		}
		phone.createLexicon("data","SpinPhone.lex");
		super.assertTrue(lexFile.exists());
		super.assertTrue(lexFile.length() > 0);
	}

	/**
	 * Test der Methode ConsolePhone.loadLexicon(). Es wird versucht, ein
	 * Lexikon aus der Datei "SpinPhone.lex" zu laden.
	 */
	public void testLoadLexicon() {
		phone.createLexicon("data","SpinPhone.lex");
		phone.loadLexicon("SpinPhone.lex");
		super.assertTrue(new File("SpinPhone.lex").exists());
		String lexText = lexicongenerator.getLoadedFileData("SpinPhone.lex");
		super.assertNotNull(lexText);
		super.assertNotNull(t9Tree);
		
	}
	
	/**
	 *Test um den WordCounter zu testen. Es wird versucht Liste eine Liste von Wörtern zu machen. Diese müssen ein häufiges 
	 *Wort wie "in" haben, aber nicht das Wort "ziit", welches z.B. nur zweimal in allen Texten vorkommt. 
	 */
	public void testWordCounter(){
		phone.createLexicon("data","SpinPhone.lex");
		
		ArrayList<WordEntry> textArrayList = new ArrayList<WordEntry>();
		ArrayList<String> wordArrayList = new ArrayList<String>();
		
		String lexText = lexicongenerator.getLoadedFileData("SpinPhone.lex");
		super.assertNotNull(lexText);
	
		textArrayList = wordCounter.getReducedArray(lexText);

		super.assertNotNull(textArrayList);
		for(int i = 0; i < textArrayList.size(); i++){
			wordArrayList.add(textArrayList.get(i).getWord());
		}
//		for(int i = wordArrayList.size()-100; i < wordArrayList.size(); i++){
//			System.out.println(wordArrayList.get(i));
//		}
		super.assertFalse(wordArrayList.contains("ziit"));
		super.assertTrue(wordArrayList.contains("in"));
		
	}
	
	/**
	 * Zusätzlicher Test um die Klasse T9Tree zu testen ohne vorher alle Textdaten zu laden und auszuwerten. 
	 */
	public void testT9Tree(){
	t9Tree2 = new T9Tree();
		ArrayList<WordEntry> textArrayList = new ArrayList<WordEntry>();
		textArrayList.add(new WordEntry("hallo", 2));
		textArrayList.add(new WordEntry("groß", 4));
		textArrayList.add(new WordEntry("und", 5));
		textArrayList.add(new WordEntry("uns", 6));
		textArrayList.add(new WordEntry("vor", 5));
		textArrayList.add(new WordEntry("top", 3));
		textArrayList.add(new WordEntry("über", 1));
		textArrayList.add(new WordEntry("$", 1)); //darf nicht in den Baum 
		textArrayList.add(new WordEntry("$huhu", 1)); //auch nihct
		
		for (WordEntry entry : textArrayList)
		{
		    t9Tree2.insertWordEntry(entry);
		    
		}
		super.assertNotNull(t9Tree2);
		
		String wordSuggestion = t9Tree2.findTheWord("uns");
		super.assertNotNull(wordSuggestion);
		System.out.println();
		System.out.println("Tadaaa: "+ wordSuggestion);
		t9Tree2.setWordSuggestionBack();
		String alternativeWord = t9Tree2.getAlternative();
		System.out.println(alternativeWord);
		t9Tree2.setWordSuggestionBack();
	}

	/**
	 * Test Methode für 't12.ConsolePhone.displayAlternative()' Zunächst wird
	 * das Wort "uns" eingegeben und geprüft, ob der zurückgegebene String null
	 * ist oder nicht. Anschließend wird nach einer Alternative gefragt, und es
	 * werden folgende Bedingungen überprüft: 1.) Die Alternative darf nicht
	 * null sein 2.) Die Alternative muss sich von der ersten Eingabe
	 * unterscheiden.
	 */
	public void testDisplayAlternative() {
		phone.createLexicon("data","SpinPhone.lex");
		phone.loadLexicon("SpinPhone.lex");
		String word = phone.typeAsNumbers("uns");
		super.assertNotNull(word);
		String word2 = phone.displayAlternative();
		super.assertNotNull(word2);
		super.assertNotSame(word, word2);
		super.assertFalse(word.equals(word2));
	}

	/**
	 * Test Methode for 't12.ConsolePhone.learn(String)' Es wird geprüft, ob das
	 * Telefon in der Lage ist, ein neues Wort zu lernen. Zunächst wird
	 * "informationsverarbeitung" eingegeben und es wird geprüft, dass das Wort
	 * noch nicht vorhanden ist. Anschließend wird es mit ConsolePhone.learn()
	 * gelernt, dann wird geprüft, ob auf erneute Eingabe von
	 * "informationsverarbeitung" ein String zurückgegeben wird, der 1.) nicht
	 * null ist und 2.) "informationsverarbeitung" lautet.
	 */
	public void testLearn() {
		phone.createLexicon("data","SpinPhone.lex");
		phone.loadLexicon("SpinPhone.lex");
		String word = phone.typeAsNumbers("informationsverarbeitung");
		System.out.println(word);
		super.assertNull(word);
		phone.learn("informationsverarbeitung");
		word = phone.typeAsNumbers("informationsverarbeitung");
		super.assertNotNull(word);
		super.assertEquals(word, "informationsverarbeitung");
	}
	
	/**
	 * Zusätzlicher Test um zu testen, ob der aktuelle T9 Baum gespeichert werden kann und er nach dem Laden immernoch der gleiche ist. 
	 */
	public void testSave(){
		phone.createLexicon("data","SpinPhone.lex");
		phone.loadLexicon("SpinPhone.lex");
		T9TreeSaver.save(t9Tree);
		File file = new File("t9tree.bin");
		Assert.assertNotNull(file);
		Assert.assertTrue(file.exists());
		T9Tree t9Tree = T9TreeSaver.loadT9Tree();
		Assert.assertNotNull(t9Tree);
		Assert.assertEquals(this.t9Tree, t9Tree);
	}

}
