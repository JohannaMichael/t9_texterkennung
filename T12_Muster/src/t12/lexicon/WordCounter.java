package t12.lexicon;

import java.util.ArrayList;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * Ein zusätzliches Programm, welches jedes Wort trennt und die Häufigkeit jedes Wortes ermittelt. 
 * </p>
 */
public class WordCounter {

	/**
	 * Die zuvor gereinigten Textdaten werden werden übergeben und als Liste von Worteinträgen 
	 * ( @see t12.lexicon.WordEntry.java ) zurückgegeben. Es wird mit einem Iterator durch die Liste der 
	 * Worteinträge gegangen und die Wörter entfernt, die eine Häufigkeit von 3 oder weniger haben. 
	 * @param lexText Textdaten aus data
	 * @return ArrayList mit Worteinträgen.
	 */
	public ArrayList<WordEntry> getReducedArray(String lexText){
		ArrayList<WordEntry> wordInfoArray = reducingArray(lexText);
		return wordInfoArray;
	}
	
	private ArrayList<WordEntry> reducingArray(String lexText){
		
		ArrayList<WordEntry> wordInfoArray = countingWords(lexText);
		
		Iterator <WordEntry> iterator =  wordInfoArray.iterator();
	    while (iterator.hasNext()) {
	    	WordEntry wordWithFreq = iterator.next();
	    	 if (wordWithFreq.getFrequency().equals(1) || wordWithFreq.getFrequency().equals(2) || wordWithFreq.getFrequency().equals(3)) {
	    		 iterator.remove(); 
	            }
	    }
		
		return wordInfoArray;
	}
	
	/**
	 * Zählt jedes Wort, indem die Einträge von Wörtern im Set mit der ArrayList von Wörtern verglichen werden. 
	 * @param lexText Textdaten aus data
	 * @return ArrayList mit Worteinträgen.
	 */
	
    public ArrayList<WordEntry> getCountedWords(String lexText){
		
    	ArrayList<WordEntry> wordInfoArray = countingWords(lexText);
		return wordInfoArray;
	}
    
	private ArrayList<WordEntry> countingWords(String lexText){
		
		ArrayList<String> textArrayList = textSplitter(lexText);
		
		Set<String> oneTime = new HashSet<String>(textArrayList); //Damit nur jedes Wort einmal vorkommt
		
		WordEntryArray wordInfoArray = new WordEntryArray();
		
		for (String word : oneTime) {
		    int frequency = Collections.frequency(textArrayList, word);
		    wordInfoArray.add(new WordEntry(word, frequency));
		    
		}
		return wordInfoArray;
	}
	
	/**
	 * Unterteilt die Textdaten in einzelne Wörter, indem bei jedem einen oder mehreren Whitespaces der Text aufgeteilt wird.
	 * Die Unterteilungen werden dann einer ArrayList hinzugefügt. 
	 * @param lexText Textdaten aus data
	 * @return ArrayList mit Wörtern
	 */
	public ArrayList<String> getTextInArray(String lexText){
		
		ArrayList<String> textArrayList = textSplitter(lexText);
		return textArrayList;
	}

	private ArrayList<String> textSplitter(String lexText){
		
		ArrayList<String> textArrayList = new ArrayList<String>();
		String[] textArray = lexText.split("\\s+");
		 
		for(int i = 0; i < textArray.length; i++){
			textArrayList.add(textArray[i]);
		}
		
		return textArrayList;
	}
	
	
}
