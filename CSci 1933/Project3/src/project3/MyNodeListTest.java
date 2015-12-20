package project3;

import static org.junit.Assert.*;

import org.junit.Test;


public class MyNodeListTest {

	
	@Test
	public void testAddNull() {
		MyList list = new MyNodeList();
		assertEquals(false,list.add(null));
	}
	
	@Test
	public void testAddTwice() {
		MyList list = new MyNodeList();
		Puppy p = new Puppy("Mal",10);
		Puppy p2 = new Puppy("Anara",11);
		assertTrue(list.add(p));
		assertTrue(list.add(p2));
	}
	
	@Test
	public void testInsertMid() {
		MyList list = new MyNodeList();
		Puppy alice = new Puppy("Alice",5);
		Puppy batman = new Puppy("Batman",10);
		Puppy carlos = new Puppy("Carlos",15);
		Puppy draco = new Puppy("Draco",20);
		list.add(alice);
		list.add(batman);
		list.add(carlos);
		assertTrue(list.insert(2, draco));
	}

	@Test
	public void testClear() {
		MyList list = new MyNodeList();
		int rand = (int)(Math.random()*100)+20;
		for(int i = 0; i < rand; i++){
			list.add(new Puppy("p"+i,i));
		}
		list.clear();
		assertEquals(0,list.size());
	}

	@Test
	public void testContains() {
		MyList list = new MyNodeList();
		Puppy alice = new Puppy("Alice",5);
		Puppy batman = new Puppy("Batman",10);
		Puppy carlos = new Puppy("Carlos",15);
		list.add(alice);
		list.add(batman);
		list.add(carlos);
		assertTrue(list.contains(new Puppy(alice)));
		assertTrue(list.contains(new Puppy(batman)));
		assertTrue(list.contains(new Puppy(carlos)));
	}

	@Test
	public void testGet() {
		MyList list = new MyNodeList();
		Puppy p = new Puppy("Mal",10);
		list.add(p);
		assertNotNull(list.get(0));
		assertTrue((new Puppy(p)).equals(list.get(0)));
	}

	@Test
	public void testIndexOf() {
		MyList list = new MyNodeList();
		int rand = (int)(Math.random()*100)+20;
		Puppy[] puppyMill = new Puppy[rand];
		for(int i = 0; i < puppyMill.length; i++){
			puppyMill[i] = new Puppy("p"+i,i);
			list.add(new Puppy("p"+i,i));
		}
		int r = (int)(Math.random()*rand);
		assertEquals(r,list.indexOf(puppyMill[r]));
	}
		
	@Test
	public void testIsNotEmpty() {
		MyList list = new MyNodeList();
	    list.add(new Puppy("pup",12));
		assertFalse(list.isEmpty());
	}

	@Test
	public void testRemoveIntEmpty() {
		MyList list = new MyNodeList();
		assertNull(list.remove(0));
	}
	
	@Test
	public void testRemoveObjectEmpty() {
		MyList list = new MyNodeList();
		Puppy p = new Puppy("Jayne",9001);
		assertFalse(list.remove(p));
	}
	
	@Test
	public void testSetSuccess() {
		MyList list = new MyNodeList();
		Puppy p = new Puppy("Jayne",9001);
		list.add(p);
		Puppy p3 = new Puppy("Kaylee",1337);
		Puppy p2 = (Puppy)list.get(0);
		list.set(0,p3);
		assertEquals(p3,list.get(0));
		assertNotEquals(p2,list.get(0));
	}
	
	@Test
	public void testSizeAddAndClear() {
		MyList list = new MyNodeList();
		int rand = (int)(Math.random()*100)+20;
		for(int i = 0; i < rand; i++){
			list.add(new Puppy("p"+i,i));
		}
		list.clear();
		assertEquals(0,list.size());
	}

}
