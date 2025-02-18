package ai.ednova.ticket.booking.service.entities;

import ai.ednova.ticket.booking.service.enums.SeaStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket extends BaseEntity {
  private UUID userId;
  private UUID eventId;
  private Integer seatNumber;
  private SeaStatus seatStatus;
}
