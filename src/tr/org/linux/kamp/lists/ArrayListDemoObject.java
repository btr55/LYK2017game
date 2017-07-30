package tr.org.linux.kamp.lists;

import java.util.ArrayList;

public class ArrayListDemoObject {		//hoca nesneyi ayrı bir sınıfta yazıyor.
	
	private ArrayList<String>listDemo;
	
	public ArrayListDemoObject() {	//constructor kısmı
		
		listDemo =new ArrayList<String>();
		
	}

	public void addElement (String element) {
		listDemo.add(element);
	}
	public boolean removeElement(String element) {
		return listDemo.remove(element);
	}
	
	public boolean doesListHasElement() {
		return !listDemo.isEmpty();
	}
	public boolean hasElement(String element) {
		return listDemo.contains(element);
	}

	public ArrayList<String> getListDemo() {
		return listDemo;
	}

	public void setListDemo(ArrayList<String> listDemo) {
		this.listDemo = listDemo;
	}
	
	
}
