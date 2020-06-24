package com.miguel.springboot.controller;

import com.miguel.springboot.dao.DepartmentDao;
import com.miguel.springboot.dao.EmployeeDao;
import com.miguel.springboot.entities.Department;
import com.miguel.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回列表页面
     *
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {
        //查询所有员工
        Collection<Employee> employees = employeeDao.getAll();
        //添加到域对象中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps", departments);
        return "emp/edit";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(Model model, @PathVariable("id") Integer id) {
        //查询员工信息
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        //查询部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps", departments);
        return "emp/edit";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        System.out.println("修改的员工数据：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
