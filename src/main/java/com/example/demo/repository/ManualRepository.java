package com.example.demo.repository;

import com.example.demo.entity.Manual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Long> {
}
