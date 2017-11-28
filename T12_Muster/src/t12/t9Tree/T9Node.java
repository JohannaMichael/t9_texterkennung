package t12.t9Tree;

import java.io.Serializable;

/**
 * <p>
 * Repräsentiert einen Knoten(von 2-9) im T9 Baum, der wiederum weitere Knoten(von 2-9) enthalten kann. 
 * @see t12.t9Tree.T9Tree 
 * </p>
 */
public class T9Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714640249646737316L;
	
	/**
	 * Jeder Knoten repräsentiert eine Zahl (z.B. two = 2, three = 3, etc.)
	 */
	public T9Node two;
	public T9Node three;
	public T9Node four;
	public T9Node five;
	public T9Node six;
	public T9Node seven;
	public T9Node eight;
	public T9Node nine;
	
	/**
	 * Die eingegebene Zahl/button, die im Knoten zusätzlich abgespeichert wird.
	 */
	public int digit;
	
	/**
	 * Die Häufigkeit des Knotens
	 */
	private int frequency = 0;
	
	/**
	 * Jeder Knoten kann eine Liste von möglichen Blättern (oder Worteinträgen) enthalten. 
	 */
	public T9LeafArray t9leafArray;
	
	public T9Node() {
		// TODO Auto-generated constructor stub
	}

	public T9Node(int digit) {
		// TODO Auto-generated constructor stub
		this.digit = digit;
	}
	
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	/**
	 * Equals Methode von der Klasse T9Node 
	 */
	@Override
	public boolean equals(Object o) {
		 
        if (o == this) {
            return true;
        }
 
        if (o instanceof T9Node) {
        	T9Node a = (T9Node) o;

            if(this.digit == a.digit && this.getFrequency() == a.getFrequency() && 
            		this.t9leafArray.equals(a.t9leafArray)){
            	return true;
            }else{
            	return false;
            }
        } else{
        	return false;
        }

       
    }
	
}
