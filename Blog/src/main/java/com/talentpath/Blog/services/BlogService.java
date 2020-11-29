package com.talentpath.Blog.services;

import com.talentpath.Blog.daos.BlogDao;
import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    BlogDao dao;

    @Autowired
    public BlogService(BlogDao dao){this.dao = dao;}

    //Entry services
    public List<Entry> getAllEntries() {
        return dao.getAllEntries();
    }

    public List<Entry> getEntriesByUsername(String username) {
        return dao.getEntriesByUsername(username);
    }

    public Entry getEntryById(Integer entryId) throws InvalidIdException {
        return dao.getEntryById(entryId);
    }

    public Entry addEntry(Entry entry) throws InvalidIdException, InvalidEntryException {
         return dao.addEntry(entry);
    }

    public Entry deleteEntryById(Integer entryId) throws InvalidIdException {
        return dao.deleteEntryById(entryId);
    }

    //Comment services
    public List<Comment> getAllEntryComments(Integer entryId) {
        return dao.getAllEntryComments(entryId);
    }

    public Comment addComment(Comment comment) throws InvalidCommentException {
        return dao.addComment(comment);
    }

    public Comment deleteCommentById(Integer commentId) throws InvalidIdException {
        return dao.deleteCommentById(commentId);
    }


}
