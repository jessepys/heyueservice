package com.heyue.service.framework;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jessepi on 4/16/16.
 */
@MappedSuperclass
public abstract class DomainEntity implements Serializable {
    protected Long id;
    private boolean delStatus;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifyBy;

    @Id
    @Column(columnDefinition="int(10) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "del_status")
    public boolean isDelStatus() {
        return delStatus;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }
    @Column(name = "created_dt")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
