package com.itechart.stlab.contacts.dao.impl;

import com.itechart.stlab.contacts.dao.PhoneNumberDao;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.PhoneNumber;
import com.itechart.stlab.contacts.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PhoneNumberDaoImpl implements PhoneNumberDao {

    private static Logger LOGGER = LogManager.getLogger(PhoneNumberDaoImpl.class);

    private static String SQL_INSERT_PHONE_NUMBER ="INSERT INTO `contacts`.`phone_number` (`country_code`, `phone_code`," +
            " `number`, `type_phone`, `comment`, `contact_id`) VALUES (?, ?, ?, ?, ?, ?);";
    private static String SQL_SELECT_PHONE_NUMBER_BY_CONTACT_ID =
            "SELECT id_phone_number, country_code, phone_code, number, type_phone, comment FROM phone_number WHERE contact_id=?;";
    private static final String SQL_SELECT_ALL_PHONES =
            "SELECT * FROM phone_number";
    private static String SQL_DELETE_PHONE =
            "DELETE FROM `phone_number` WHERE id_phone_number =?;";
    private static String SQL_SELECT_PHONE_BY_ID	=
            "SELECT id_phone_number, country_code, phone_code, number, type_phone, comment FROM phone_number WHERE id_phone_number=?";
    private static String SQL_UPDATE_PHONE_NUMBER =
            "UPDATE `phone_number` SET country_code=?, phone_code=?, number=?, type_phone=?, comment=? WHERE id_phone_number=?";
    @Override
    public void create(PhoneNumber entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PHONE_NUMBER)){

            preparedStatement.setString(1, entity.getCountryCode());
            preparedStatement.setString(2, entity.getPhoneCode());
            preparedStatement.setString(3, entity.getNumber());
            preparedStatement.setString(4, entity.getType().toString());
            preparedStatement.setString(5, entity.getComment());
            preparedStatement.setInt(6, entity.getContactId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("Problem in  PhoneNumberDaoImpl.create() -> check preparedStatement or SQL query", e);
        }
    }

    @Override
    public void update(PhoneNumber entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHONE_NUMBER)){
            preparedStatement.setString(1, entity.getCountryCode());
            preparedStatement.setString(2, entity.getPhoneCode());
            preparedStatement.setString(3, entity.getNumber());
            preparedStatement.setString(4, entity.getType().toString());
            preparedStatement.setString(5, entity.getComment());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Problem in  PhoneNumberDaoImpl.update() -> check preparedStatement or SQL query", e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PHONE)){
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Deliting phone failed, no rows affected.");
            }
        }catch(SQLException e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<PhoneNumber> getAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<PhoneNumber> getById(Integer id) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PHONE_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                LOGGER.log(Level.INFO, "getById() PhoneNumberDaoImpl resultSet successfull!");
                return Optional.ofNullable(createResultSetPhoneNumber(resultSet));

            } else {
                LOGGER.log(Level.INFO, "Error in gerById() PhoneNumberDaoImpl");
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new DaoException("Error has accurred in query or in database",e);
        }
    }

    private PhoneNumber createResultSetPhoneNumber(ResultSet resultSet) throws SQLException{
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(resultSet.getInt("id_phone_number"));
        phoneNumber.setCountryCode(resultSet.getString("country_code"));
        phoneNumber.setPhoneCode(resultSet.getString("phone_code"));
        phoneNumber.setNumber(resultSet.getString("number"));
        phoneNumber.setType(PhoneNumber.TypeCode.valueOf(resultSet.getString("type_phone").toUpperCase()));
        phoneNumber.setComment(resultSet.getString("comment"));

        return phoneNumber;
    }

    @Override
    public Set<PhoneNumber> getByContactId(Integer contactId) throws DaoException {
        Set<PhoneNumber>  phoneNumbers = new HashSet<>();
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PHONE_NUMBER_BY_CONTACT_ID)){
            preparedStatement.setInt(1, contactId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PhoneNumber phoneNumber= createResultSetPhoneNumber(resultSet);
                phoneNumbers.add(phoneNumber);
                LOGGER.log(Level.INFO, " Successfully searching phoneNumbers!");
            }
        }catch (SQLException e){
            throw  new DaoException("Error has occurred in query or in database",e);
        }

        return phoneNumbers;
    }

    @Override
    public Set<PhoneNumber> getAllPhoneNumbers() throws DaoException {
        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        try( Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PHONES);
            while (resultSet.next()) {
                PhoneNumber phoneNumber = createResultSetPhoneNumber(resultSet);
                phoneNumbers.add(phoneNumber);
            }
        }catch(SQLException e){
            throw new DaoException(e);
        }
        return phoneNumbers;
    }
}
