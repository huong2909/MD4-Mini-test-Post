package com.codegym.md4minitestpost2006.repository;


import com.codegym.md4minitestpost2006.model.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<Post,Long> {
    Iterable<Post> findAllByCreateAtBetween(LocalDateTime from, LocalDateTime to);

    Iterable<Post> findAllByTitleContaining(String name);

    @Query(value = "select * from posts where title like :title and create_at between :dateFrom and :dateTo", nativeQuery = true)
    Iterable<Post> findByTitleAndCreateAt(@Param("title") String title, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo")LocalDateTime dateTo);

    @Query(value = "select * from posts where create_at between :dateFrom and :dateTo order by create_at ", nativeQuery = true)
    Iterable<Post> findAllOrderByCreateAt(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo")LocalDateTime dateTo);

    @Query(value = "select * from posts where title like :title and create_at between :dateFrom and :dateTo order by create_at", nativeQuery = true)
    Iterable<Post> findByTitleAndCreateAtOrderByCreateAt(@Param("title") String title, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo")LocalDateTime dateTo);
}


