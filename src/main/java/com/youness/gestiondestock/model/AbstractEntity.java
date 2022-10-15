package com.youness.gestiondestock.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

//@Builder
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //@CreatedDate
    @Column(name = "creationDate")
    private Instant ceartionDate;

    //@LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Instant lastModifiedDate;

    @PrePersist
    void prePersist(){
        ceartionDate=Instant.now();
    }

    @PreUpdate
    void preUpdate(){
        lastModifiedDate=Instant.now();
    }


}
