package com.talentpath.Blog.daos;

import com.talentpath.Blog.exceptions.InvalidCommentException;
import com.talentpath.Blog.exceptions.InvalidEntryException;
import com.talentpath.Blog.exceptions.InvalidIdException;
import com.talentpath.Blog.models.Comment;
import com.talentpath.Blog.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile( {"production", "daotesting"} )
public class PostgresBlogDao implements BlogDao{

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Entry> getAllEntries(){
        return template.query("SELECT id, username, title, content\n" +
                "\tFROM public.\"Entries\" ORDER BY id DESC;", new EntryMapper());
    }

    @Override
    public List<Entry> getEntriesByUsername(String username) {
        List<Entry> toReturn = template.query("SELECT id, username, title, content FROM public.\"Entries\" \n" +
                "WHERE username = '"+username+"' ORDER BY id DESC;", new EntryMapper());
        return toReturn;

    }


    @Override
    public Entry getEntryById(Integer entryId) throws InvalidIdException {
        Entry entry = null;
        try{
           entry = template.queryForObject("SELECT id, username, title, content FROM public.\"Entries\"\n" +
                    "WHERE id = '"+entryId+"';", new EntryMapper());
        }
        catch (DataAccessException ex){
            throw new InvalidIdException("could not find entry with matching id: "+ ex);
        }
        return entry;
    }

    @Override
    public Entry addEntry(Entry entry) throws InvalidEntryException {

        if(entry.getTitle() == null || entry.getTitle().isEmpty() || entry.getTitle().isBlank()){
            throw new InvalidEntryException("Cannot add entry without a title");
        }


        Entry e = null;
        try{
            e = template.queryForObject("INSERT INTO public.\"Entries\"(username, title, content)\n" +
                    "VALUES ('"+entry.getUsername()+"','"+entry.getTitle()+"', '"+entry.getContent()+"') RETURNING id, username, title, content;", new EntryMapper());
        }catch (DataAccessException ex){
            throw new InvalidEntryException("Could not add entry: "+ ex);
        }
        return e;
    }

    @Override
    public Entry deleteEntryById(Integer entryId) throws InvalidIdException {
        Entry toReturn = null;
        try{
            //first have to delete all comments associated with entry
            template.update("DELETE FROM public.\"Comments\" WHERE \"entryId\" = '"+entryId+"';");

            //then can delete actual entry
            toReturn = getEntryById(entryId);
            int rows = template.update("DELETE FROM public.\"Entries\"" +
                                " WHERE id = '"+entryId+"';");
        }
        catch (DataAccessException ex){
            throw new InvalidIdException("Could not delete item with given Id: "+ex);
        }

        return toReturn;
    }

    @Override
    public List<Comment> getAllEntryComments(Integer entryId) {
        return template.query("SELECT id, \"entryId\", username, content FROM public.\"Comments\" " +
                                    "WHERE \"entryId\" = '"+entryId+"' ORDER by id ASC;", new CommentMapper());
    }

    @Override
    public Comment addComment(Comment comment) throws InvalidCommentException {

        if(comment.getContent() == null || comment.getContent().isEmpty() || comment.getContent().isBlank()){
           throw new InvalidCommentException("cannot add comment with no content");
        }

        Comment c = null;
        try{
            c =  template.queryForObject("INSERT INTO public.\"Comments\"(\"entryId\", username, content)\n" +
                    "VALUES ('"+comment.getEntryId()+"', 'Joe A', '"+comment.getContent()+"') RETURNING id, \"entryId\", username, content;", new CommentMapper());
        }catch (DataAccessException ex){
            throw new InvalidCommentException("Could not add comment: "+ ex);
        }
        return c;
    }

    @Override
    public Comment deleteCommentById(Integer commentId) throws InvalidIdException {
        Comment comment = null;
        try{
            comment = template.queryForObject("DELETE FROM public.\"Comments\" WHERE \"id\" = '"+commentId+"'\n" +
                    "RETURNING id, \"entryId\", username, content;", new CommentMapper());
        }catch (DataAccessException ex){
            throw new InvalidIdException("Could not find comment with given id");
        }

        return comment;
    }

    @Override
    public void reset() {
        template.update("TRUNCATE \"Comments\", \"Entries\";");
        template.update("ALTER SEQUENCE public.\"Entries_id_seq\" RESTART WITH 1;");
        template.update("ALTER SEQUENCE public.\"Comments_id_seq\" RESTART WITH 1;");
    }


    class EntryMapper implements RowMapper<Entry>{

        @Override
        public Entry mapRow(ResultSet resultSet, int i) throws SQLException {
            Entry entry = new Entry();
            entry.setId(resultSet.getInt("id"));
            entry.setUsername(resultSet.getString("username"));
            entry.setTitle(resultSet.getString("title"));
            entry.setContent(resultSet.getString("content"));

            return entry;
        }
    }

    class CommentMapper implements RowMapper<Comment>{

        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setEntryId(resultSet.getInt("entryId"));
            comment.setUsername(resultSet.getString("username"));
            comment.setContent(resultSet.getString("content"));
            return comment;
        }
    }

}
