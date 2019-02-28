import java.util.ArrayList;
import java.util.Scanner;

public class SearchAndSort {
	
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			SearchAndSort s = new SearchAndSort();
			
			// Questions
			int responseSearch = s.validResponse(7, "What sort would you like to use?\n\tBubble    [1]\n\tSelection [2]\n\tInsertion [3]\n\tMerge     [4]\n\tLinear    [5]\n\tBinary    [6]\n\t> Quit    [7]\nChoice: ");
			if (responseSearch == 7) {
				System.out.print("Closing...");
				return; 
			}
			int responseData = s.validResponse(2, "\n\nWhat datatype will it be?\n\tIntegers  [1]\n\tStrings   [2]\nChoice: ");
			int responseStorage = s.validResponse(2, "\n\nHow will it be stored?\n\tArray     [1]\n\tList      [2]\nChoice: ");
			
			// Get list
			System.out.println("\n\nPlease enter your data in the following format: \"a,b,c...x,y,z\"");
			boolean responseValid = false;
			String response = "";
			while (!responseValid) {
				response = s.sc.nextLine();
				responseValid = true;
				for(int i = 0; i < response.length(); i++) {
					responseValid = (response.charAt(i) == ',' && (response + " ").charAt(i+1) == ',') ? false : responseValid;
					if (responseData == 1)
						responseValid = (!((response.charAt(i) >= '0' && response.charAt(i) <= '9') || response.charAt(i) == ',')) ? false : responseValid;
				}
				responseValid = (response.charAt(response.length()-1) == ',') ? false : responseValid;
				if (!responseValid) {
					System.out.println("Invalid Reponse, please try again.");
				}
			}
			String[] strings = response.split(",");
			
			// Integer and Array
			System.out.println("\n****************************\n");
			if (responseData == 1 && responseStorage == 1) { 
				int[] ints = new int[strings.length];
				for(int i = 0; i < strings.length; i++) {
					ints[i] = Integer.parseInt(strings[i]);
				}
				switch (responseSearch) {
				case 1:
					System.out.println("Bubble Sort: " + s.toString(s.bubble(ints)));
					break;
				case 2:
					System.out.println("Selection Sort: " + s.toString(s.selection(ints)));
					break;
				case 3:
					System.out.println("Insertion Sort: " + s.toString(s.insertion(ints)));
					break;
				case 4:
					System.out.println("Merge Sort: " + s.toString(s.merge(ints)));
					break;
				case 5:
					System.out.println("Index from Linear Search: " + s.linear(ints, Integer.parseInt(s.targetVal(true, false))));
					break;
				case 6:
					System.out.println("Index from Binary Search: " + s.binary(ints, Integer.parseInt(s.targetVal(true, true))));
					break;
				default:
					System.out.println("ERROR: how did you even get here?");
				}
			} 
			
			// Integer and ArrayList
			else if (responseData == 1 && responseStorage == 2) {
				ArrayList<Integer> intsList = new ArrayList<Integer>(strings.length);
				for(int i = 0; i < strings.length; i++) {
					intsList.add(Integer.parseInt(strings[i]));
				}
				switch (responseSearch) {
				case 1:
					System.out.println("Bubble Sort: " + s.toString(s.bubble(intsList)));
					break;
				case 2:
					System.out.println("Selection Sort: " + s.toString(s.selection(intsList)));
					break;
				case 3:
					System.out.println("Insertion Sort: " + s.toString(s.insertion(intsList)));
					break;
				case 4:
					System.out.println("Merge Sort: " + s.toString(s.merge(intsList)));
					break;
				case 5:
					System.out.println("Index from Linear Search: " + s.linear(intsList, Integer.parseInt(s.targetVal(true, false))));
					break;
				case 6:
					System.out.println("Index from Binary Search: " + s.binary(intsList, Integer.parseInt(s.targetVal(true, true))));
					break;
				default:
					System.out.println("ERROR: how did you even get here?");
				}
			} 
			
			// String and Array
			else if (responseData == 2 && responseStorage == 1) {
				switch (responseSearch) {
				case 1:
					System.out.println("Bubble Sort: " + s.toString(s.bubble(strings)));
					break;
				case 2:
					System.out.println("Selection Sort: " + s.toString(s.selection(strings)));
					break;
				case 3:
					System.out.println("Insertion Sort: " + s.toString(s.insertion(strings)));
					break;
				case 4:
					System.out.println("Merge Sort: " + s.toString(s.merge(strings)));
					break;
				case 5:
					System.out.println("Index from Linear Search: " + s.linear(strings, s.targetVal(false, false)));
					break;
				case 6:
					System.out.println("Index from Binary Search: " + s.binary(strings, s.targetVal(false, true)));
					break;
				default:
					System.out.println("ERROR: how did you even get here?");
				}
			} 
			
			// String and ArrayList
			else if (responseData == 2 && responseStorage == 2) {
				ArrayList<String> stringsList = new ArrayList<String>(strings.length);
				for(int i = 0; i < strings.length; i++) {
					stringsList.add(strings[i]);
				}
				switch (responseSearch) {
				case 1:
					System.out.println("Bubble Sort: " + s.toString(false, s.bubble(false, stringsList)));
					break;
				case 2:
					System.out.println("Selection Sort: " + s.toString(false, s.selection(false, stringsList)));
					break;
				case 3:
					System.out.println("Insertion Sort: " + s.toString(false, s.insertion(false, stringsList)));
					break;
				case 4:
					System.out.println("Merge Sort: " + s.toString(false, s.merge(false, stringsList)));
					break;
				case 5:
					System.out.println("Index from Linear Search: " + s.linear(false, stringsList, s.targetVal(false, false)));
					break;
				case 6:
					System.out.println("Index from Binary Search: " + s.binary(false, stringsList, s.targetVal(false, true)));
					break;
				default:
					System.out.println("ERROR: how did you even get here?");
				}
			} else {
				System.out.println("Error: Inputs: responseData=" + responseData+ ", reponseStorage=" + responseStorage);
			}
			System.out.println("\n****************************\n");
		}
	}
	
	///////////////////////////////// BUBBLE SORT ////////////////////////////////
	
	private int[] bubble(int[] nums) {
		int length = nums.length;
		int temp = 0;
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (nums[j] > nums[j+1]) {
					temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
		return nums;
	}
	
	private String[] bubble(String[] words) {
		int length = words.length;
		String temp = "";
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (compareStrings(words[j], words[j+1]) == '>') {
					temp = words[j];
					words[j] = words[j+1];
					words[j+1] = temp;
				}
			}
		}
		return words;
	}
	
	private ArrayList<Integer> bubble(ArrayList<Integer> nums) {
		int length = nums.size();
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (nums.get(j) > nums.get(j+1)) {
					nums.set(j, nums.set(j+1, nums.get(j)));
				}
			}
		}
		return nums;
	}
	
	private ArrayList<String> bubble(boolean randomassboolean, ArrayList<String> words) {
		int length = words.size();
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (compareStrings(words.get(j), words.get(j+1)) == '>') {
					words.set(j, words.set(j+1, words.get(j)));
				}
			}
		}
		return words;
	}
	
    ///////////////////////////////// SELECTION SORT ////////////////////////////////
	
	private int[] selection(int[] nums) {
		int length = nums.length;
		for (int i = 0; i < length; i++) {		
			int minIndex = i;
			for (int j = i; j< length; j++) {
				if (nums[j] < nums[minIndex]) {
					minIndex = j;
				}
			}
			int temp = nums[i];
			nums[i] = nums[minIndex];
			nums[minIndex] = temp;
		}
		return nums;
	}
	
	private String[] selection(String[] words) {
		int length = words.length;
		for (int i = 0; i < length; i++) {		
			int minIndex = i;
			for (int j = i; j< length; j++) {
				if (compareStrings(words[j], words[minIndex]) == '<') {
					minIndex = j;
				}
			}
			String temp = words[i];
			words[i] = words[minIndex];
			words[minIndex] = temp;
		}
		return words;
	}
	
	private ArrayList<Integer> selection(ArrayList<Integer> nums) {
		int length = nums.size();
		for (int i = 0; i < length; i++) {		
			int minIndex = i;
			for (int j = i; j< length; j++) {
				if (nums.get(j) < nums.get(minIndex)) {
					minIndex = j;
				}
			}
			nums.set(i, nums.set(minIndex, nums.get(i)));
		}
		return nums;
	}
	
	private ArrayList<String> selection(boolean randomassboolean, ArrayList<String> words) {
		int length = words.size();
		for (int i = 0; i < length; i++) {		
			int minIndex = i;
			for (int j = i; j< length; j++) {
				if (compareStrings(words.get(j), words.get(minIndex)) == '<') {
					minIndex = j;
				}
			}
			words.set(i, words.set(minIndex, words.get(i)));
		}
		return words;
	}
	
	///////////////////////////////// INSERTION SORT ////////////////////////////////
	
	private int[] insertion(int[] nums) {
		int length = nums.length;
		for (int i = 1; i < length; i++) {
			int x = nums[i];
			int j;
			for (j = i-1; j >= 0 && nums[j] > x; j--) {
				nums[j+1] = nums[j];
			}
			nums[j+1] = x;
		}
		return nums;
	}
	
	private String[] insertion(String[] words) {
		int length = words.length;
		for (int i = 1; i < length; i++) {
			String x = words[i];
			int j;
			for (j = i-1; j >= 0 && (compareStrings(words[j],x) == '>'); j--) {
				words[j+1] = words[j];
			}
			words[j+1] = x;
		}
		return words;
	}
	
	private ArrayList<Integer> insertion(ArrayList<Integer> nums) {
		int length = nums.size();
		for (int i = 1; i < length; i++) {
			int x = nums.get(i);
			int j;
			for (j = i-1; j >= 0 && nums.get(j) > x; j--) {
				nums.set(j+1, nums.get(j));
			}
			nums.set(j+1, x);
		}
		return nums;
	}
	
	private ArrayList<String> insertion(boolean randomassboolean, ArrayList<String> words) {
		int length = words.size();
		for (int i = 1; i < length; i++) {
			String x = words.get(i);
			int j;
			for (j = i-1; j >= 0 && (compareStrings(words.get(j), x) == '>'); j--) {
				words.set(j+1, words.get(j));
			}
			words.set(j+1, x);
		}
		return words;
	}
	
	///////////////////////////////// MERGE SORT ////////////////////////////////
	
	private int[] merge(int[] nums) {
		int length = nums.length;
		int mid = (int) length/2;
		
		if (length < 2) {
			return nums;
		}
		
		// Halves
		int[] ar1 = new int[mid];
		for (int i = 0; i < mid; i++) {
			ar1[i] = nums[i];
		}
		int[] ar2 = new int[length - mid];
		for (int i = mid; i < length; i++) {
			ar2[i-mid] = nums[i];
		}
		
		// Recursive
		ar1 = merge(ar1);
		ar2 = merge(ar2);
		
		// Merge
		int i = 0;
		int j = 0;
		int k = 0;
	    while (i < mid && j < (length-mid)) {
	        if (ar1[i] <= ar2[j]) {
	        	nums[k++] = ar1[i++];
	        }
	        else {
	        	nums[k++] = ar2[j++];
	        }
	    }
	    while (i < mid) {
	    	nums[k++] = ar1[i++];
	    }
	    while (j < (length-mid)) {
	    	nums[k++] = ar2[j++];
	    }
		return nums;
	}
	
	private String[] merge(String[] words) {
		int length = words.length;
		int mid = (int) length/2;
		
		if (length < 2) {
			return words;
		}
		
		// Halves
		String[] ar1 = new String[mid];
		for (int i = 0; i < mid; i++) {
			ar1[i] = words[i];
		}
		String[] ar2 = new String[length - mid];
		for (int i = mid; i < length; i++) {
			ar2[i-mid] = words[i];
		}
		
		// Recursive
		ar1 = merge(ar1);
		ar2 = merge(ar2);
		
		// Merge
		int i = 0;
		int j = 0;
		int k = 0;
	    while (i < mid && j < (length-mid)) {
	        if (compareStrings(ar1[i], ar2[j]) != '>') { // Essentially '<='
	        	words[k++] = ar1[i++];
	        }
	        else {
	        	words[k++] = ar2[j++];
	        }
	    }
	    while (i < mid) {
	    	words[k++] = ar1[i++];
	    }
	    while (j < (length-mid)) {
	    	words[k++] = ar2[j++];
	    }
		return words;
	}
	
	private ArrayList<Integer> merge(ArrayList<Integer> nums) {
		int length = nums.size();
		int mid = (int) length/2;
		
		if (length < 2) {
			return nums;
		}
		
		// Halves
		ArrayList<Integer> ar1 = new ArrayList<Integer>(mid);
		for (int i = 0; i < mid; i++) {
			ar1.set(i, nums.get(i));
		}
		ArrayList<Integer> ar2 = new ArrayList<Integer>(length - mid);
		for (int i = mid; i < length; i++) {
			ar2.set(i-mid, nums.get(i));
		}
		
		// Recursive
		ar1 = merge(ar1);
		ar2 = merge(ar2);
		
		// Merge
		int i = 0;
		int j = 0;
		int k = 0;
	    while (i < mid && j < (length-mid)) {
	        if (ar1.get(i) <= ar2.get(j)) {
	        	nums.set(k++, ar1.get(i++));
	        }
	        else {
	        	nums.set(k++, ar2.get(j++));
	        }
	    }
	    while (i < mid) {
	    	nums.set(k++, ar1.get(i++));
	    }
	    while (j < (length-mid)) {
	    	nums.set(k++, ar2.get(j++));
	    }
		return nums;
	}
	
	private ArrayList<String> merge(boolean randomassboolean, ArrayList<String> words) {
		int length = words.size();
		int mid = (int) length/2;
		
		if (length < 2) {
			return words;
		}
		
		// Halves
		ArrayList<String> ar1 = new ArrayList<String>(mid);
		for (int i = 0; i < mid; i++) {
			ar1.set(i, words.get(i));
		}
		ArrayList<String> ar2 = new ArrayList<String>(length - mid);
		for (int i = mid; i < length; i++) {
			ar2.set(i-mid, words.get(i));
		}
		
		// Recursive
		ar1 = merge(false, ar1);
		ar2 = merge(false, ar2);
		
		// Merge
		int i = 0;
		int j = 0;
		int k = 0;
	    while (i < mid && j < (length-mid)) {
	        if (compareStrings(ar1.get(i), ar2.get(j)) != '>') { // Essentially '<='
	        	words.set(k++, ar1.get(i++));
	        }
	        else {
	        	words.set(k++, ar2.get(j++));
	        }
	    }
	    while (i < mid) {
	    	words.set(k++, ar1.get(i++));
	    }
	    while (j < (length-mid)) {
	    	words.set(k++, ar2.get(j++));
	    }
		return words;
	}
	
	///////////////////////////////// LINEAR SEARCH ////////////////////////////////
	
	private int linear(int[] nums, int target) {
		int i = 0;
		boolean found = false; 
		while (i < nums.length && !found) {
			if (nums[i] == target) {
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? i : -1;
	}
	
	private int linear(String[] words, String target) {
		int i = 0;
		boolean found = false; 
		while (i < words.length && !found) {
			if (words[i].equals(target)) {
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? i : -1;
	}
	
	private int linear(ArrayList<Integer> nums, int target) {
		int i = 0;
		boolean found = false; 
		while (i < nums.size() && !found) {
			if (nums.get(i).equals(target)) {
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? i : -1;
	}
	
	private int linear(boolean randomassboolean, ArrayList<String> words, String target) {
		int i = 0;
		boolean found = false; 
		while (i < words.size() && !found) {
			if (words.get(i).equals(target)) {
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? i : -1;
	}

	///////////////////////////////// BINARY SEARCH ////////////////////////////////
	
	private int binary(int[] nums, int target) {
		nums = bubble(nums);
		int start = 0;
		int end = nums.length - 1;	
		int position = -1;
		while (start <= end) {
			position = (int) ((end + start) / 2);
			if (nums[position] > target) {
				end = position - 1;
			} else if (nums[position] < target) {
				start = position + 1; 
			} else {
				return position;
			}
		}
		return -1;
	}
	
	private int binary(String[] words, String target) {
		words = bubble(words);
		int start = 0;
		int end = words.length - 1;	
		int position = -1;
		while (start <= end) {
			position = (int) ((end + start) / 2);
			if (compareStrings(words[position], target) == '>') {
				end = position - 1;
			} else if (compareStrings(words[position], target) == '<') {
				start = position + 1; 
			} else {
				return position;
			}
		}
		return -1;
	}
	
	private int binary(ArrayList<Integer> nums, int target) {
		nums = bubble(nums);
		int start = 0;
		int end = nums.size() - 1;	
		int position = -1;
		while (start <= end) {
			position = (int) ((end + start) / 2);
			if (nums.get(position) > target) {
				end = position - 1;
			} else if (nums.get(position) < target) {
				start = position + 1; 
			} else {
				return position;
			}
		}
		return -1;
	}
	
	private int binary(boolean randomassboolean, ArrayList<String> words, String target) {
		words = bubble(false, words);
		int start = 0;
		int end = words.size() - 1;	
		int position = -1;
		while (start <= end) {
			position = (int) ((end + start) / 2);
			if (compareStrings(words.get(position), target) == '>') {
				end = position - 1;
			} else if (compareStrings(words.get(position), target) == '<') {
				start = position + 1; 
			} else {
				return position;
			}
		}
		return -1;
	}
	
	///////////////////////////////// TO-STRING ////////////////////////////////
	
	private String toString(int[] nums) {
		String output = "[";
		for (int integer : nums) {
			output = output + String.valueOf(integer) + ", ";
		}
		return output.substring(0, output.length()-2) + "]";
	}
	
	private String toString(String[] vals) {
		String output = "[";
		for (String str : vals) {
			output = output + str + ", ";
		}
		return output.substring(0, output.length()-2) + "]";
	}
	
	private String toString(ArrayList<Integer> nums) {
		String output = "[";
		for (int integer : nums) {
			output = output + String.valueOf(integer) + ", ";
		}
		return output.substring(0, output.length()-2) + "]";
	}
	
	private String toString(boolean randomassboolean, ArrayList<String> vals) {
		String output = "[";
		for (String str : vals) {
			output = output + String.valueOf(str) + ", ";
		}
		return output.substring(0, output.length()-2) + "]";
	}
	
	///////////////////////////////// OTHER METHODS ////////////////////////////////
	
	private int validResponse(int num, String prompt) {
		System.out.print(prompt);
		boolean responseValid = false;
		char response = 0;
		while (!responseValid) {
			String responseString = sc.nextLine();
			response = (!(responseString.length() != 1)) ? responseString.charAt(0) : '0';
			responseValid = (response != '0' && response >= '1' && response <= String.valueOf(num).charAt(0)) ? true : false;
			if (!responseValid) {
				System.out.print("Invalid Reponse, please try again: ");
			}
		}
		return ((int) response) - 48;
	}
	
	private String targetVal(boolean number, boolean binary) {
		boolean valid = false;
		String response = "";
		if (binary) {
			System.out.println("Binary Search requires a sorted list. \nThis will sort it for you, but return \nthe target's index in the sorted list.");
		}
		System.out.print("Please enter a target value: ");
		while (!valid) {
			valid = true;
			response = sc.nextLine();
			for(int i = 0; i < response.length(); i++) {
				if (number) {
					valid = (!(response.charAt(i) >= '0' && response.charAt(i) <= '9')) ? false : valid;
				}
			}
			if (!valid) {
				System.out.print("Invalid Reponse, please try again: ");
			} 
		}
		return response;
	}

	private char compareStrings(String str1, String str2) {
		if (str1.equals(str2))
			return '=';
		int charSpace = 0;
		while (str1.length() - charSpace > 0 && str2.length() - charSpace > 0) {
			if (str1.charAt(charSpace) > str2.charAt(charSpace)) {
				return '>';
			} else if (str1.charAt(charSpace) < str2.charAt(charSpace)) {
				return '<';
			} else if (str1.charAt(charSpace) == str2.charAt(charSpace)) {
				charSpace++;
			} 
		}
		if (str1.length() > str2.length()) {
			return '>';
		} else {
			return '<';
		}
	}
}










