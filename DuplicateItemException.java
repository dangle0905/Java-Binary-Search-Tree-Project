public class DuplicateItemException extends Exception {

	public DuplicateItemException() {
		super("No duplicate items");
	}
	
	public DuplicateItemException(String message) {
		super(message);
	}
}
