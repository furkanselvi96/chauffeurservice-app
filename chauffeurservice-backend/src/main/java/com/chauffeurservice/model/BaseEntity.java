package com.chauffeurservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Column(nullable = false, updatable = false)
    private LocalDateTime insertDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        insertDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }
}

