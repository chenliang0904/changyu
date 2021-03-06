/*
 * Copyright (c) 2016 Breezee.org. All Rights Reserved.
 */

package org.breezee.sysmgr.entity;

import org.breezee.common.framework.BaseEntity;
import org.breezee.sysmgr.api.domain.EnumInfo;
import org.breezee.sysmgr.api.domain.EnumItemInfo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 持久域：枚举项实体
 * Created by Silence on 2016/5/10.
 */
@Entity
@Table(name = "SYM_TF_ENUM")
public class EnumEntity extends BaseEntity<EnumEntity, EnumInfo> {

    private Set<EnumItemEntity> items;

    @Id
    @GeneratedValue(generator = "assigned-uid")
    @GenericGenerator(name = "assigned-uid", strategy = "assigned")
    @Column(name = "PK_ID", unique = true, nullable = false, updatable = false, length = 64)
    public String getId() {
        return id;
    }

    @Column(name = "CODE", unique = true, nullable = false, updatable = false, length = 64)
    public String getCode() {
        return code;
    }

    @Column(name = "NAME", nullable = false, length = 2000)
    public String getName() {
        return name;
    }

    @Column(name = "TENANT_ID", nullable = false, updatable = false, length = 64)
    public String getTenantId() {
        return tenantId;
    }

    @Column(name = "CREATOR", nullable = false, updatable = false, length = 64)
    public String getCreator() {
        return creator;
    }

    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "UPDATOR", nullable = false, length = 64)
    public String getUpdator() {
        return updator;
    }

    @Column(name = "UPDATE_TIME", nullable = false)
    public Date getUpdateTime() {
        return updateTime;
    }

    @Column(name = "REMARK", length = 3000)
    public String getRemark() {
        return remark;
    }

    @Column(name = "ROW_NUM", nullable = false)
    public Long getRowNum() {
        return rowNum;
    }

    @Column(name = "VERSION", nullable = false)
    public Integer getVersion() {
        return version;
    }

    @Column(name = "STATUS", nullable = false)
    public Integer getStatus() {
        return this.status;
    }

    @Column(name = "NODE_HOST", nullable = false, length = 128)
    public String getNode() {
        return node;
    }

    @OneToMany(mappedBy = "master", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<EnumItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<EnumItemEntity> items) {
        this.items = items;
    }

    public void addItem(EnumItemEntity item) {
        item.setMaster(this);
        this.items.add(item);
    }

    public EnumInfo toInfo(EnumInfo r, String... ignorePro) {
        super.toInfo(r, "items");
        this.getItems().forEach(a -> {
            r.getItems().add(a.toInfo(new EnumItemInfo()));
        });
        return r;
    }

    public EnumEntity parseInfo(EnumInfo info, String... ignorePro) {
        super.parseInfo(info, "items");
        if (info.getItems() != null) {
            this.items = new HashSet<>();
            info.getItems().forEach(a -> {
                this.addItem(new EnumItemEntity().parseInfo(a));
            });
        }
        return this;
    }
}
