package me.mhlee.service;

import lombok.RequiredArgsConstructor;
import me.mhlee.domain.Article;
import me.mhlee.dto.AddArticleRequest;
import me.mhlee.dto.UpdateArticleRequest;
import me.mhlee.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto.toEntity());

    }

    public List<Article> getAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).get();

    }

    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest req) {
        Article article = findById(id);
        article.update(req.getTitle(), req.getContent());
        return article;
    }
}
