package _00_Intro_To_String_Methods;

import java.util.Base64;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

    // Given Strings s1 and s2, return the longer String
    public static String longerString(String s1, String s2) {
//        return null;
    	if (s1.length() > s2.length()) {
    		return s1;
    	}
    	return s2;
    }

    // If String s contains the word "underscores", change all of the spaces
    // to underscores
    public static String formatSpaces(String s) {
    	String sReplaced = "";
    	if (s.contains("underscores")) {
    		sReplaced = s.replace(" ", "_");
    		return sReplaced;
    	}
//    	System.out.println(sReplaced);
    	return s;
    }

    // Return the name of the person whose LAST name would appear first if they
    // were in alphabetical order.
    // You cannot assume there are no extra spaces around the name, but you can
    // assume there is only one space between the first and last name
    public static String lineLeader(String s1, String s2, String s3) {
//        return null;
    	String s1Letter = s1.trim();
    	char s1Char = s1Letter.charAt(s1Letter.length()-1);
    	String s2Letter = s2.trim();
    	char s2Char = s2Letter.charAt(s2Letter.length()-1);
    	String s3Letter = s3.trim();
    	char s3Char = s3Letter.charAt(s3Letter.length()-1);
    	
    	if (Character.toString(s1Char).compareTo(Character.toString(s2Char)) > 0 ) {
    		
    		if (Character.toString(s2Char).compareTo(Character.toString(s3Char)) > 0 ) {
    			return s3Letter;
    			}
    		else {
    			return s2Letter;
    		}
    	} else {
    		if (Character.toString(s1Char).compareTo(Character.toString(s3Char)) > 0){
    			return s3Letter;
    			}
    		}
    	
    	return s1Letter;
    }

    // Return the sum of all numerical digits in the String
    public static int numeralSum(String s) {
        int count = 0;
    	char[] sArray = s.toCharArray();
    	
    	for (char n : sArray) {
    		if (Character.isDigit(n)) {
    			count += Integer.parseInt(String.valueOf(n));
    		}
    	}
        
    	return count;
    }

    // Return the number of times String substring appears in String s
    public static int substringCount(String s, String substring) {
        int count = 0;
        
        int index = s.indexOf(substring);
        while( index != -1 ) {
            count++;
            index = s.indexOf(substring, index + substring.length());
        }
    	return count;
    }

    // Call Utilities.encrypt at the bottom of this file to encrypt String s
    public static String encrypt(String s, char key) {
    	byte[] sByte = s.getBytes();
    	byte keyByte = (byte) key;
        return Utilities.encrypt(sByte, keyByte);
    }

    // Call Utilities.decrypt at the bottom of this file to decrypt the
    // cyphertext (encrypted text)
    public static String decrypt(String s, char key) {
    	byte keyByte = (byte) key;
        return Utilities.decrypt(s, keyByte);
    }

    // Return the number of words in String s that end with String substring
    // You can assume there are no punctuation marks between words
    public static int wordsEndsWithSubstring(String s, String substring) {
        int count = 0;
        String[] sArr = s.split(" ");
        
        for (String word : sArr) {
        	int index = word.indexOf(substring);
//        	System.out.println(index);
        	int position = (word.length() - substring.length() - 1);
        	if (index >= 0 && index >= position) {
        		count++;
        	}
        }
        
    	return count;
    }

    // Given String s, return the number of characters between the first
    // occurrence of String substring and the final occurrence
    // You can assume that substring will appear at least twice
    public static int distance(String s, String substring) {
    	int end = 0;
    	int start = s.indexOf(substring) + substring.length();
    	
    	for (int i = s.length(); i > 0; i--) {
//    		System.out.println(s.substring(i-substring.length(), i).equals(substring));
    		if (s.substring(i-substring.length(), i).equals(substring)) {
    			end = i-substring.length();
    			break;
    		}
    	}
    	
     	return end-start;
    }

    // Return true if String s is a palindrome
    // palindromes are words or phrases are read the same forward as backward.
    // HINT: ignore/remove all punctuation and spaces in the String
    public static boolean palindrome(String s) {
        String revString = "";
        String sNew = "";
        
        for (int i = 0; i < s.length(); i++) {
        	
        	if (Character.isLetter(s.charAt(i)) || Character.isWhitespace(s.charAt(i))) {
        		char temp = s.charAt(i);
            	revString = temp + revString;
            	sNew = sNew + temp;
        	}
        	
        	
        }
        sNew = sNew.toLowerCase().replace(" ", "").toLowerCase();
        
        revString = revString.replace(" ", "").toLowerCase();
        System.out.println(revString);
        System.out.println(sNew);
        if (revString.equals(sNew)) {
        	return true;
        }
    	return false;
    }
}

class Utilities {
    // This basic encryption scheme is called single-byte xor. It takes a
    // single byte and uses exclusive-or on every character in the String.
    public static String encrypt(byte[] plaintext, byte key) {
        for (int i = 0; i < plaintext.length; i++) {
            plaintext[i] = (byte) (plaintext[i] ^ key);
        }
        return Base64.getEncoder().encodeToString(plaintext);
    }

    public static String decrypt(String cyphertext, byte key) {
        byte[] b = Base64.getDecoder().decode(cyphertext);
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] ^ key);
        }
        return new String(b);
    }
}
