package stormsprid.endtermtest.Controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stormsprid.endtermtest.Entity.Task;
import stormsprid.endtermtest.Repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;


    @GetMapping
    public String listTasks(Model model){
        model.addAttribute("tasks",taskRepository.findAll());
        return "tasks";

    }


    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("task",new Task());
        return"task-form";

    }


    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task){
        taskRepository.save(task);
        return "redirect:/tasks";

    }

    @GetMapping("/edit/{id}")
    public String ShowEditTask(@PathVariable Long id,Model model){
        model.addAttribute("task",taskRepository.findById(id).orElseThrow());
        return "task-form";
    }

    @PostMapping("/edit/{id}")
        public String editTask(@PathVariable Long id,Task task){
            task.setId(id);
            taskRepository.save(task);
            return "redirect:/tasks";
        }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
           taskRepository.deleteById(id);
           return "redirect:/tasks";
    }


}
