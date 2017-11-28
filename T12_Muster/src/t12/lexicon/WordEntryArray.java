package t12.lexicon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>
 * Zusätzliche Klasse, die die Worteinträge, mit Hilfe eines Comparators, nach der Wortlänge sortieren kann. 
 * @param reverseOrder Sortiert nach aufsteigender/absteigender Reihenfolge
 * @see t12.lexicon.WordEntry
 * </p>
 */
public class WordEntryArray extends ArrayList<WordEntry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935961348274423631L;
	
	public void sortByWordLength(boolean reverseOrder){
		sortWordLength(reverseOrder);
	}
	
	private void sortWordLength(boolean reverseOrder){
		
		Comparator<WordEntry> stringLengthComparator = new Comparator<WordEntry>() {
			@Override
			public int compare(WordEntry w1, WordEntry w2) {
				int length = Integer.compare(w1.getWord().length(), w2.getWord().length());
					return length;
				
			}
		};
		if(reverseOrder == true){
		Collections.sort(this, stringLengthComparator.reversed());
		}else{
			Collections.sort(this, stringLengthComparator);
		}
	}
	

}
