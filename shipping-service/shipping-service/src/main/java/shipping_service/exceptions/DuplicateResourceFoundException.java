package shipping_service.exceptions;

public class DuplicateResourceFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateResourceFoundException(String message) {
		super(message);
	}

}
