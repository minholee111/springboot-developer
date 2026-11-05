package me.mhlee.service;

import lombok.RequiredArgsConstructor;
import me.mhlee.domain.Article;
import me.mhlee.dto.AddArticleRequest;
import me.mhlee.repository.BlogRepository;
import org.springframework.stereotype.Service;

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
}
