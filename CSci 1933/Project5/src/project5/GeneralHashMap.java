package project5;

import java.util.LinkedList;
import java.util.List;

public class GeneralHashMap extends MyHashMap {
	
	int tablesize = 120; //determines the hash table size, *this is heavily dependent on the file used*
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GeneralHashMap() {
		buckets = (List<String>[]) new LinkedList[tablesize];
		for(int i=0; i<tablesize; i++){
			buckets[i]= (List<String>) new LinkedList();
		}

	}

	@Override
	protected int hash(String token) {
		int hash = 0;
		int index = 0;

		for(int i=0; i<token.length(); i++){
			hash += token.codePointAt(i);
			hash += 1;
		}
		//Reasoning/Explanation:
		//adds up the unicode of the characters in the word in order to establish some intial variation
		//it is necessary to add further complexity since most of these values are close and many small
		//words can share a same sum of its charicters unicode

		if(token.contains("e")){
			hash -= 7;
		}
		if(token.contains("s")){
			hash = (hash+3)*7;
		}
		//Reasoning/Explanation:
		//This just spices it up a little bit and diversifies the hashes between the words in the file.
		//I arrived at these paticular if statement and effect completely randomly after trying out about
		//6 other .contains(...) then add/subtract/multiply/divide various versions

		index = Math.abs(hash % tablesize);             
		//choosing the index value based on tablesize and taking the absolute value so it does not
		//return a negative value for an index which would cause problems


		return index;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void add(String token) {
		int index = hash(token);
		if (((LinkedList) buckets[index]).size() == 0){  //if index is empty simply insert the token
			((LinkedList) buckets[index]).add(0, token);
		}
		else if(((LinkedList) buckets[index]).indexOf(token) == -1){ //if the token isnt in the linkedlist add it
			((LinkedList) buckets[index]).add(token);
			}
	
	}

	@Override
	public void display() {
		for(int i=0; i<tablesize; i++){  //i is used to cycle through every bucket
			int iterator=0;
			while(iterator<buckets[i].size()){  //iterator checks for collisions
				System.out.print(buckets[i].get(iterator).toString() + " ");
				//System.out.print(iterator) this can be used to quickly see the index of the element
				iterator ++;
			}
				System.out.println();
		}
	}
		//Display Notes:
		//The display method prints one bucket per line and thus prints collisions on the same line.

}
