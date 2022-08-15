import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

public class BinarySearchTree <E extends Comparable<E>>{
	BSTNode<E> root;
	
	public BinarySearchTree(){
		root=null;
	}
	
	
	//this adds the values and nodes
	public BinarySearchTree(E...list){
	
		for(int count = 0; count < list.length; count++) {
			E key = list[count];
			
			BSTNode<E> child = new BSTNode(key);

			if (isEmpty()) {
				this.root = child;
			}else{

				BSTNode<E> parent;
				try {
					parent = insertionPoint(key);
					if (key.compareTo(parent.data)<0) {
						parent.left = child;
					}else if (key.compareTo(parent.data)>0) {
						parent.right = child;
					}
					child.parent = parent;
				} catch (DuplicateItemException e) {
					// TODO Auto-generated catch block
					System.out.println("No duplicates, " + key + " is already in the binary search tree.");
				}
	
			}
		}
		
		printTree();
		toString();
		
	}
	
	public void insert(E value) {
		E key = value;
		

		BSTNode<E> child = new BSTNode<E>(key); 

		if (isEmpty()) {
			this.root = child;
		}else{

			BSTNode<E> parent;
			try {
				parent = insertionPoint(key);
				if (key.compareTo(parent.data)<0) {
					parent.left = child;
				}else if (key.compareTo(parent.data)>0) {
					parent.right = child;
				}
				child.parent = parent;
			} catch (DuplicateItemException e) {
				// TODO Auto-generated catch block
				System.out.println("No duplicates, " + key + " is already in the binary search tree.");
			}
			
			
			
		}
		
		printTree();
		toString();

	}
	
	public void delete(E value) {
		E key = value;
		delete(nodeToDelete(key));


	}
	
	private void delete(BSTNode<E> node) {
		
		if(isLeaf(node)) {
			if(isLeftChild(node)) {
				node.parent.left = null;
			}else if(isRightChild(node)) {
				node.parent.right = null;
			 }
			//there's already one child so if one side is null that means the other side is not null and is the child
		}else if (numChildren(node) == 1) {
	
				if (isLeftChild(node.left)) {
					BSTNode<E> child = node.left;
					node.parent.left = child;
				}else if (isRightChild(node.right)) {
					BSTNode<E> child = node.right;
					node.parent.right = child;
				}
		
		}else if (numChildren(node)==2) {
				BSTNode<E> max = maxLeftSubtree(node);
				node.setData(max.getData());
				delete(max); 
		}
		
		printTree();
		toString();

		
	
	}

	//i guess this searches for the max value in the left subtree and returns the address of the node
	//that holds the max value. 
	private BSTNode<E> maxLeftSubtree(BSTNode<E> node) {
		BSTNode<E> current = node.left;
		BSTNode<E> maxValue = node.left;
		
		
		//scans thru the entire thing and compares values higher than 
		while(current!=null){
			if(maxValue.data.compareTo(current.data) < 0) {
				maxValue = current;
				current=current.right;
				
				
			}else if(maxValue.data.compareTo(current.data) >= 0) {
				current = current.right;
			}
		}
	
		return maxValue;

	}
	
	private BSTNode<E> nodeToDelete(E key) {
		BSTNode<E> current = this.root;
		while(current != null) {
			if(key.equals(current.data)) {
				
				return current;
			}else if (key.compareTo(current.data) < 0) {
				current = current.left;
			}else if (key.compareTo(current.data) > 0) {
				current = current.right;
			}
		}
		return null;
		
	}
	
	public boolean find(E value) {
		E key = value;
		BSTNode<E> current = this.root;
		while(current != null) {
			if(key.equals(current.data)) {
				return true;
			}else if (key.compareTo(current.data) < 0) {
				current = current.left;
			}else if (key.compareTo(current.data) > 0) {
				current = current.right;
			}
		}
		return false;
	}

	
	public boolean isEmpty() {
		if (this.root==null) {
			return true;
		}else
			return false;
	}
	
	public ArrayList<E> preorder() {
		ArrayList<E> arrayList = new ArrayList<>();
		BSTNode<E> node = this.root;
		arrayList.addAll(preorder(node));
		System.out.println(arrayList);
		return arrayList;
		
	}
	
	private ArrayList<E> preorder(BSTNode<E> node) {
		ArrayList<E> arrayList = new ArrayList<>();

		if (node==null) {
			return arrayList;
		}else
		//visit node
		arrayList.add(node.data);
		
		//recursion left side
		arrayList.addAll(preorder(node.left));
		
		//recursion right side
		arrayList.addAll(preorder(node.right));
		
		return arrayList;
		
		
		
	}
	
	public ArrayList<E> inorder() {
		ArrayList<E> arrayList = new ArrayList<>();
		BSTNode<E> node = this.root;
		arrayList.addAll(inorder(node));
		System.out.println(arrayList);
		return arrayList;
	}
	
	private ArrayList<E> inorder(BSTNode<E> node) {
		ArrayList<E> arrayList = new ArrayList<>();

		if (node==null) {
			return arrayList;
		}else
		//recursion left side
		arrayList.addAll(inorder(node.left));
		//visit node
		arrayList.add(node.data);
		//recursion right side
		arrayList.addAll(inorder(node.right));
		
		return arrayList;
		
		
		
	}
	
	
	public ArrayList<E> postorder() {
		ArrayList<E> arrayList = new ArrayList<>();
		BSTNode<E> node = this.root;
		arrayList.addAll(postorder(node));
		System.out.println(arrayList);
		return arrayList;
	}
	
	private ArrayList<E> postorder(BSTNode<E> node) {
		ArrayList<E> arrayList = new ArrayList<>();

		if (node==null) {
			return arrayList;
		}else
		//recursion left side
		arrayList.addAll(postorder(node.left));

		//recursion right side
		arrayList.addAll(postorder(node.right));
		
		//visit node
		arrayList.add(node.data);
		
		return arrayList;
		
	}
	
	public ArrayList<E> breadthfirst() {
		ArrayList<E> arrayList = new ArrayList<>();
		arrayList.addAll(breadthfirst(this.root));
		System.out.println(arrayList);
		return arrayList;
	}
	
	private ArrayList<E> breadthfirst(BSTNode<E> node) {
		ArrayList<E> arrayList = new ArrayList<>();
		BSTNode<E> current;
		Queue<BSTNode<E>> q = new LinkedList<>();
		
		
		if (node == null) {
			return arrayList;
		}
		q.add(node);
		while(!q.isEmpty()) {
			//removes node from q queue and now current holds the node removed
			current = q.remove();
			
			//current.data is the root and is added to an arrayList
			arrayList.add(current.data);
			if(current.left != null) {
				q.add(current.left);
			}
			if(current.right != null) {
				q.add(current.right);
			}
			
			
		}
		
		return arrayList;
		
		
		
	}
	
	public String toString(String string) {
		String total = "";
		total += string;
		System.out.print(total);
		return string;
	}
	
	protected boolean isLeaf(BSTNode<E> node) {
		if (node.left == null && node.right == null) {
			return true;
		} else
			
			return false;
	}
	
	protected boolean isLeftChild(BSTNode<E> node) {
		
		if(node ==null) {
			System.out.println("null");
			return false;
		}
		
		//we have to do <= to  because they are duplicate of each other
		//it wouldnt matter too because we already got an exception handler
		//for insert that wont take in duplicate numbers. this is only duplicate because we copied the data 
		if (node.data.compareTo(node.parent.data) <= 0) {
			return true;
		}
		
		return false;
	}
	
	protected boolean isRightChild(BSTNode<E> node) {
		if(node ==null) {
			return false;
		}
		
		if (node.data.compareTo(node.parent.data) >= 0) {
			return true;
		}
		
		return false;
	}
	
	protected int numChildren(BSTNode<E> node) {
		if (node.left != null && node.right != null) {
			return 2;
		}else if(node.left == null && node.right != null) {
			return 1;
		}else if(node.left != null && node.right ==null) {
			return 1;
		}else
			return 0;
		
	}
	
	private BSTNode<E> insertionPoint(E key) throws DuplicateItemException{
		BSTNode<E> current = this.root;
		BSTNode<E> parent = null;
		
		while(current!= null) {
				if (key.compareTo(current.data)==0) {
					throw new DuplicateItemException();
				} else if (key.compareTo(current.data) < 0) {
					parent = current;
					current = current.left;
					
				}else if (key.compareTo(current.data) > 0) {
					parent = current;
					current = current.right;
				}
			
			
		}
		
		return parent;
	}
	
	public void printTree() {

	    if (this.root.right != null) {
	        this.printTree(this.root.right, true, "");
	    }

	    printNodeValue(this.root);

	    if (this.root.left != null) {
	        this.printTree(this.root.left, false, "");
	    }
	}

	private void printTree(BSTNode<E> node, boolean isRight, String indent) {
	    if (node.right != null) {
	        printTree(node.right, true, indent + (isRight ? "        " : " |      "));
	    }
	    
	    toString(indent);


	    if (isRight) {
	    	toString(" /");

	    }
	    else {
	    	toString(" \\");

	    }
	    toString("----- ");

	    printNodeValue(node);
	    
	    if (node.left != null) {
	        printTree(node.left, false, indent + (isRight ? " |      " : "        "));
	    }
	}

	private void printNodeValue(BSTNode<E> node) {
	    if (node == null) {
	    	toString("<null>");

	    }
	    else {
	    	toString(String.valueOf(node.getData()));

	    }
	    toString("\n");
	    

	}
	
}
