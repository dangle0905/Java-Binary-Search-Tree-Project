public class BSTNode <E extends Comparable<E>> {
	protected E data;
	protected BSTNode <E> parent;
    protected BSTNode <E> left;
    protected BSTNode <E> right;
    
    //constructor
    public BSTNode(E data){
        this.data = data;
        
    }
    
    public E getData() {
    	return this.data;
    }
    
    public void setData(E value) {
    	this.data = value;
    }
}
 