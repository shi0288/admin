package com.xyauto.qa.entity;

import javax.persistence.*;

@Table(name = "attach")
public class Attach {
    @Id
    @Column(name = "attach_id")
    private Long attachId;

    private Integer type;

    @Column(name = "target_id")
    private Long targetId;

    private String file;

    @Column(name = "file_type")
    private Integer fileType;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;

    @Column(name = "deleted_at")
    private Integer deletedAt;

    /**
     * @return attach_id
     */
    public Long getAttachId() {
        return attachId;
    }

    /**
     * @param attachId
     */
    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return target_id
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * @param targetId
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * @return file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return file_type
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * @return created_at
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_at
     */
    public Integer getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return deleted_at
     */
    public Integer getDeletedAt() {
        return deletedAt;
    }

    /**
     * @param deletedAt
     */
    public void setDeletedAt(Integer deletedAt) {
        this.deletedAt = deletedAt;
    }
}