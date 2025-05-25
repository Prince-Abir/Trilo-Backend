package shipping_service.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shipping_service.exceptions.BadRequestException;
import shipping_service.exceptions.DuplicateResourceFoundException;
import shipping_service.exceptions.InternalServerException;
import shipping_service.exceptions.JsonConversionException;
import shipping_service.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateResourceFoundException.class)
	public ResponseEntity<String> handleDuplicateDataException(DuplicateResourceFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<String> handleServerException(InternalServerException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(JsonConversionException.class)
	public ResponseEntity<String> ConvertException(JsonConversionException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex) {
		if (ex.getMessage().contains("from String value")) {
			return ResponseEntity.badRequest()
					.body("Invalid enum value provided in request. Please use a valid value.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed request.");
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> anyException(Exception ex){
//		
//		return new ResponseEntity<String>(ex.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
