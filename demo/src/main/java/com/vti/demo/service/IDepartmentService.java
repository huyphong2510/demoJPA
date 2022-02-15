package com.vti.demo.service;

import java.text.ParseException;
import java.util.List;

import com.vti.demo.dto.filter.DepartmentFilter;
import com.vti.demo.entity.Department;
import com.vti.demo.form.DepartmentFormForCreating;
import com.vti.demo.form.DepartmentFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {

    public Page<Department> getAllDepartments(Pageable pageable,String search, DepartmentFilter filter) throws ParseException;

    public Department getDepartmentByID(short id);

    public Department getDepartmentByName(String name);

    public void createDepartment(DepartmentFormForCreating form);

    public void updateDepartment(short id, DepartmentFormForUpdating form);

    public void deleteDepartment(short id);

    public boolean isDepartmentExistsByID(short id);

    public boolean isDepartmentExistsByName(String name);

    public void deleteDepartments(List<Short> ids);
}
