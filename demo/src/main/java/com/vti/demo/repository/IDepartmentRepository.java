package com.vti.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.awt.print.Pageable;
import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Short>, JpaSpecificationExecutor<Department> {

    public Department findByName(String name);

    public boolean existsByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Department WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Short> ids);
}
