package com.archive.model;

import com.archive.controller.MoveObject; 
import java.util.ArrayList;
import java.util.Random;




public class ArchiveVO {
	private ArrayList<String> media_paths = new ArrayList<String>();
	String projectId;
	String bucketName = "homi-media-bucket";

	public ArchiveVO () {};
	
	public ArrayList<String> get_Media_Paths() {
		return media_paths;
	}
	
	public void add_Media_Paths(String[] paths_to_add) {
		for (int i = 0; i < paths_to_add.length; i++) {
			media_paths.add(paths_to_add[i]);
		}
	}
	public void remove_Media_Paths(String[] paths_to_remove) {
		for (int i = 0; i < paths_to_remove.length; i++) {
			media_paths.remove(paths_to_remove[i]);
		}
	}
	
	
	public void rename_Object(String sourceObjectName) {
		Random random = new Random();
		String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	    String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz"; 
	    String numbers = "0123456789"; 
	    // create a super set of all characters 
	    String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers; 
	    // initialize a string to hold result 
	    StringBuffer randomString = new StringBuffer(); 
	    // loop for 10 times 
	    for (int i = 0; i < 10; i++) { 
	      // generate a random number between 0 and length of all characters 
	      int randomIndex = random.nextInt(allCharacters.length()); 
	      // retrieve character at index and add it to result 
	      randomString.append(allCharacters.charAt(randomIndex)); 
	    } 
		String replacementString = randomString.toString();
		

		MoveObject.moveObject(projectId, bucketName, sourceObjectName, bucketName, replacementString);
	}
}
