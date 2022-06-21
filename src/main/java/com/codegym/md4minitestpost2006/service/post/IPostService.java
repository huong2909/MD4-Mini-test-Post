package com.codegym.md4minitestpost2006.service.post;


import com.codegym.md4minitestpost2006.model.Post;
import com.codegym.md4minitestpost2006.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IPostService extends IGeneralService<Post> {
    Page<Post> findAll(Pageable pageable);
    Iterable<Post> findAllByCreateAtBetween(LocalDateTime from, LocalDateTime to);
    Iterable<Post> findAllByContent(String title);

    Iterable<Post> findByTitle(String title, LocalDateTime dateFrom, LocalDateTime dateTo);

    Iterable<Post> findAllOrderByCreateAt(LocalDateTime dateFrom,LocalDateTime dateTo);

    Iterable<Post> findByTitleAndCreateAtOrderByCreateAt( String title,  LocalDateTime dateFrom, LocalDateTime dateTo);
}
