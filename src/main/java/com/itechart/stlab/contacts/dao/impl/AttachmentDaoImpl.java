package com.itechart.stlab.contacts.dao.impl;

import com.itechart.stlab.contacts.dao.AttachmentDao;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.Attachment;
import com.itechart.stlab.contacts.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AttachmentDaoImpl implements AttachmentDao {

    private static Logger LOGGER = LogManager.getLogger(AttachmentDaoImpl.class);
    private static String SQL_INSERT_ATTACHMENT ="INSERT INTO `contacts`.`attachment` (`name_attachment`, `date_upload`," +
            " `comment`, `contact_id`) VALUES (?, ?, ?, ?);";
    private static String SQL_SELECT_ATTACHMENT_BY_CONTACT_ID =
            "SELECT id_attachment, name_attachment, date_upload, comment FROM attachment WHERE contact_id=?;";
    private static String SQL_DELETE_ATTACHMENT =
            "DELETE FROM `attachment` WHERE id_attachment =?;";
    private static String SQL_UPDATE_ATTACHMENT =
            "UPDATE `attachment` SET name_attachment=?, date_upload=?, comment=? WHERE id_attachment=?";


    @Override
    public Set<Attachment> getByContactId(Integer contactId) throws DaoException {
        Set<Attachment>  attachments = new HashSet<>();
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ATTACHMENT_BY_CONTACT_ID)){
            preparedStatement.setInt(1, contactId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Attachment attachment= createResultSetPhoneNumber(resultSet);
                attachments.add(attachment);
                LOGGER.log(Level.INFO, " Successfully searching attachments!");
            }
        }catch (SQLException e){
            throw  new DaoException("Error has occurred in query or in database",e);
        }

        return attachments;
    }

    private Attachment createResultSetPhoneNumber(ResultSet resultSet) throws SQLException{
        Attachment attachment = new Attachment();
        attachment.setId(resultSet.getInt("id_attachment"));
        attachment.setNameAttachment(resultSet.getString("name_attachment"));
        attachment.setDateUpload(resultSet.getDate("date_upload").toLocalDate());
        attachment.setComment(resultSet.getString("comment"));

        return attachment;
    }

    @Override
    public void create(Attachment entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ATTACHMENT)){
            preparedStatement.setString(1, entity.getNameAttachment());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDateUpload()));
            preparedStatement.setString(3, entity.getComment());
            preparedStatement.setInt(4, entity.getContactId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("Problem in  AttachmentDaoImpl.create() -> check preparedStatement or SQL query", e);
        }
    }

    @Override
    public void update(Attachment entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ATTACHMENT)){
            preparedStatement.setString(1, entity.getNameAttachment());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDateUpload()));
            preparedStatement.setString(3, entity.getComment());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Problem in  AttachmentDaoImpl.update() -> check preparedStatement or SQL query", e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ATTACHMENT)){
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Deleting attachment failed, no rows affected.");
            }
        }catch(SQLException e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<Attachment> getAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Attachment> getById(Integer id) throws DaoException {
        return Optional.empty();
    }
}

