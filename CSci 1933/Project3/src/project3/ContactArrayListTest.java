package project3;

import static org.junit.Assert.*;

import org.junit.Test;


public class ContactArrayListTest {

	@Test
	public void testAdd() {
		ContactArrayList list = new ContactArrayList();
		assertTrue(list.add(new Contact()));
	}

	@Test
	public void testFind() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		list.add(new Contact("c",3,"add3","c3"));		
		assertTrue(list.find("c").equals(new Contact("c",3,"add3","c3")));
	}

	@Test
	public void testRemove() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		list.add(new Contact("c",3,"add3","c3"));
		Contact c = list.remove();
		assertTrue(c.equals(new Contact("c",3,"add3","c3")));
	}

	@Test
	public void testGetCurrent() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		Contact c = list.getCurrent();
		assertTrue(c.equals(new Contact("b",2,"add2","c2")));
	}

	@Test
	public void testGet() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		Contact c = list.get(0);
		assertTrue(c.equals(new Contact("a",1,"add","c")));
	}

	@Test
	public void testNext() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		list.add(new Contact("c",3,"add3","c3"));
		Contact c = list.next();
		assertTrue(c.equals(new Contact("a",1,"add","c")));
	}

	@Test
	public void testPrevious() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("b",2,"add2","c2"));
		list.add(new Contact("c",3,"add3","c3"));
		Contact c = list.previous();
		assertTrue(c.equals(new Contact("b",2,"add2","c2")));
	}

	@Test
	public void testSort() {
		ContactArrayList list = new ContactArrayList();
		list.add(new Contact("c",3,"add3","c3"));
		list.add(new Contact("b",2,"add2","c2"));
		list.add(new Contact("a",1,"add","c"));
		list.add(new Contact("z",26,"add26","c26"));
		list.sort();
		assertTrue(list.get(0).getName().equals("a"));
		assertTrue(list.get(1).getName().equals("b"));
		assertTrue(list.get(2).getName().equals("c"));
		assertTrue(list.get(3).getName().equals("z"));
	}

	@Test
	public void testAddInOrder() {
		ContactArrayList list = new ContactArrayList();
		list.addInOrder(new Contact("c",3,"add3","c3"));
		list.addInOrder(new Contact("b",2,"add2","c2"));
		list.addInOrder(new Contact("z",26,"add26","c26"));
		list.addInOrder(new Contact("a",1,"add","c"));
		assertTrue(list.get(0).getName().equals("a"));
		assertTrue(list.get(1).getName().equals("b"));
		assertTrue(list.get(2).getName().equals("c"));
		assertTrue(list.get(3).getName().equals("z"));
	}

}
