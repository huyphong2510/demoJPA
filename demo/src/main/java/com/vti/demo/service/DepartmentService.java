package com.vti.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.vti.demo.dto.filter.DepartmentFilter;
import com.vti.demo.specification.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.demo.entity.Account;
import com.vti.demo.entity.Department;
import com.vti.demo.form.DepartmentFormForCreating;
import com.vti.demo.form.DepartmentFormForUpdating;
import com.vti.demo.repository.IAccountRepository;
import com.vti.demo.repository.IDepartmentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @SuppressWarnings("deprecation")
    public Page<Department> getAllDepartments(Pageable pageable, String search, DepartmentFilter filter) throws ParseException {

        Specification<Department> where = null;

        if (!StringUtils.isEmpty(search)) {
            DepartmentSpecification nameSpecification = new DepartmentSpecification("name", "LIKE", search);
            DepartmentSpecification authorSpecification = new DepartmentSpecification("author.fullName", "LIKE",
                    search);
            where = Specification.where(nameSpecification).or(authorSpecification);
        }

        if(filter != null && filter.getMinDate() != null) {
            DepartmentSpecification minDateSpecification = new DepartmentSpecification("createDate", ">=", filter.getMinDate());
            if(where == null) {
                where = Specification.where(minDateSpecification);
            } else {
                where  = where.and(minDateSpecification);
            }
        }

        if(filter != null && filter.getMaxDate() != null) {
            DepartmentSpecification maxDateSpecification = new DepartmentSpecification("createDate", "<=", filter.getMaxDate());
            if(where == null) {
                where = Specification.where(maxDateSpecification);
            } else {
                where  = where.and(maxDateSpecification);
            }
        }


        return departmentRepository.findAll(where, pageable);
    }
    public Department getDepartmentByID(short id) {
        return departmentRepository.findById(id).get();
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public void createDepartment(DepartmentFormForCreating form) {
        // convert form --> entity

        // get author
        Account author = accountRepository.findById(form.getAuthorId()).get();

        Department department = new Department(form.getName());
        department.setAuthor(author);

        departmentRepository.save(department);
    }

    public void updateDepartment(short id, DepartmentFormForUpdating form) {
        Department department = getDepartmentByID(id);
        department.setName(form.getName());

        departmentRepository.save(department);
    }

    public void deleteDepartment(short id) {
        departmentRepository.deleteById(id);
    }

    public boolean isDepartmentExistsByID(short id) {
        return departmentRepository.existsById(id);
    }

    public boolean isDepartmentExistsByName(String name) {
        return departmentRepository.existsByName(name);
    }

    public void deleteDepartments(List<Short> ids) {
        departmentRepository.deleteByIds(ids);
    }
}