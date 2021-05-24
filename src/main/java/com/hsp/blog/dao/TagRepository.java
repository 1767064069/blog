package com.hsp.blog.dao;

import com.hsp.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    // enquiry types by passing in number of size
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
