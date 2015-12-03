package TestStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TestLists {

	public static void main(String[] args) {
		TestLists test = new TestLists();
		test.testListSort();
	}
	
	public void testListSort(){
		// Erstmal brauchen wir eine List mit der wir spielen können.		
		List<String> list = Arrays.asList("Hello", "World", "Hello World");
				
		list.sort(new Comparator<String>() {
			public int compare(String val1, String val2) {
				return val1.compareTo(val2);
			}
		});
		
		System.out.println(list);
	}
	
	public void testListModifiable(){
		// Erstmal brauchen wir eine List mit der wir spielen können.
		List<Integer> defaultList = Arrays.asList(1, 2, 3);
		
		List<Integer> unmodifiableList = Collections.unmodifiableList(defaultList);
		System.out.println(unmodifiableList); // Output: [1, 2, 3]
//		unmodifiableList.add(1); // throws java.lang.UnsupportedOperationException
		defaultList.add(4); // klappt, ist ja die modifizierbare Liste
		System.out.println(unmodifiableList); // Output: [1, 2, 3, 4] 
	}
	
	public void testListLoops(){
		// Erstmal brauchen wir eine List mit der wir spielen können.		
		List<String> list = Arrays.asList("Hello", "World", "Hello World");
		
		list.forEach(str -> { // Ausgabe für jedes Element das es in der List gibt.
			System.out.println(str); // str ist eine Temp-Variable
		});
		
		list.stream().
		filter(s -> s.contains("H")). // Nur Elemente die ein "H" enthalten....
		forEach((str) -> {
			System.out.println(str); // ... werden ausgegeben
		});
	}
	
	public void testDynOutput(){
		dynOutput(1, 2, 3, 4, 5, 6); // Übergabe von beliebig vielen Parametern.
	}
	private void dynOutput(int... vals){
		for(int i : vals){ // Der Compiler baut ein Array aus den Values
			System.out.println(i);
		}
	}
	
	public void emptyList(){
		List<Integer> ints = Collections.emptyList(); 	// erstellt eine leere List die sich nicht modifizieren lässt 
														//(also nix hinzufügen, sonst Exception)
		List<Integer> ints2 = new ArrayList<Integer>(); // Normale leere List	
		System.out.println(ints);
		System.out.println(ints2);
		
//		ints.add(1); // Das wirft eine Exception.
		List<Integer> newInt = new ArrayList<Integer>(ints); // Neue List gleichen Typs aus der Leeren.
		newInt.add(1); // jetzt gehts mit dem add()
		ints2.add(1);  // das geht so wie so, ist ja eine StandardList ohne jegliche Dekorierer
		System.out.println(newInt);
		System.out.println(ints2);
	}
}
