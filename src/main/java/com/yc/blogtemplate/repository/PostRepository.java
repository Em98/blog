package com.yc.blogtemplate.repository;

import com.yc.blogtemplate.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByTitleLike(String title, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Post p SET p.title=?1, p.summary=?2, p.contentMd=?3 , p.contentHtml=?4 WHERE p.id=?5")
    void updateContent(String title, String sumarry, String contentMd, String contentHtml, Long id);

    @Query(value = "SELECT new Post (p.id, p.title, p.publishData, p.postStatus)  FROM Post p")
    Page<Post> getPartFieldPage(Pageable pageable);

    @Query(value = "SELECT new Post (p.id, p.title, p.publishData, p.postStatus)  FROM Post p")
    List<Post> getPartFiel();


}
