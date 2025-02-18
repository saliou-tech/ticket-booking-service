package ai.ednova.ticket.booking.service.enums;

import ai.ednova.ticket.booking.service.constants.Constants;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {
  NOT_FOUND("001", "Not Found"),
  INTERNAL_SERVER_ERROR("001", "Internal Server Error"),
  HTTP_REQUEST_METHOD_NOT_SUPPORTED("003", "HTTP Request Method Not Supported"),
  INSUFFICIENT_PERMISSIONS("004", "Insufficient Permissions"),
  INSUFFICIENT_SEATS_AVAILABLE("004", "Not enough seats available"),
  INPUT_VALIDATION_ERROR("005", "Input Validation Error"),
  ;

  private final String code;

  private final String message;

  ErrorCode(String code, String message) {
    this.code = Constants.ERROR_CODE_PREFIX + code;
    this.message = message;
  }
}
