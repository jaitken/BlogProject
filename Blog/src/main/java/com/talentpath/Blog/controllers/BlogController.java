package com.talentpath.Blog.controllers;

import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;
import com.talentpath.Blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

    @Autowired
    BlogService service;

    //entries
    @GetMapping("/entries")
    public List<Entry> getAllEntries(){
        return service.getAllEntries();
    }

    @GetMapping("/entries/{username}")
    public List<Entry> getEntriesByUsername(@PathVariable String username) throws InvalidIdException {
        return service.getEntriesByUsername(username);
    }

    @GetMapping("/entry/{entryId}")
    public Entry getEntryById(@PathVariable Integer entryId) throws InvalidIdException {
        return service.getEntryById(entryId);
    }

    @PostMapping("/addEntry")
    public Entry addEntry(@RequestBody Entry entry) throws InvalidIdException, InvalidEntryException {
       return service.addEntry(entry);
    }

    @DeleteMapping("/deleteEntry/{entryId}")
    public Entry deleteEntryById(@PathVariable Integer entryId) throws InvalidIdException {
        return service.deleteEntryById(entryId);
    }


    //comments
    @GetMapping("/entryComments/{entryId}")
    public List<Comment> getAllEntryComments(@PathVariable Integer entryId){
        return service.getAllEntryComments(entryId);
    }

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment) throws InvalidCommentException {
        return service.addComment(comment);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public Comment deleteCommentById(@PathVariable Integer commentId) throws InvalidIdException {
        return service.deleteCommentById(commentId);
    }

}
