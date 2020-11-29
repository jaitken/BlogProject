package com.talentpath.Blog.daos;

import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;

import java.util.List;

public interface BlogDao {

    public List<Entry> getAllEntries();

    List<Entry> getEntriesByUsername(String username);

    public Entry getEntryById(Integer gameId) throws InvalidIdException;

    Entry addEntry(Entry entry) throws InvalidEntryException;

    Entry deleteEntryById(Integer entryId) throws InvalidIdException;

    List<Comment> getAllEntryComments(Integer entryId);

    Comment addComment(Comment comment) throws InvalidCommentException;

    Comment deleteCommentById(Integer commentId) throws InvalidIdException;

    void reset();

}
