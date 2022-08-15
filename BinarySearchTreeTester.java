public class BinarySearchTreeTester {

	public static void main(String[] args){
		
		
		//this tree object takes a list of numbers
		//we added a duplicate in this tree too
		System.out.println("Prints a tree formed by list.\n");
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(60, 55, 45, 57, 100, 45, 67, 107);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
		
		System.out.println("Prints out all the methods used to transverse for tree 1.\n");
		//all the methods
		tree1.preorder();
		tree1.inorder();
		tree1.postorder();
		tree1.breadthfirst();
		
		
		System.out.println("\nBuilding a Tree.\n");
		//inserting more elements into the tree
		tree2.insert(60);
		tree2.insert(55);
		tree2.insert(45);
		tree2.insert(57);
		tree2.insert(100);
		tree2.insert(67);
		tree2.insert(107);
		tree2.insert(66);
		
		//testing out adding a duplicate
		System.out.println("\nAdding a duplicate number.\n");
		tree2.insert(66);
		//continuing to add on to the tree
		tree2.insert(69);
		tree2.insert(70);
		tree2.insert(106);
		tree2.insert(109);
		
		System.out.println("\nPrints out all the methods used to transverse for tree 2.\n");
		//all the methods
		tree2.preorder();
		tree2.inorder();
		tree2.postorder();
		tree2.breadthfirst();
		

		System.out.println("\nTesting out delete method.\n");
		//deleting a node that is a leaf
		tree2.delete(57);
		//deleting a node that has one child
		tree2.delete(55);
		//deleting a node with two child that is extended
		tree2.delete(100);
		//deleting a node with two child
		tree2.delete(107);


		
		}
}
