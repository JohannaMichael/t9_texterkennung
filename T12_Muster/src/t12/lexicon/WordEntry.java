package t12.lexicon;

/**
 * <p>
 * Zusätzliche Klasse, die einen Worteintrag, das Wort und seine Häufigkeit, abspeichert. 
 * </p>
 */
public class WordEntry {

	private String word;
	private Integer frequency;
	
	public WordEntry(){
		
	}

	public WordEntry(String word, Integer frequency){
		this.word = word;
		this.frequency = frequency;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public Integer getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
	
	
}
