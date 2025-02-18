package ai.ednova.ticket.booking.service.entities;

import ai.ednova.ticket.booking.service.enums.DocumentStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class BaseEntity {
  @Id private String mongoId;

  @Field("id")
  private UUID id;

  @Version private Integer version;
  private DocumentStatus documentStatus;
  @CreatedBy private String createdBy;
  @CreatedDate private LocalDateTime createdAt;
  @LastModifiedBy private String lastModifiedBy;
  @LastModifiedDate private LocalDateTime updatadAt;
}
