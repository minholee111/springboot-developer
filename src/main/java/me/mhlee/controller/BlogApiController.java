package me.mhlee.controller;

import lombok.RequiredArgsConstructor;
import me.mhlee.domain.Article;
import me.mhlee.dto.AddArticleRequest;
import me.mhlee.dto.ArticleResponse;
import me.mhlee.dto.UpdateArticleRequest;
import me.mhlee.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    //public ResponseEntity<Article> addArtcle(@RequestBody AddArticleRequest request){
    //Article savedArticle = blogService.save(request);
    //return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);

    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ArticleResponse(savedArticle));
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
//       List<Article> articleList = blogService.getAll();
//       for(Article article : articleList){
//          ArticleResponse ar = new ArticleResponse(article);
//           articleResponseList.add(ar);
// }
        List<ArticleResponse> result = blogService.getAll()
                .stream()
                //.map(a -> new ArticleResponse(a))
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        blogService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") Long id,
                                                         @RequestBody UpdateArticleRequest req) {
        Article article = blogService.update(id, req);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
}