package com.talentpath.Blog.daos;

import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemBlogDaoTest {

    InMemBlogDao daoToTest = new InMemBlogDao();

    @BeforeEach
    void setUp() {
        daoToTest.reset();
    }

    @Test
    void getAllEntries(){
        List<Entry> all = daoToTest.getAllEntries();
        assertEquals(0, all.size());

        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        try{
            daoToTest.addEntry(e);
            all = daoToTest.getAllEntries();
            assertEquals(1, all.size());
            assertEquals("title", all.get(0).getTitle());
        } catch (InvalidEntryException ex) {
            fail("Unexpected exception thrown during getAllEntries "+ ex);
        }

    }

    @Test
    void getEntryById() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");
        try{
            daoToTest.addEntry(e);
            Entry entry = daoToTest.getEntryById(1);
            assertEquals(1, entry.getId());
            assertEquals("Joe", entry.getUsername());
            assertEquals("title", entry.getTitle());
            assertEquals("content", entry.getContent());
        } catch (InvalidIdException | InvalidEntryException ex) {
            fail("Unexpected exception thrown during getAllEntries "+ ex);
        }


    }

    @Test
    void getEntryByIdBadId(){
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        try{
            daoToTest.addEntry(e);
            Entry entry = daoToTest.getEntryById(2);
            fail("Should have thrown an invalid Id Exception");
        }catch (InvalidIdException ex) {
            //correct exception caught
        } catch (InvalidEntryException ex) {
            fail("Should have thrown an invalid Id Exception");
        }

    }

    @Test
    void addEntry(){
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        try {
            daoToTest.addEntry(e);
        }catch (InvalidEntryException ex){
            fail("Invalid entry exception thrown during addEntry golden path "+ ex);
        }

    }

    @Test
    void addEntryNullTitle() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle(null);
        e.setContent("content");

        try {
            daoToTest.addEntry(e);
            fail("Should have thrown an InvalidEntry Exception");
        }catch (InvalidEntryException ex){
            //correct ex caught
        }

    }

    @Test
    void addEntryBlankTitle(){
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle(" ");
        e.setContent("content");

        try {
            daoToTest.addEntry(e);
            fail("Should have thrown an InvalidEntry Exception");
        }catch (InvalidEntryException ex){
            //correct ex caught
        }

    }

    @Test
    void deleteEntryById() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        try {
            daoToTest.addEntry(e);

            daoToTest.deleteEntryById(1);
            List<Entry> all = daoToTest.getAllEntries();
            assertEquals(0, all.size());

        }catch (InvalidEntryException | InvalidIdException ex){
            fail("Unexpected exception thrown during deleteEntry goldenpath");
        }

    }

    @Test
    void deleteEntryByIdBadId() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        try {
            daoToTest.addEntry(e);
            daoToTest.deleteEntryById(2);

        }catch (InvalidEntryException ex){
            fail("Unexpected exception thrown during deleteEntry goldenpath");
        }catch (InvalidIdException ex){
            //correct exception caught
        }

    }

    @Test
    void getAllEntryComments(){
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        Comment c = new Comment();
        c.setEntryId(1);
        c.setUsername("Joe A");
        c.setContent("content");

        try{
            daoToTest.addEntry(e);
            daoToTest.addComment(c);

            List<Comment> all = daoToTest.getAllEntryComments(1);
            assertEquals(1, all.size());

        } catch (InvalidEntryException | InvalidCommentException ex ){
            fail("Unexpected exception thrown during getAllEntryComments" + ex);
        }

    }

    @Test
    void addComment() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        Comment c = new Comment();
        c.setEntryId(1);
        c.setUsername("Joe A");
        c.setContent("content");

        try{
            daoToTest.addEntry(e);
            daoToTest.addComment(c);

            List<Comment> all = daoToTest.getAllEntryComments(1);
            assertEquals(1, all.size());

        } catch (InvalidEntryException | InvalidCommentException ex ){
            fail("Unexpected exception thrown during getAllEntryComments" + ex);
        }

    }

    @Test
    void addCommentNullContent() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        Comment c = new Comment();
        c.setEntryId(1);
        c.setUsername("Joe A");
        c.setContent(null);

        try{
            daoToTest.addEntry(e);
            daoToTest.addComment(c);
            fail("Should have failed, comment has no content");

        } catch (InvalidEntryException  ex ){
            fail("Unexpected exception thrown during getAllEntryComments" + ex);
        }catch (InvalidCommentException ex){
            //correct exception caught
        }

    }

    @Test
    void deleteCommentById() {
        Entry e = new Entry();
        e.setUsername("Joe");
        e.setTitle("title");
        e.setContent("content");

        Comment c = new Comment();
        c.setEntryId(1);
        c.setUsername("Joe A");
        c.setContent("content");

        try{
            daoToTest.addEntry(e);
            daoToTest.addComment(c);

            daoToTest.deleteCommentById( 1);
            List<Comment> all = daoToTest.getAllEntryComments(1);
            assertEquals(0, all.size());

        } catch (InvalidEntryException | InvalidCommentException | InvalidIdException ex ){
            fail("Unexpected exception thrown during getAllEntryComments" + ex);
        }
    }
}