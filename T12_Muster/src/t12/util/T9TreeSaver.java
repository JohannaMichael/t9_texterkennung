package t12.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import t12.t9Tree.T9Tree;

/**
 * <p>
 * Mit Hilfe dieser Klasse kann der T9 Baum gespeichert und geladen werden. 
 * @see t12.t9Tree.T9Tree 
 * </p>
 */
public class T9TreeSaver {
	
	
	/**
	 *Speichert den Baum mit Hilfe eines ObjectOutputStreams als "t9tree.bin". 
	 *@param t9Tree Der zuvor generierte T9Tree 
	 */
	public static void save(T9Tree t9Tree) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("t9tree.bin")));
			out.writeObject(t9Tree);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Lädt den zuvor gespeicherten T9 Baum mit einem ObjectInputStream 
	 *@return Der gespeicherte T9Tree
	 */
	public static T9Tree loadT9Tree() {
		
		T9Tree t9Tree = null;
		 try {
	         FileInputStream fileIn = new FileInputStream("t9tree.bin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         t9Tree = (T9Tree) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c) {
	         c.printStackTrace();
	      }

		return t9Tree;
	}

}
