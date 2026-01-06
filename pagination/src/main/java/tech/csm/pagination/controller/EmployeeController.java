package tech.csm.pagination.controller;

import tech.csm.pagination.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.csm.pagination.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    //      return page instead of List<> cz Page contains useful metedata for frontend
//            eg-getContent(), getTotalPages(), getTotalElements(), isLast(), isFast() -booleans for next/prev btns

    @GetMapping
    public Page<Employee> getAllEmployees( //u can specify params in url
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy)); //sort so that we dont get same data in nxt pages

        return repository.findAll(pageable);
    }
}
