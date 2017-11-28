package t12.t9Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>
 * Eine Liste von Worteinträgen (T9Leaf). Kann diese, mit Hilfe eines Comparators, nach der Häufigkeit sortieren. 
 * @param reverseOrder Ob die Häufigkeit aufsteigend/absteigend sortiert werden soll
 * @see t12.t9Tree.T9Leaf
 * @see t12.t9Tree.T9Tree 
 * </p>
 */
public class T9LeafArray extends ArrayList<T9Leaf>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728214959758533408L;
	
	public void sortByFrequency(boolean reverseOrder){
	sortFrequency(reverseOrder);
	}

	private void sortFrequency(boolean reverseOrder) {

			
			Comparator<T9Leaf> frequencyComparator = new Comparator<T9Leaf>() {
				@Override
				public int compare(T9Leaf l1, T9Leaf l2) {
					int frequency = Integer.compare(l1.getFrequency(), l2.getFrequency());
						return frequency;
					
				}
			};
			if(reverseOrder == true){
			Collections.sort(this, frequencyComparator.reversed());
			}else{
				Collections.sort(this, frequencyComparator);
			}

	}
	
}
