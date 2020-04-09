package com.wassimmiladi.project_managment_tool.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audible<U> {


    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    @CreatedDate
    private Date createdAt;

    @Column(updatable = false)
    @CreatedBy
    private U createdBy;


    @JsonFormat(pattern = "yyyy-mm-dd")
    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private U lastModifiedBy;
}