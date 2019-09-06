package com.vuebin.common.model.entity;

import com.vuebin.common.model.validator.BaseValidatingParam;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author fengjiabin
 * @date 2019/6/10 20:59
 */
@Data
@Entity
@Table(name = "b_user")
public class User implements BaseValidatingParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @Override
    public String checkParam() {
        if (Objects.isNull(this.name)) {
            return "name参数不能为空";
        }
        if (Objects.isNull(this.age)) {
            return "age参数不能为空";
        }
        return null;
    }
}
