package TestStuff;

import java.util.ArrayList;
import java.util.List;

public class TestLamba {

	public static void main(String[] args) {
		System.out.println("KICK IT !!!");

		// Test here :D
		TestLamba t = new TestLamba();
		System.out.println(t.getRetNew() ? "Jau" : "Nee");

		System.out.println("THAT'S ALL, FOLKS");
	}

	public boolean getRetOld(){
		List<Person> personen = new ArrayList<>();
		personen.add(new Person("Hans", 1));
		personen.add(new Person("Petra", 5));
		personen.add(new Person("Heinz", 7));
		List<Integer> nummern = new ArrayList<>();
		nummern.add(1);
		nummern.add(2);

		for(Person p : personen){
			for(int i : nummern){
				if(p.getNummer() == i){
					return true;
				}
			}
		}

		return false;
	}

	public boolean getRetNew(){
		List<Person> personen = new ArrayList<>();
		personen.add(new Person("Hans", 1));
		personen.add(new Person("Petra", 5));
		personen.add(new Person("Heinz", 7));
		List<Integer> nummern = new ArrayList<>();
		nummern.add(3);
		nummern.add(2);

//		return personen.stream().filter(p -> nummern.contains(p.getNummer())).count() > 0;
		return personen.stream().anyMatch(p -> nummern.contains(p.getNummer()));
	}

	private static class Person{
		String name = "";
		int nummer = 0;

		public Person(String name, int nummer){
			this.name = name;
			this.nummer = nummer;
		}

//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
		public int getNummer() {
			return nummer;
		}
//		public void setNummer(int nummer) {
//			this.nummer = nummer;
//		}
	}
}
