package com.won.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * 엔티티 공통 컬럼
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class BaseEntity {

	@Setter
	@Column(name="is_deleted", nullable=false, updatable = true)
	private boolean isDeleted; // 삭제여부

	@CreationTimestamp
	@Column(name="created_at", nullable=false, updatable = false)
	private LocalDateTime createdAt; // 추가시간

	@Setter
	@UpdateTimestamp
	@Column(name="updated_at", nullable=true, updatable = true, insertable = false)
	private LocalDateTime updatedAt; // 수정시간


}
