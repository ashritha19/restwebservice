package com.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set names = new HashSet();
		names.add("Tom");
		names.add("Mary");
		names.add(123);
		names.add(123);
		names.add("Mary");
		names.add(null);
		names.add(null);
		
		Iterator iterator = names.iterator();
		 
		while (iterator.hasNext()) {
		    System.out.println(iterator.next());
		}  
	}

}
