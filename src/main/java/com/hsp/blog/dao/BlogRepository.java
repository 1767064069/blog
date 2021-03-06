package com.hsp.blog.dao;

import com.hsp.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    // enquiry types by passing in number of size
    @Query("select b from Blog b where b.isRecommend = true")
    List<Blog> findTop(Pageable pageable);

    // enquiry for search select b from Blog b where b.title like %内容%
    @Query("select b from Blog b  where b.published = true and ( b.title like ?1 or b.description like ?1 )")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);


    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b where b.published = true group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where b.published = true and function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    Blog getBlogById(Long id);

    /*@Transactional
    @Modifying
    @Query("update Blog b set b.commentCount = b.commentCount+1 where b.id = ?1")
    int addCommentCount(Long blogId);*/

}