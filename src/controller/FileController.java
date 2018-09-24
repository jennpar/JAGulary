package controller;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileController {
//create directory
//add library to directory
//add file to library
//create new file
//scan a file
	
	
// All files have strings, no int, in the order of percentage (for each word), word, definition, and hint.
// All of them are separated by tabs
	
/*************************************CREATES DIRECTORIES*********************************/
	public static void createDirectory() throws IOException {
		
		ArrayList<String> array = null;

		Path p1 = Paths.get("Directory\\Library 1");
		Path p2 = Paths.get("Directory\\Library 2");
		Path p3 = Paths.get("Directory\\Library 3");
		Path p4 = Paths.get("Directory\\Library 4");
		Path p5 = Paths.get("Directory\\CA 275");
		
		Files.createDirectories(p1);
		Files.createDirectories(p2);
		Files.createDirectories(p3);
		Files.createDirectories(p4);
		Files.createDirectories(p5);
		
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 1");
		scanNewFileIntoDirectory("CA 275", "Chapter 1", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 2");
		scanNewFileIntoDirectory("CA 275", "Chapter 2", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 3");
		scanNewFileIntoDirectory("CA 275", "Chapter 3", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 4");
		scanNewFileIntoDirectory("CA 275", "Chapter 4", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 5");
		scanNewFileIntoDirectory("CA 275", "Chapter 5", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 6");
		scanNewFileIntoDirectory("CA 275", "Chapter 6", array);
		array = scanNewFileToArray("C:\\Users\\jennp\\OneDrive\\Documents\\javaWorkspace\\JAGulary\\Files\\CA 275\\Chapter 7");
		scanNewFileIntoDirectory("CA 275", "Chapter 7", array);
	}
	
	
/***********************************PRINTS LIBRARY NAMES IN DIRECTORY********************/	
	public static String[] printDirectory(String folder) {
	
		String pathway = "Directory\\" + folder;
		File file = new File(pathway);
		String[] names = file.list();
		
//		for (int i = 0; i<names.length; i++)
//		{
//			System.out.println(names[i]);
//		}	
		
		return names;
	}
	
/*****************************ADDS NEW LIBRARY TO DIRECTORY****************************/	
	public static void addLibraryToDirectory(String library) {
		Path path = Paths.get("Directory\\" + library);
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
/*****************************ADDS NEW FILE TO LIBRARY*******************************/		
	public static void addFileToLibrary(String library, String filename) {
		
		File infile = new File(filename);
		Path path = Paths.get("Directory\\" + library + "\\" + infile + ".txt");
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
/********************************CREATES NEW FILE IN DIRECTORY****************************************/
	public static void scanNewFileIntoDirectory(String library, String filename, ArrayList<String> arrayList) throws IOException {	
		
//		filename = filename + ".txt";
		File f = new File(filename);
		if(!f.exists()){
		   try {
			f.createNewFile();
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		}
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Directory\\" + library + "\\" + filename)))) {
			int size = arrayList.size();
	        for (int i=0;i<size;i++) {
	        	String str = arrayList.get(i).toString();
	            writer.write(str);
//	            if(i < size-1) //This prevent creating a blank like at the end of the file**
//	                writer.write("\n");   
	        }
	            
	        writer.close();
		}
		
	}
	
/********************************SCANS NEW FILE INTO ARRAY****************************************/
	//Adds  place for percentage at the beginning of each line
	public static ArrayList<String> scanNewFileToArray(String newFile) throws FileNotFoundException{
		
		String word, def, hint;
		String correct = "0\t";
		String total = "0\t";		
		ArrayList<String> arrayList = new ArrayList<String>();
		File inFile = new File(newFile);
		if (inFile.exists()) {
			Scanner input = new Scanner(inFile);
			input.useDelimiter("\t");
			
			while (input.hasNext()) {
				if((arrayList.size())%5 == 0) {
					arrayList.add(correct);
					arrayList.add(total);
				}
				word = input.next();
				arrayList.add(word + "\t");
				def = input.next();
				arrayList.add(def + "\t");
				hint = input.nextLine();
				arrayList.add(hint + "\n");
				
			}
			input.close();
		}
		else {
		    System.err.println(
			        "I cannot find '" + inFile + "' ('" + inFile.getPath() + "')");
		}
		return arrayList;
	}
	
	
/********************************SCANS FILE INTO ARRAY****************************************/
	
	public static ArrayList<String> scanFileToArray(String newFile) throws FileNotFoundException{
		
		String word, def, hint, correct, total;
		ArrayList<String> arrayList = new ArrayList<String>();
		File inFile = new File("Directory\\" + newFile);
		if (inFile.exists()) {
			Scanner input = new Scanner(inFile);
			input.useDelimiter("\t");
			
			while (input.hasNext()) {
				correct = input.next();
				arrayList.add(correct);
				total = input.next();
				arrayList.add(total);
				word = input.next();
				arrayList.add(word);
				def = input.next();
				arrayList.add(def);
				hint = input.nextLine();
				arrayList.add(hint);
//				System.out.println("Percentage: " + percentage + "\nWord: " + word + "\nDef: " + def + "\nHint: " + hint);
				
			}
			input.close();
		}
		else {
		    System.err.println(
			        "I cannot find '" + inFile + "' ('" + inFile.getPath() + "')");
		}

		return arrayList;
				
	}

/***********************************Rewrite the file to the directory*************************/
public static void rewriteFileInDirectory(String filename, ArrayList<String> arrayList) throws IOException {	
		
	
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Directory\\" + filename)))) {
			int size = arrayList.size();
	        for (int i=0;i<size;i++) {
	        	String str = arrayList.get(i).toString();
	            writer.write(str);
//	            if(i < size-1) //This prevent creating a blank like at the end of the file**
//	                writer.write("\n");   
	        }
	            
	        writer.close();
		}
		
	}

/**********************************Overwrite File************************************************/
	public static void overwriteFile(String filename, ArrayList<String> array) {
		deleteFile(filename);
		try {
			rewriteFileInDirectory(filename, array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


/********************************Deletes A File*****************************************/	
	public static void deleteFile(String deleteFile) {

		File file = new File("Directory\\" + deleteFile );
		if (file.exists()) {
		    file.delete();
		} else {
		    System.err.println(
		        "I cannot find '" + file + "' ('" + file.getPath() + "')");
		}
			
	}
	
/********************************Deletes All Files and Library*****************************************/	
	public static void deleteAllFile(String deleteLibrary) {

		String [] array = printDirectory(deleteLibrary);
		for (int i = 0; i<array.length; i++) {
			File file = new File("Directory\\" + deleteLibrary + "\\" + array[i] );
			if (file.exists()) {
			    file.delete();
			} else {
			    System.err.println(
			        "I cannot find '" + file + "' ('" + file.getPath() + "')");
			}
		}
		File library = new File("Directory\\" + deleteLibrary);
		if (library.exists()) {
		    library.delete();
		} else {
		    System.err.println(
		        "I cannot find '" + library + "' ('" + library.getPath() + "')");
		}
			
	}
	
/***********************************Update Percentage***********************************************/
	public static void updatePercentageNumbers(String filename, ArrayList<Integer> correct, ArrayList<Integer> total){
		ArrayList<String> fileArray = new ArrayList<String> ();
		try {
			fileArray = scanFileToArray(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = 0;
		for (int i = 0; i< fileArray.size(); i += 5) {
			String num = "" + correct.get(j) + "\t";
			System.out.println(correct.get(j));
			fileArray.set(i, num);
			j++;
		}
		j = 0;
		for (int i = 1; i < fileArray.size(); i += 5) {
			String num = "" + total.get(j) + "\t";
			fileArray.set(i, num);
			j++;
		}
		for (int i = 2; i<fileArray.size(); i+=5) {
			fileArray.set(i, fileArray.get(i)+"\t");
		}
		for (int i = 3; i<fileArray.size(); i+=5) {
			fileArray.set(i, fileArray.get(i)+"\t");
		}
		for (int i = 4; i<fileArray.size(); i+=5) {
			fileArray.set(i, fileArray.get(i)+"\n");
		}
		overwriteFile(filename, fileArray);

	}
	
/*************************************************FLASHCARD CLASS**********************************************/	

	public static ArrayList<String> getDefinition(ArrayList<String> array){
		
		ArrayList<String> defs = new ArrayList<String>();
		for (int i = 3; i<array.size(); i += 5) {
				defs.add(array.get(i));
		}
		
		return defs;
	}
	
	public static ArrayList<String> getWords(ArrayList<String> array){
		
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 2; i<array.size(); i += 5) {
			words.add(array.get(i));
		}
		
		return words;
	}

	public static ArrayList<String> getHint(ArrayList<String> array){
		
		ArrayList<String> hints = new ArrayList<String>();
		for (int i = 4; i<array.size(); i += 5) {
			hints.add(array.get(i));
		}
		
		return hints;
	}
		
	public static ArrayList<Integer> getCorrect(ArrayList<String> array){
		
		ArrayList<Integer> correct = new ArrayList<Integer>();
		for (int i = 0; i<array.size(); i += 5) {
			array.get(i).replaceAll("\\s+", "");
			int number = Integer.parseInt(array.get(i));
			correct.add(number);
		}
		
		return correct;
	}
	
	public static ArrayList<Integer> getTotal(ArrayList<String> array){
		
		ArrayList<Integer> total = new ArrayList<Integer>();
		for (int i = 1; i<array.size(); i += 5) {
			array.get(i).replaceAll("\\s+", "");
			int number = Integer.parseInt(array.get(i));
			total.add(number);
		}
		
		return total;
	}
	
	public static ArrayList<String> getPercentage(ArrayList<Integer> correct, ArrayList<Integer> total){
		ArrayList<String> percent = new ArrayList<String>();
		for (int i = 0; i<correct.size(); i ++) {
			if (total.get(i)>0) {
				int first = correct.get(i);
				int second = total.get(i);
				int percentage = (first*100/second);
				percent.add(percentage + "%");
			}else {
				percent.add("0%");
			}
		}
		
		return percent;
	}

	
	
	

		

//	public static void main (String [] args) throws IOException {
//		ArrayList<String> array = new ArrayList<String>();
//		ArrayList<String> percent = new ArrayList<String>();
//		ArrayList<Integer> correct = new ArrayList<Integer>();
//		ArrayList<Integer> total = new ArrayList<Integer>();
//		String filename = "CA 275\\Chapter 1";
//		createDirectory();
//		printDirectory("CA 275\\Chapter 1");
//		addLibraryToDirectory("CA 275");
//		addFileToLibrary("CA 275", "Chapter 1");
//		scanFileIntoDirectory("Tester.txt");
//		array = scanNewFileToArray("Files\\CA 275\\Chapter 7");
//		scanNewFileIntoDirectory("CA 275", "Chapter 7", array);
//		array = scanFileToArray("Directory\\CA 275\\Chapter 7");
//		deleteFile("CA 275");
//		deleteAllFile("CA 275");
//		printDirectory("CA 275");
		
//		array = scanFileToArray(filename);
//		correct = getCorrect(array);
//		total = getTotal(array);
//		correct.set(0, 1);
//		total.set(0, 2);
//		
//		percent = getPercentage(correct, total);
		
//		updatePercentageNumbers(filename, correct, total);
//    Print array for testing purposes		
//		for (int i=0; i<array.size(); i++) {
//	            System.out.print(array.get(i)+"\n");
//		}
//		for (int i=0; i<percent.size(); i++) {
//            System.out.print(percent.get(i)+"\n");
//		}


		
//	}

}



