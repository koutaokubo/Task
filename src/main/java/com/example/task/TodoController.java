package com.example.task;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.task.db.TodoRepository;
import com.example.task.entity.Todo;


@Controller
public class TodoController {

  private final TodoRepository todoRepository;

  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @RequestMapping("/todo")
  public String index(Model model) {
      List<Todo> todoList = todoRepository.findAll();
      model.addAttribute("todoList", todoList);
      return "index";
  }

  @RequestMapping("/todo/add")
  public String add(Model model, Todo todo) {
    return "createTask";
  }

  @RequestMapping(value = "/todo/add", method = RequestMethod.POST)
  public String create(Model model, Todo todo) {
    // mv.setViewName("createTask");
    // mv.addObject("id", todo.getId());
    // mv.addObject("title", todo.getTitle());
    // mv.addObject("detail", todo.getDetail());
    // return mv;
    Todo todo1 = new Todo();
    todo1.setId(todo.getId());
    todo1.setTitle(todo.getTitle());
    todo1.setDetail(todo.getDetail());
    todoRepository.save(todo1);
    return "redirect:/todo";
  }

  @RequestMapping("/todo/del/{id}")
  public String destroy(@PathVariable Long id) {
    todoRepository.deleteById(id);
    return "redirect:/todo";
  }  

  @RequestMapping(value = "/todo/check/{id}/{done}")
  public String check(@PathVariable Long id, @PathVariable boolean done) {
    todoRepository.checkTask(!done, id);
    return "redirect:/todo";
  }

  @RequestMapping("/todo/edit/{id}")
  public String edit(@PathVariable Long id, Model model, Todo todo) {
    Todo todo1 = this.todoRepository.findById(id).orElseThrow();
    if (todo.getTitle() == null) {
      todo.setTitle(todo1.getTitle());
      todo.setDetail(todo1.getDetail());
    }
    model.addAttribute("todo", todo);
    return "edit";
  }

  @RequestMapping("/todo/edit/{id}/exe")
  public String update(@PathVariable Long id, Model model, @Validated Todo todo, BindingResult result) {
    Todo todo1 = this.todoRepository.findById(id).orElseThrow();
    if (result.hasErrors()) {
      model.addAttribute("todo", todo1);
      return "edit";
    }
    this.todoRepository.updateTitleAndDetail(todo.getTitle(), todo.getDetail(), id);
    return "redirect:/todo";
  }
}
