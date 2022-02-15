package com.vti.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.vti.demo.dto.AccountDTO;
import com.vti.demo.dto.DetailDepartmentDTO;
import com.vti.demo.dto.filter.DepartmentFilter;
import com.vti.demo.form.DepartmentFormForCreating;
import com.vti.demo.form.DepartmentFormForUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vti.demo.dto.DepartmentDTO;
import com.vti.demo.entity.Department;
import com.vti.demo.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin(origins = "*")

public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @GetMapping()
    public ResponseEntity<?> getAllDepartments(
            Pageable pageable,
            @RequestParam(required = false) String search,
            DepartmentFilter filter) throws ParseException {
        Page<Department> entitiesPage = service.getAllDepartments(pageable, search, filter);

        // convert entities --> dtos
        // https://stackoverflow.com/questions/39036771/how-to-map-pageobjectentity-to-pageobjectdto-in-spring-data-rest
        Page<DepartmentDTO> dtoPage = entitiesPage.map(new Function<Department, DepartmentDTO>() {
            @Override
            public DepartmentDTO apply(Department entity) {
                DepartmentDTO dto = new DepartmentDTO(entity.getId(), entity.getName(),
                        new AccountDTO(entity.getAuthor().getId(), entity.getAuthor().getFullName()),
                        entity.getCreateDate());
                return dto;
            }
        });

        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDepartmentByID(@PathVariable(name = "id") short id) {

        Department entity = service.getDepartmentByID(id);

        DetailDepartmentDTO dto = new DetailDepartmentDTO(entity.getId(), entity.getName(),
                new AccountDTO(entity.getAuthor().getId(), entity.getAuthor().getFullName()), entity.getCreateDate());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentFormForCreating form) {
        service.createDepartment(form);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/name/{name}/exists")
    public ResponseEntity<?> existsByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(service.isDepartmentExistsByName(name), HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") short id,
                                              @RequestBody DepartmentFormForUpdating form) {
        service.updateDepartment(id, form);
        return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") short id) {
        service.deleteDepartment(id);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDepartments(@RequestParam(name = "ids") List<Short> ids) {
        service.deleteDepartments(ids);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }
}