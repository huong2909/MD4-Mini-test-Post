package com.codegym.md4minitestpost2006.service.post;


import com.codegym.md4minitestpost2006.model.Post;
import com.codegym.md4minitestpost2006.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService implements IPostService{

    @Autowired
    public IPostRepository postRepository;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Iterable<Post> findAllByCreateAtBetween(LocalDateTime from, LocalDateTime to) {
        return postRepository.findAllByCreateAtBetween(from,to);
    }

    @Override
    public Iterable<Post> findAllByContent(String title) {
        return postRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findByTitle(String title, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return postRepository.findByTitleAndCreateAt(title,dateFrom,dateTo);
    }

    @Override
    public Iterable<Post> findAllOrderByCreateAt( LocalDateTime dateFrom, LocalDateTime dateTo) {
        return postRepository.findAllOrderByCreateAt(dateFrom,dateTo);
    }

    @Override
    public Iterable<Post> findByTitleAndCreateAtOrderByCreateAt(String title, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return postRepository.findByTitleAndCreateAtOrderByCreateAt(title,dateFrom,dateTo);
    }
}
