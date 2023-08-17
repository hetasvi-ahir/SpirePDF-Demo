package com.pdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdf.entity.Form;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

}
