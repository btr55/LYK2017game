package tr.org.linux.kamp.lists;

import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		
		ArrayListDemoObject aListDemo= new ArrayListDemoObject();
		
		aListDemo.addElement("LKD");
		
		aListDemo.addElement("2017");
		
		aListDemo.addElement("Java");
		
		ArrayList<String> list=new ArrayList<String>();
		
		list.add("LKD");
		
		list.addAll(aListDemo.getListDemo());
		
		for (String element : list) {
			System.out.println(element);
		}
		
		boolean removeResult =aListDemo.removeElement("Java");
		if (removeResult) {
			System.out.println("Removing successfull.");
		}
		else {
			System.out.println("Removing failed.");
		}
	}
	

	
}
