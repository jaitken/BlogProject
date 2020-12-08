package com.talentpath.Blog.daos;

import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class InMemBlogDao implements BlogDao {

    List<Entry> entries = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    int idCountEntry = 1;
    int idCountComment = 1;

    @Override
    public List<Entry> getAllEntries() {
        return entries;
    }

    @Override
    public List<Entry> getEntriesByUsername(String username) {
        List<Entry> toReturn = new ArrayList<>();
        for(Entry e : entries){
            if(e.getUsername().equals(username)){
                toReturn.add(e);
            }
        }
        return toReturn;
    }

    @Override
    public Entry getEntryById(Integer entryId) throws InvalidIdException {
        for(Entry e : entries){
            if(e.getId().equals(entryId)){
                return e;
            }
        }
        throw new InvalidIdException("Could not find entry with matching id");
    }

    @Override
    public Entry addEntry(Entry entry) throws InvalidEntryException {
        if(entry.getTitle() == null || entry.getTitle().isEmpty() || entry.getTitle().isBlank()){
            throw new InvalidEntryException("Cannot add entry without a title");
        }
        entry.setId(idCountEntry);
        idCountEntry++;
        entries.add(entry);
        return entry;
    }

    @Override
    public Entry deleteEntryById(Integer entryId) throws InvalidIdException {
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).getId() == entryId){
               Entry e = entries.remove(i);
               return e;
            }
        }
        throw new InvalidIdException("Could not find entry with matching id");
    }

    @Override
    public List<Comment> getAllEntryComments(Integer entryId) {

        List<Comment> toReturn = new ArrayList<>();
        for(Comment c : comments){
            if(c.getEntryId().equals(entryId)){
                toReturn.add(c);
            }
        }
        return toReturn;
    }

    @Override
    public Comment addComment(Comment comment) throws InvalidCommentException {

        if(comment.getContent() == null || comment.getContent().isEmpty() || comment.getContent().isBlank()){
            throw new InvalidCommentException("cannot add comment with no content");
        }
        comment.setId(idCountComment);
        idCountComment++;
        comments.add(comment);
        return comment;
    }

    @Override
    public Comment deleteCommentById(Integer commentId) throws InvalidIdException {

        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getId() == commentId){
                Comment c = comments.remove(i);
                return c;
            }
        }
        throw new InvalidIdException("Could not find entry with matching id");
    }

    @Override
    public void reset() {
        entries.clear();
        comments.clear();
        idCountEntry = 1;
        idCountComment = 1;
    }
}
