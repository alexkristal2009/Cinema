package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    boolean existsSessionById(Long id);

    boolean existsSessionByDate(LocalDate date);

    boolean existsSessionByPrice(Double price);

    Session getSessionById(Long id);

    List<Session> getAllByDate(LocalDate date);

    List<Session> getAllByPrice(Double price);

    List<Session> getAllByPriceLessThan(Double price);

    List<Session> getAllByPriceGreaterThan(Double price);

    List<Session> getAllBySessionMovie_Title(String movieTitle);

    void deleteAllBySessionMovie_Title(String movieTitle);

    void deleteAllByDate(LocalDate date);

}
