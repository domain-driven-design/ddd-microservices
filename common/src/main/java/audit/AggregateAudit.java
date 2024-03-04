package audit;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@SuperBuilder
@Getter
public class AggregateAudit {
    private String id;
    private String createdBy;
    private OffsetDateTime createdTime;
    private String updatedBy;
    private OffsetDateTime updatedTime;

    public void updatedAudit(String userId) {
        this.createdBy = userId;
        this.updatedBy = userId;
        this.createdTime = OffsetDateTime.now();
        this.updatedTime = OffsetDateTime.now();
    }
}
