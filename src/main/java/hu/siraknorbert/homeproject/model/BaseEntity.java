package hu.siraknorbert.homeproject.model;

import hu.siraknorbert.homeproject.util.UuidUtil;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;

import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UuidUtil.randomUUID();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
