package ai.ednova.ticket.booking.service.dtos.requests;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
  private String name;
  private String description;
  private String location;
  private LocalDateTime date;
  private Integer totalSeats;
}
