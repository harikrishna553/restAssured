package com.sample.app.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.util.EmployeeUtil;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

	@RequestMapping(value = "employees", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Collection<Employee>> all() throws InterruptedException {
		return ResponseEntity.ok(EmployeeUtil.all());
	}

	@RequestMapping(value = "employees/by-names", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> allByNames(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws InterruptedException {
		return ResponseEntity.ok(EmployeeUtil.getByFirstAndLastName(firstName, lastName));
	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> create(@RequestBody Employee emp) throws InterruptedException {
		return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeUtil.create(emp));
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> byId(@PathVariable int id) throws InterruptedException {
		if (!EmployeeUtil.isExist(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(EmployeeUtil.byId(id));
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteById(@PathVariable int id) throws InterruptedException {
		if (!EmployeeUtil.isExist(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(EmployeeUtil.delete(id));
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateById(@PathVariable int id, @RequestBody Employee emp)
			throws InterruptedException {
		if (!EmployeeUtil.isExist(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(EmployeeUtil.updateById(id, emp));
	}

}
