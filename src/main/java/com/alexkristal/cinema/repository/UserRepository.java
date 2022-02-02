package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    boolean existsById(Long id);

    User getUserByLogin(String login);

    User getUserById(Long userId);

    void deleteByLogin(String login);

    void deleteById(Long id);



}
