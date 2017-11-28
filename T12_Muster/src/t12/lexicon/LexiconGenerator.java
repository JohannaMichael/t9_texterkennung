package t12.lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * Erstellt das Lexikon Spinphone.lex, indem es alle SMS-Korpus Texte aus "data" einliest, diese mit Hilfe des 
 * LexiconRegexTools "filtert" und dann in die Datei Spinphone.lex schreibt. 
 * </p>
 */

public class LexiconGenerator {
	
	private LexiconRegexTool lexiconRegexTool = new LexiconRegexTool();
	
	/**
	 * Speichert die Anzahl an Dateien aus dem "data SMS-Korpus". Ist vor allem im Test nützlich, um herauszufinden,
	 * ob das Programm alle Dateien eingelesen hat. 
	 */
	private int size;
	
	/**
	 * Beinhaltet die Texte aller Dateien aus dem SMS Korpus.
	 */
	private String allTexts = "";
	
	/**
	 * Gibt die Zahl der Dateien wieder.
	 */
	
	public int size(){
		return size;
	}
	
	/**
	 * Diese Methode erstellt das Lexikon Spinphone.lex mit allen Textdaten aus dem SMS Korpus. 
	 * 
	 * @param pathToTexts Verzeichnis zu den Texten
	 * @param lexFileDestination Lexikondatei
	 * @see writeFileData(File pathToTexts, String lexFileDestination)
	 */
	public void setAllFileData(File pathToTexts, String lexFileDestination){ 
		if(pathToTexts == null || lexFileDestination == null) { 
			throw new IllegalArgumentException("Die übergebenen Parameter dürfen nicht null sein!");
		}
		writeFileData(pathToTexts, lexFileDestination); 
	}
	
	/**
	 * Methode in der das Dateiverzeichnis "data" eingegeben wird. Sie lädt alle Dateien, die in diesem 
	 * Verzeichnis sind, in ein Array und übergibt diese an die Methode showAllFiles(). Die Methode showAllFiles() gibt 
	 * alle Textdaten aus allen Dateien aus dem Verzeichnis Data und seinen Unterverzeichnissen zurück.
	 * 
	 * @param directory Der Pfad zum SMS Korpus
	 * @return Alle Textdaten aus allen Dateien des SMS Korpus
	 */
	public String getAllFileData(File directory){
		
		if(directory == null) { 
			throw new IllegalArgumentException("Die übergebenen Parameter dürfen nicht null sein!");
		}
		
		File[] arrayFile = directory.listFiles();
		String allTexts = showAllFiles(arrayFile);
		return allTexts;
	}
	
	/**
	 * Durchläuft alle Unterverzeichnisse von "data" und liest die Texte mit
	 * loadFileData(String relativePath) ein. Dabei ist wichtig, dass der relative Pfad
	 * als Parameter dient. Um das zu erreichen, muss jeder absolute Pfad einer
	 * Datei zurechtgeschnitten werden. Speichert alle Textdaten auf der Variable allTexts. 
	 * 
	 * @param currentFiles Array von den Unterverzeichnissen/Dateien von data
	 * @return allTexts Alle Textdaten aus den data Dateien
	 */
	private String showAllFiles(File[] currentFiles) {
		 for (File file : currentFiles) {
		 if (file.isDirectory()) {

	            showAllFiles(file.listFiles()); 
	        } else {
	        	String absoluteFilePath = file.getAbsolutePath();
	        	String relativeFilePath = absoluteFilePath.substring(absoluteFilePath.indexOf("data\\"));
	            String newText = loadFileData(relativeFilePath); 
	            if(newText != null){
	            	size++;
	            }
	            allTexts = allTexts + " " + newText;
	            
	        }
		 }
		 return allTexts;
	}
	
	/**
	 *Zusätzliche Methode, die den Text einer Datei zurückgibt
	 *
	 *@param fileName Der Pfad einer Datei
	 *@return Den Text der Datei 
	 */
	public String getLoadedFileData(String fileName){
		String text = loadFileData(fileName);
		return text;
	}
	
	/**
	 *InputStream mit einem BufferedReader und StringBuffer um den Text einer Datei schnell einlesen zu können. 
	 *Encoding ist bei allen Dateien UTF-8. 
	 *
	 *@param fileName Der Pfad einer Datei
	 *@return Den Text der Datei 
	 */
	private String loadFileData(String fileName) {
				FileInputStream in = null;
				File file = new File(fileName);
				try {
					in = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InputStreamReader isr = null;
				try {
					isr = new InputStreamReader(in, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				BufferedReader bufferedReader = new BufferedReader(isr);
				StringBuffer stringBuffer = new StringBuffer();

				String currentLine;
				try {
					while ((currentLine = bufferedReader.readLine()) != null) {
						stringBuffer.append(currentLine);
						stringBuffer.append("\n"); 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				String text = stringBuffer.toString();
				return text;
	}
	
	/**
	 *Erstellt das Lexikon. Die Textdaten werden mit getAllFileData(pathToTexts) eingelesen. Mit dem 
	 *lexiconRegexTool.getRegexCleaner(content) werden die Textdaten "gefiltert" und in die Lexikon reingeschrieben.
	 *
	 *@see t12.lexicon.LexiconRegexTool.java
	 *@param pathToTexts 
	 *@param lexFileDestination
	 */
	private void writeFileData(File pathToTexts, String lexFileDestination){ 
		
		if(pathToTexts == null || lexFileDestination == null) { 
			throw new IllegalArgumentException("Die übergebenen Parameter dürfen nicht null sein!");
		}
		
		File file = new File(lexFileDestination); 
	
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {

			String content = getAllFileData(pathToTexts);
			
			String cleanedTexts =lexiconRegexTool.getRegexCleaner(content);

			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(cleanedTexts);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	
	
	
}
