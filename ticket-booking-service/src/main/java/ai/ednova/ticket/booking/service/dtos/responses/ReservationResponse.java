package ai.ednova.ticket.booking.service.dtos.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {
  private UUID userId;
  private UUID eventId;
  private Integer seatNumber;
}
