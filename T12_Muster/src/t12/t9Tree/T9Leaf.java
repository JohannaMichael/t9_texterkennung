package t12.t9Tree;

import java.io.Serializable;

/**
 * <p>
 * Die Klasse, die ein Blatt(also einen Worteintrag) im T9 Baum repräsentiert. Wird in einer Liste von T9Leaf Objekten 
 * gespeichert, die über einen T9Node (Knoten) zu erreichen ist. 
 * @see t12.t9Tree.T9LeafArray
 * @see t12.t9Tree.T9Tree 
 * </p>
 */
public class T9Leaf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 420179874548748937L;
	
	private String word;
	private Integer frequency;
	
	public T9Leaf(){
		
	}
	
	public T9Leaf(String word, Integer frequency){
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
	
	/**
	 * Equals Methode von der Klasse T9Leaf
	 */
	@Override
	public boolean equals(Object o) {
		 
        if (o == this) {
            return true;
        }
 
        if (o instanceof T9Leaf) {
        	T9Leaf a = (T9Leaf) o;

            if(this.getWord().equals(a.getWord()) && this.getFrequency().equals(a.getFrequency())){
            	return true;
            }else{
            	return false;
            }
        } else{
        	return false;
        }

       
    }
	
	
}
