package com.fengwenyi.component.common.jpa.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-13
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseBizEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -4142804697681834023L;

    @Column(name = "status")
    private Boolean status;

}
