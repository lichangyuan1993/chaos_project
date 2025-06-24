package com.example.ffp.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 聚合根抽象基类
 * @param <ID> 聚合根ID类型（必须是值对象）
 */
public abstract class AggregateRoot<ID> {
    // 聚合根唯一标识（protected允许子类访问）
    protected final ID id;
    
    // 领域事件列表（事件溯源支持）
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    
    // 乐观锁版本（并发控制）
    private Long version = 0L;
    
    // 必须通过ID构造
    protected AggregateRoot(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("聚合根ID不能为空");
        }
        this.id = id;
    }
    
    public ID id() {
        return id;
    }
    
    public Long version() {
        return version;
    }
    
    /**
     * 注册领域事件
     */
    protected void registerEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }
    
    /**
     * 发布后清除事件（通常在持久化后调用）
     */
    public List<DomainEvent> releaseEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(events);
    }
    
    /**
     * 增加版本号（并发控制）
     */
    public void incrementVersion() {
        this.version++;
    }
    
    // 领域对象相等性（基于ID）
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AggregateRoot)) return false;
        AggregateRoot<?> that = (AggregateRoot<?>) o;
        return id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}