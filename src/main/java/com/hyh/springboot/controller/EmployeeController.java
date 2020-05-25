package com.hyh.springboot.controller;

import com.hyh.springboot.dao.DepartmentDao;
import com.hyh.springboot.dao.EmployeeDao;
import com.hyh.springboot.entities.Department;
import com.hyh.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //查询所有员工返回列表
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        //讲查询到的数据放入请求域中
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String addToPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        //跳转添加页面,查询出所有部门，并在页面显示
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到员工列表页面
        System.out.println("保存的员工信息："+employee);
        //保存员工
        employeeDao.save(employee);
        //redirect:表示重定向到一个地址
        //forward:表示转发到一个地址
        return"redirect:emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String updateToPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        //查询出指定id的员工信息
        System.out.println(model.addAttribute("emp", employee));
        Collection<Department> departments = departmentDao.getDepartments();
        //查询出所有部门，并在页面显示
        model.addAttribute("depts",departments);
        //跳转修改（添加）页面
        return "emp/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        //来到员工列表页面
        System.out.println("修改的员工信息："+employee);
        //保存员工
        employeeDao.save(employee);
        return"redirect:emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }



}
