package t12.t9Tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import t12.lexicon.WordEntry;
import t12.util.KeyConverter;

/**
 * <p>
 * Die T9Tree Klasse repräsentiert den T9 Baum. Hier wird aus der Liste von Wörtern und seinen Häufigkeiten, jedes Wort 
 * abgespeichert. Wiederum kann man auch nach einzelnen Wörtern den Baum durchsuchen lassen.  
 * </p>
 */
public class T9Tree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4863744304656487122L;
	
	/**
	 *Der Wurzelknoten des Baumes.
	 */
	private T9Node root = null;
	
	/**
	 *Hier wird ein Wortvorschlag gespeichert, was beim Suchen eines Wortes gefunden wurde. 
	 */
	private String wordSuggestion = "";
	
	/**
	 *Anzahl der Zeichen in einem Wort.
	 */
	private int charNumber = 0;
	/**
	 *Liste von T9Leaf Objekten, die ein Knoten haben kann. 
	 */
	private List<T9Leaf> leafArray = new ArrayList<T9Leaf>();
	/**
	 *Variable, die die Position in der leafArray Liste bestimmt. 
	 */
	private int arrayPosition = 1;
	
	public void setRootToNull(){
		if(root != null){
			root = null;
		}
	}
	
	public void setCharNrToZero(){
		if(charNumber > 0){
			charNumber = 0;
		}
		
	}
	
	public void setWordSuggestionBack(){
		wordSuggestion = "";
	}
	
	public void setLeafArrayToNull(){
		if(leafArray != null){
			leafArray = null;
		}
	}
	
	public void setArrayPositionToOne(){
		if(arrayPosition > 1){
			arrayPosition = 1;
		}
		
	}
	
	/**
	 *Gibt eine Alternative eines Wortes zurück, falls die leafArray Liste noch weitere T9Leaf Objekte enthält. 
	 *@return Wortalternative
	 */
	public String getAlternative(){
		if(arrayPosition > leafArray.size()-1){
			return "Keine weiteren Alternativen";
		} else{
		wordSuggestion = leafArray.get(arrayPosition).getWord();
		arrayPosition++;
		return wordSuggestion;
		}
	}
	
	/**
	 *Speichert einen Worteintrag im T9 Baum. Falls die Wurzel noch nicht initialisiert wurde, wird dies gemacht. 
	 *@param wordWithFrequency Ein Worteintrag 
	 */
	public void insertWordEntry(WordEntry wordWithFrequency){
		if(root == null){
			root = new T9Node();
			insertWord(root, wordWithFrequency.getWord(), wordWithFrequency.getFrequency());
		} else {
			insertWord(root, wordWithFrequency.getWord(), wordWithFrequency.getFrequency());
		}
	}
	
	/**
	 *Durchläuft jeden Buchstaben eines Wortes und wandelt diesen in eine Zahl um. 
	 *@param currentNode Der zuletzt besuchte Knoten im Baum 
	 *@param word Das Wort aus WordEntry
	 *@param frequency Die Häufigkeit des jeweiligen Wortes
	 */
	private void insertWord(T9Node currentNode, String word, Integer frequency){
		if(charNumber + 1 > word.length()){
				insertDigit(currentNode, -1, word, frequency);
			} else {
				int digit = KeyConverter.convertToNumber(word.charAt(charNumber));
				charNumber++;
				insertDigit(currentNode, digit, word, frequency);
			}
	}
	
	/**
	 *Diese Methode sucht für die nächste Zahl( @param digit ) den passenden Knoten. Die Methode insertWord() wird solange rekursiv
	 *wieder aufgerufen bis das Wort in insertWord() durchlaufen worden ist. Dann wird das Wort im zuletzt besuchten Knoten(T9Node)
	 *in einem Blatt(T9Leaf) abgespeichert. 
	 *@param currentNode Der zuletzt besuchte Knoten im Baum 
	 *@param word Das Wort aus WordEntry
	 *@param frequency Die Häufigkeit des jeweiligen Wortes
	 *@param digit Die zu suchende Zahl aus dem jeweiligen Wort
	 */
	private void insertDigit(T9Node currentNode, int digit, String word, Integer frequency){
		
			switch(digit) {
			
	        case 2: 
	        	if(currentNode.two == null){
	        	currentNode.two = new T9Node(digit);
	        	currentNode.two.setFrequency(frequency); 
	        	insertWord(currentNode.two, word, frequency); //rekursives Aufrufen der Methode
		        break;
	        } else {
	        	int currentNodeFrequency = currentNode.two.getFrequency() + frequency;
	        	currentNode.two.setFrequency(currentNodeFrequency);
	        	insertWord(currentNode.two, word, frequency);
		        break;
	        }
	        	
	        case 3: 
	        	if(currentNode.three == null){
	        	currentNode.three = new T9Node(digit);
	        	currentNode.three.setFrequency(frequency);
	        	insertWord(currentNode.three, word, frequency);
	        	break;
	        } else {
	        	int currentNodeFrequency = currentNode.three.getFrequency() + frequency;
	        	currentNode.three.setFrequency(currentNodeFrequency);
	        	insertWord(currentNode.three, word, frequency);
	        	break;
	        }
	        	
	        case 4: 
	        	if(currentNode.four == null){
		        	currentNode.four = new T9Node(digit);
		        	currentNode.four.setFrequency(frequency);
		        	insertWord(currentNode.four, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.four.getFrequency() + frequency;
		        	currentNode.four.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.four, word, frequency);
		        	break;
		        }
	        	
	        case 5: 
	        	if(currentNode.five == null){
		        	currentNode.five = new T9Node(digit);
		        	currentNode.five.setFrequency(frequency);
		        	insertWord(currentNode.five, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.five.getFrequency() + frequency;
		        	currentNode.five.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.five, word, frequency);
		        	break;
		        }
	        	
	        case 6: 
	        	if(currentNode.six == null){
		        	currentNode.six = new T9Node(digit);
		        	currentNode.six.setFrequency(frequency);
		        	insertWord(currentNode.six, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.six.getFrequency() + frequency;
		        	currentNode.six.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.six, word, frequency);
		        	break;
		        }
	        	
	        case 7: 
	        	if(currentNode.seven == null){
		        	currentNode.seven = new T9Node(digit);
		        	currentNode.seven.setFrequency(frequency);
		        	insertWord(currentNode.seven, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.seven.getFrequency() + frequency;
		        	currentNode.seven.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.seven, word, frequency);
		        	break;
		        }
	        	
	        case 8: 
	        	if(currentNode.eight == null){
		        	currentNode.eight = new T9Node(digit);
		        	currentNode.eight.setFrequency(frequency);
		        	insertWord(currentNode.eight, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.eight.getFrequency() + frequency;
		        	currentNode.eight.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.eight, word, frequency);
		        	break;
		        }
	        	
	        case 9: 
	        	if(currentNode.nine == null){
		        	currentNode.nine = new T9Node(digit);
		        	currentNode.nine.setFrequency(frequency);
		        	insertWord(currentNode.nine, word, frequency);
		        	break;
		        } else {
		        	int currentNodeFrequency = currentNode.nine.getFrequency() + frequency;
		        	currentNode.nine.setFrequency(currentNodeFrequency);
		        	insertWord(currentNode.nine, word, frequency);
		        	break;
		        }
	        	
	        case -1: //Wort zu Ende, hier Spezialanweisung:
	        	if(currentNode.t9leafArray == null){
	        		currentNode.t9leafArray = new T9LeafArray(); //Liste wird für diesen bestimmten Knoten erstellt
	        		currentNode.t9leafArray.add(new T9Leaf(word, frequency)); //dann wird das Wort hinzugefügt
	        		setCharNrToZero();
	        		break;
	        	} else {
	        		currentNode.t9leafArray.add(new T9Leaf(word, frequency));
	        		setCharNrToZero();
	        		break;
	        	}
	        default:  System.out.println("digit ist nicht -1,2,3... 9"); 
	        
			}	
	} 
	
	/**
	 *Das übergebene Wort wird im T9 Baum gesucht. Falls ein passendes Wort gefunden wurde, wird dieses zurückgegeben. 
	 *Falls kein Wort mit dieser Zahlenkombination existiert, wird null zurückgegeben. 
	 *@param word Das zu suchende Wort
	 *@return Ein Wortvorschlag
	 */
	public String findTheWord(String word){
    find(root, word);  
		return wordSuggestion;
	}
	
	/**
	 *Wie schon in der Methode insertWord(), wird das Wort durchlaufen und in Zahlen konvertiert. 
	 *Falls das Wort nur einen Buchstaben hat, wird jener Buchstabe wieder zurückgegeben, damit nicht jedes Mal die 
	 *Antwort kommt, dass jener Buchstabe/Wort nicht im Lexikon existiere. Denn in der Anwendung wird auch ein Buchstabe als Wort 
	 *angesehen, damit dynamisch Wortvorschläge gemacht werden können. 
	 *@param currentNode Der zuletzt besuchte Knoten im Baum 
	 *@param word Das zu suchende Wort
	 */
	private void find(T9Node currentNode, String word){
		if(word.length() == 1){ //wenn das Wort nur einen Buchstaben hat, dann wird dieser Buchstabe einfach zurückgegeben
			wordSuggestion = word;
		}else{
		if(charNumber + 1 > word.length()){
			findWord(currentNode, -1, word);
		} else {
			int digit = KeyConverter.convertToNumber(word.charAt(charNumber));
			charNumber++;
			findWord(currentNode, digit, word);
		}
		}
	}
	
	/**
	 *Ähnlich wie in der Methode insertDigit() wird der Zahlenpfad abgegangen. Wenn dieser zu Ende ist, wird im zuletzt besuchten
	 *Knoten die ArrayList nach der Häufigkeit der Wörter sortiert, sodass das häufigste Wort zuerst ausgegeben wird. 
	 *Wenn der Knoten null ist, oder die Liste null ist, wird null zurückgegeben. 
	 *@param currentNode Der zuletzt besuchte Knoten im Baum 
	 *@param word Das zu suchende Wort
	 *@param digit Die zu suchende Zahl aus dem jeweiligen Wort
	 */
	private void findWord(T9Node currentNode, int digit, String word){
		
		switch(digit) {
		
        case 2: 
        	if(currentNode.two == null){
        		wordSuggestion = null;
        		break;
        } else {
        	int currentNodeFrequency = currentNode.two.getFrequency() + 1; 
        	currentNode.two.setFrequency(currentNodeFrequency);
        	find(currentNode.two, word);
	        break;
        }
        	
        case 3: 
        	if(currentNode.three == null){
        		wordSuggestion = null;
        		break;
        } else {
        	int currentNodeFrequency = currentNode.three.getFrequency() + 1;
        	currentNode.three.setFrequency(currentNodeFrequency);
        	find(currentNode.three, word);
        	break;
        }
        	
        case 4: 
        	if(currentNode.four == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.four.getFrequency() + 1;
	        	currentNode.four.setFrequency(currentNodeFrequency);
	        	find(currentNode.four, word);
	        	break;
	        }
        	
        case 5: 
        	if(currentNode.five == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.five.getFrequency() + 1;
	        	currentNode.five.setFrequency(currentNodeFrequency);
	        	find(currentNode.five, word);
	        	break;
	        }
        	
        case 6: 
        	if(currentNode.six == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.six.getFrequency() + 1;
	        	currentNode.six.setFrequency(currentNodeFrequency);
	        	find(currentNode.six, word);
	        	break;
	        }
        	
        case 7: 
        	if(currentNode.seven == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.seven.getFrequency() + 1;
	        	currentNode.seven.setFrequency(currentNodeFrequency);
	        	find(currentNode.seven, word);
	        	break;
	        }
        	
        case 8: 
        	if(currentNode.eight == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.eight.getFrequency() + 1;
	        	currentNode.eight.setFrequency(currentNodeFrequency);
	        	find(currentNode.eight, word);
	        	break;
	        }
        	
        case 9: 
        	if(currentNode.nine == null){
        		wordSuggestion = null;
        		break;
	        } else {
	        	int currentNodeFrequency = currentNode.nine.getFrequency() + 1;
	        	currentNode.nine.setFrequency(currentNodeFrequency);
	        	find(currentNode.nine, word);
	        	break;
	        }
        	
        case -1: //Wort zu Ende, hier Spezialanweisung
        	if(currentNode.t9leafArray == null){
        		setCharNrToZero();
        		wordSuggestion = null; //keine Liste am Knoten vorhanden, also ist das Wort auch nicht vorhanden
        		break;
        	} else {
        		currentNode.t9leafArray.sortByFrequency(true); //die Liste wird noch einmal nach der Häufigkeit der Wörter sortiert
        		leafArray = currentNode.t9leafArray; //die vorhandene Liste wird auf die Variable learArray abgespeichert
        		wordSuggestion = currentNode.t9leafArray.get(0).getWord(); //Zuerst wird immer das erste Wort der Liste zurückgegeben
        		setCharNrToZero();
        		break;
        	}
        default:  System.out.println("digit ist nicht -1,2,3... 9"); 
        
		}	
}
	
	@Override
	public boolean equals(Object o) {
		 
        if (o == this) {
            return true;
        }
 
        if (o instanceof T9Tree) {
        	T9Tree a = (T9Tree) o;

            if(this.wordSuggestion.equals(a.wordSuggestion) && this.leafArray.equals(a.leafArray) 
            		&& this.charNumber == a.charNumber && this.arrayPosition == a.arrayPosition){
            	return true;
            }else{
            	return false;
            }
        } else{
        	return false;
        }

       
    }
	
}
