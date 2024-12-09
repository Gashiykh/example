package stormsprid.endtermtest.Controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stormsprid.endtermtest.Entity.Task;
import stormsprid.endtermtest.Repository.TaskRepository;
import java.io.File;
import java.io.IOException;

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
    public String addTask(@ModelAttribute Task task, @RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IOException("Файл не найден");
        }

        String uploadDir = "C:/Users/gashi/OneDrive/Рабочий стол/0010101/uploads/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String absolutePath = uploadDir + image.getOriginalFilename();
        image.transferTo(new File(absolutePath));

        String relativePath = "/uploads/" + image.getOriginalFilename();
        task.setImagePath(relativePath);

        taskRepository.save(task);

        return "redirect:/tasks";
    }


    @GetMapping("/edit/{id}")
    public String ShowEditTask(@PathVariable Long id,Model model){
        model.addAttribute("task", taskRepository.findById(id).orElseThrow());
        return "task-edit";
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
