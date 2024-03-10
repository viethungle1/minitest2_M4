package com.example.minitest2.controller;

import com.example.minitest2.model.entity.Category;
import com.example.minitest2.model.entity.Tasks;
import com.example.minitest2.service.impl.CategoryService;
import com.example.minitest2.service.impl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.jar.Attributes;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Value("${upload}")
    private String upload;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> listCategory() {
        return categoryService.findAll();
    }

    @GetMapping
    public String listTask(Model model) {
        model.addAttribute("listTask",taskService.findAll());
        return "/task/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("task", new Tasks());
        return "/task/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam MultipartFile fileUpload,@ModelAttribute("task") Tasks tasks, RedirectAttributes redirectAttributes) throws IOException {
        String nameImg = fileUpload.getOriginalFilename();
        FileCopyUtils.copy(fileUpload.getBytes(),new File(upload+nameImg));
        tasks.setImgStr(nameImg);
        taskService.save(tasks);
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Tasks> task = taskService.findById(id);
        if (task.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/task/update");
            modelAndView.addObject("task", task.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("task") Tasks tasks, RedirectAttributes redirect) {
        taskService.save(tasks);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/tasks";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        taskService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/tasks";
    }
    @GetMapping("/total")
    public String totalAmount(Model model) {
        model.addAttribute("total",categoryService.totalAmountOfCategories());
        return "/task/total";
    }
}
