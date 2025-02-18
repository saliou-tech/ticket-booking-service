package ai.ednova.ticket.booking.service.exceptions.handler;

import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ErrorResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ResponseWrapper;
import ai.ednova.ticket.booking.service.enums.ErrorCode;
import ai.ednova.ticket.booking.service.exceptions.RestException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ResponseWrapper<Void>> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    return buildErrorResponse(
        HttpStatus.METHOD_NOT_ALLOWED,
        List.of(ErrorResponse.from(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED, ex.getMessage())));
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public ResponseEntity<ResponseWrapper<Void>> handleAccessDeniedException(
      AccessDeniedException accessDeniedException) {
    return buildErrorResponse(
        HttpStatus.FORBIDDEN, List.of(ErrorResponse.from(ErrorCode.INSUFFICIENT_PERMISSIONS)));
  }

  @ExceptionHandler(value = {RestException.class})
  public ResponseEntity<ResponseWrapper<Void>> handleResponseException(
      RestException restException) {
    return buildErrorResponse(restException.getHttpStatus(), restException.getErrorResponses());
  }

  @ExceptionHandler(value = {Throwable.class})
  public ResponseEntity<ResponseWrapper<Void>> handleGenericThrowable(Throwable throwable) {
    ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR);
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(errorResponse));
  }

  @ExceptionHandler(value = {MissingServletRequestParameterException.class})
  public ResponseEntity<ResponseWrapper<Void>> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException missingServletRequestParameterException) {
    ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INPUT_VALIDATION_ERROR);
    errorResponse.setDetail(missingServletRequestParameterException.getMessage());

    List<ErrorResponse> errorResponses = List.of(errorResponse);

    return buildErrorResponse(HttpStatus.BAD_REQUEST, errorResponses);
  }

  private ResponseEntity<ResponseWrapper<Void>> buildErrorResponse(
      HttpStatus httpStatus, List<ErrorResponse> errorResponses) {
    return ResponseEntity.status(httpStatus).body(ResponseWrapper.failure(errorResponses));
  }
}
