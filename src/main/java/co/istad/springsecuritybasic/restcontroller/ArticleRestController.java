package co.istad.springsecuritybasic.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {
    @GetMapping
    public String getArticles() {
        return "List of articles";
    }

    @GetMapping("/read/{id}")
    public String readArticle(@PathVariable Long id) {
        return "Reading article" + id;
    }

    @PostMapping("/write")
    public String writeArticle() {
        return "Writing article";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        return "Deleting article" + id;
    }
}
