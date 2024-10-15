package com.manish.moodle.repository;

import com.manish.moodle.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {

}
