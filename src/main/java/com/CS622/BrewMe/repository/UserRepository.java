package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface UserRepository<A extends User> extends JpaRepository<User, Long> {


}
