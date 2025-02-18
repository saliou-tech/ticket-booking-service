package ai.ednova.ticket.booking.service.dtos.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequest {
  private UUID userId;
  private UUID eventId;
  private Integer seatNumber;
}
