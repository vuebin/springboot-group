package com.vuebin.jpa.repository;

import com.vuebin.common.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fengjiabin
 * @date 2019/6/10 20:58
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * name -> user
     *
     * @param name
     * @return
     */
    User findByName(String name);

}
