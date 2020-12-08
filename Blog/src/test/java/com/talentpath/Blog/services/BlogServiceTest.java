package com.talentpath.Blog.services;

import com.talentpath.Blog.daos.BlogDao;
import com.talentpath.Blog.daos.InMemBlogDao;
import com.talentpath.Blog.models.Entry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlogServiceTest {

    BlogService service = new BlogService(new InMemBlogDao());
    @Test
    void getAllEntries() {
        List<Entry> allEntries = service.getAllEntries();
        assertEquals(0, allEntries.size());
    }

    @Test
    void getEntriesByUsername() {
    }

    @Test
    void getEntryById() {
    }

    @Test
    void addEntry() {
    }

    @Test
    void deleteEntryById() {
    }

    @Test
    void getAllEntryComments() {
    }

    @Test
    void addComment() {
    }

    @Test
    void deleteCommentById() {
    }
}