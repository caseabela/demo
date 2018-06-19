/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Book;

/**
 * @author CaseyS
 *
 */
@Repository

public interface BookRepository extends JpaRepository<Book, Integer> {

}
