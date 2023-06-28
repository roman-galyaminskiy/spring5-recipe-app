package guru.springframework.controllers;

import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;

    public IndexController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index() {
        System.out.println(categoryRepository.findByDescription("American").get().getDescription());
        return "index";
    }
}
