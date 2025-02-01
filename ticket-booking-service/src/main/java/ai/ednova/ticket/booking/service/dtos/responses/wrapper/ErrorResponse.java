package ai.ednova.ticket.booking.service.dtos.responses.wrapper;

import ai.ednova.ticket.booking.service.enums.ErrorCode;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse implements Serializable {
  private String errorCode;

  private String message;

  private String detail;

  private String help;

  public static ErrorResponse from(ErrorCode errorCode) {
    return from(errorCode, null, null);
  }

  public static ErrorResponse from(ErrorCode errorCode, String detail) {
    return from(errorCode, detail, null);
  }

  public static ErrorResponse from(ErrorCode errorCode, String detail, String help) {
    return from(errorCode.getCode(), errorCode.getMessage(), detail, help);
  }

  public static ErrorResponse from(String errorCode, String message, String detail, String help) {
    return new ErrorResponse(errorCode, message, detail, help);
  }
}
