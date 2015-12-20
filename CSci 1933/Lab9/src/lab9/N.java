package lab9;

public class N {
	 // instance variables
    private Object data;
    private N next;

    // constructors
    public N() {

    }

    public N(Object o, N link) {
         data = o;
         next = link;
    }

    // selectors
    public Object getData() {
         return data;
    }

    public void setData(Object o) {
         data = o;
    }

    public N getNext() {
         return next;
    }

    public void setNext(N link) {
         next = link;
    }
}
