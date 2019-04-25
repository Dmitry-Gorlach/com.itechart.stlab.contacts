package com.itechart.stlab.contacts.dao.impl;

import com.itechart.stlab.contacts.dao.ContactDao;
import com.itechart.stlab.contacts.exception.DaoException;
import com.itechart.stlab.contacts.model.Attachment;
import com.itechart.stlab.contacts.model.Contact;
import com.itechart.stlab.contacts.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactDaoImpl implements ContactDao {

    private static Logger LOGGER = LogManager.getLogger(ContactDaoImpl.class);

    private static String SQL_INSERT_USER =
            "INSERT INTO contact (first_name, surname, last_name, birthday, gender, nationality, " +
                    "marital_status, company, website, email, country, city, street, house, flat, postcode)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?);";

    private static final String SQL_SELECT_ALL_CONTACTS =
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact";
    private static String SQL_SELECT_CONTACT_BY_ID	=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE id_contact=?";
    private static String SQL_SELECT_CONTACT_BY_FIRST_NAME=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE first_name=?";
    private static String SQL_SELECT_CONTACT_BY_LAST_NAME=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE last_name=?";
    private static String SQL_SELECT_CONTACT_BY_SURNAME=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE surname=?";
    private static String SQL_SELECT_CONTACT_BY_GENDER=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE gender=?";
    private static String SQL_SELECT_CONTACT_BY_MARITAL_STATUS=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE marital_status=?;";
    private static String SQL_SELECT_CONTACT_BY_NATIONALITY=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality," +
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE nationality=?;";
    private static String SQL_SELECT_CONTACT_BY_BIRTHDAY_ASC=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality,"+
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE birthday >=? ORDER BY birthday ASC;";
    private static String SQL_SELECT_CONTACT_BY_BIRTHDAY_DESC=
            "SELECT id_contact, first_name, surname, last_name, birthday, gender, nationality,"+
                    "marital_status, company, website, email, photo_uri, country, city, street, house, flat, postcode FROM contact WHERE birthday <=? ORDER BY birthday DESC;";

    private static String SQL_UPDATE_CONTACT_TEST =

            "UPDATE `contact` SET first_name=?, surname=?, last_name=?, birthday=?, gender=?, nationality=?, marital_status=?," +
                    "company=?, website=?, email=?, country=?, city=?, street=?, house=?, flat=?, postcode=? WHERE id_contact=?";

    private static String SQL_UPDATE_CONTACT =
            "UPDATE `contact` SET first_name=?, last_name=?, surname=?, birthday=?, gender=?, nationality=?, marital_status=?," +
                    "company=?, website=?, email=?, photo_uri=?, country=?, city=?, street=?, house=?, flat=?,postcode=? WHERE id_contact=?";
    private static String SQL_UPDATE_CONTACT_WITHOUT_PHOTO =
            "UPDATE `contact` SET first_name=?, last_name=?, surname=?, birthday=?, gender=?, nationality=?, marital_status=?," +
                    "company=?, website=?, email=?, country=?, city=?, street=?, house=?, flat=?,postcode=? WHERE id_contact=?";
    private static String SQL_DELETE_CONTACT =
            "DELETE FROM `CONTACT` WHERE id_contact =?;";
    @Override
    public void create(Contact entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)){

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getLastname());
            preparedStatement.setDate(4, java.sql.Date.valueOf(entity.getBirthday()));
            preparedStatement.setString(5, entity.getGender().toString());
            preparedStatement.setString(6, entity.getNationality());
            preparedStatement.setString(7, entity.getMaritalStatus().toString());
            preparedStatement.setString(8, entity.getCompany());
            preparedStatement.setString(9, entity.getWebSite());
            preparedStatement.setString(10, entity.getEmail());
            preparedStatement.setString(11, entity.getCountry());
            preparedStatement.setString(12, entity.getCity());
            preparedStatement.setString(13, entity.getStreet());
            preparedStatement.setString(14, entity.getHouse());
            preparedStatement.setString(15, entity.getFlat());
            preparedStatement.setString(16, entity.getPostcode());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("Problem in  ContactDaoImpl.create() -> wrong preparedStatement", e);
        }
    }

    @Override
    public void update(Contact entity) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CONTACT_TEST)){
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getSurname());
            preparedStatement.setString(4, entity.getLastname());
            preparedStatement.setDate(5, java.sql.Date.valueOf(entity.getBirthday()));
            preparedStatement.setString(6, entity.getGender().toString());
            preparedStatement.setString(7, entity.getNationality());
            preparedStatement.setString(8, entity.getMaritalStatus().toString());
            preparedStatement.setString(9, entity.getCompany());
            preparedStatement.setString(10, entity.getWebSite());
            preparedStatement.setString(11, entity.getEmail());
            preparedStatement.setString(12, entity.getCountry());
            preparedStatement.setString(13, entity.getCity());
            preparedStatement.setString(14, entity.getStreet());
            preparedStatement.setString(15, entity.getHouse());
            preparedStatement.setString(16, entity.getFlat());
            preparedStatement.setString(17, entity.getPostcode());
            preparedStatement.setString(18, entity.getPhoto());
            preparedStatement.executeUpdate();

        if(preparedStatement.executeUpdate() == 0) {
            throw  new DaoException("Error, no line is changed.");
        }
    }catch (SQLException e){
        throw  new DaoException("SQL query failed", e);
    }
}

    @Override
    public void delete(Integer id) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CONTACT)){
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Deliting failed, no rows affected.");
            }
        }catch(SQLException e){
            throw new DaoException(e);
        }

    }

    @Override
    public List<Contact> getAll() throws DaoException {
        List<Contact> contacts = new ArrayList<>();
       try( Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
           ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CONTACTS);
           while (resultSet.next()) {
               Contact contact = createResultSetContact(resultSet);
               contacts.add(contact);
           }
       }catch(SQLException e){
               throw new DaoException(e);
           }
            return contacts;
    }

    @Override
    public Optional<Contact> getById(Integer id) throws DaoException {

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CONTACT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                LOGGER.log(Level.INFO, "getById() ContactDaoImpl resultSet successfull!");
                return Optional.ofNullable(createResultSetContact(resultSet));

            } else {
                LOGGER.log(Level.INFO, "Error in gerById() ContactDaoImpl");
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new DaoException("Error has accurred in query or in database",e);
        }
    }

       @Override
    public List<Contact> getByFirstName(String firstName) throws DaoException {
        List<Contact>  contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_FIRST_NAME, firstName);
       return contacts;
    }

    @Override
    public List<Contact> getBySurname(String surname) throws DaoException {
        List<Contact>  contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_SURNAME, surname);
        return contacts;
    }

    public List<Contact> executeSqlWithParameter(String SQL, String parameter) throws DaoException {
        List<Contact>  contacts = new ArrayList<>();
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Contact contact = createResultSetContact(resultSet);
                contacts.add(contact);
                LOGGER.log(Level.INFO, "getByFirstname() ContactDaoImpl resultSet successfull!");
            }
        }catch (SQLException e){
            throw  new DaoException("Error has accurred in query or in database",e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getByLastName(String lastName) throws DaoException {
        List<Contact>  contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_LAST_NAME, lastName);
        return contacts;
    }

    @Override
    public List<Contact> getBirthdayAsc(LocalDate birthday) throws DaoException {
        List<Contact> contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_BIRTHDAY_ASC, birthday.toString());
        return contacts;
    }

    @Override
    public List<Contact> getBirthdayDesc(LocalDate birthday) throws DaoException {
        List<Contact> contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_BIRTHDAY_DESC, birthday.toString());
        return contacts;
    }

    @Override
    public List<Contact> getByGender(Contact.Gender gender) throws DaoException {
        List<Contact> contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_GENDER, gender.toString());
        return contacts;
    }

    @Override
    public List<Contact> getByMaritalStatus(Contact.MaritalStatus maritalStatus) throws DaoException {
        List<Contact> contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_MARITAL_STATUS, maritalStatus.toString());
        return contacts;
    }

    @Override
    public List<Contact> getByNationality(String nationality) throws DaoException {
        List<Contact> contacts = executeSqlWithParameter(SQL_SELECT_CONTACT_BY_NATIONALITY, nationality);
        return contacts;
    }

    @Override
    public void updateContact(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                              Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus,
                              String company, String website, String email, String photo_uri, String country,
                              String city, String street, String house, String flat, String postcode) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CONTACT)){
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, surname);
            preparedStatement.setDate(4, java.sql.Date.valueOf(birthday));
            preparedStatement.setString(5, gender.toString());
            preparedStatement.setString(6, nationality);
            preparedStatement.setString(7, maritalStatus.toString());
            preparedStatement.setString(8,company);
            preparedStatement.setString(9, website);
            preparedStatement.setString(10, email);
            preparedStatement.setString(11, photo_uri);
            preparedStatement.setString(12, country);
            preparedStatement.setString(13, city);
            preparedStatement.setString(14, street);
            preparedStatement.setString(15, house);
            preparedStatement.setString(16, flat);
            preparedStatement.setString(17, postcode);
            preparedStatement.setInt(18, id);
            if(preparedStatement.executeUpdate() == 0) {
                throw  new DaoException("Error, no line is changed.");
            }
        }catch (SQLException e){
            throw  new DaoException("SQL query failed", e);
        }
    }

    @Override
    public void updateContactWithoutPhoto(Integer id, String firstName, String lastname, String surname, LocalDate birthday,
                                          Contact.Gender gender, String nationality, Contact.MaritalStatus maritalStatus, String company,
                                          String website, String email, String country, String city, String street, String house, String flat,
                                          String postcode) throws DaoException {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CONTACT_WITHOUT_PHOTO)){
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, surname);
            preparedStatement.setDate(4, java.sql.Date.valueOf(birthday));
            preparedStatement.setString(5, gender.toString());
            preparedStatement.setString(6, nationality);
            preparedStatement.setString(7, maritalStatus.toString());
            preparedStatement.setString(8,company);
            preparedStatement.setString(9, website);
            preparedStatement.setString(10, email);
            preparedStatement.setString(11, country);
            preparedStatement.setString(12, city);
            preparedStatement.setString(13, street);
            preparedStatement.setString(14, house);
            preparedStatement.setString(15, flat);
            preparedStatement.setString(16, postcode);
            preparedStatement.setInt(17, id);
            if(preparedStatement.executeUpdate() == 0) {
                throw  new DaoException("Error, no line is changed.");
            }
        }catch (SQLException e){
            throw  new DaoException("SQL query failed", e);
        }
    }


    @Override
    public void addAttachment(Attachment attachment) throws DaoException {

    }

    private  Contact createResultSetContact(ResultSet resultSet) throws SQLException{
        Contact contact = new Contact();
        contact.setId(resultSet.getInt("id_contact"));
        contact.setFirstName(resultSet.getString("first_name"));
        contact.setSurname(resultSet.getString("surname"));
        contact.setLastname(resultSet.getString("last_name"));
        contact.setBirthday(resultSet.getDate("birthday").toLocalDate());
        contact.setGender(Contact.Gender.valueOf(resultSet.getString("gender").toUpperCase()));
        contact.setNationality(resultSet.getString("nationality"));
        contact.setMaritalStatus(Contact.MaritalStatus.valueOf(resultSet.getString("marital_status").toUpperCase()));
        contact.setCompany(resultSet.getString("company"));
        contact.setWebSite(resultSet.getString("website"));
        contact.setEmail(resultSet.getString("email"));
        contact.setPhoto(resultSet.getString("photo_uri"));
        contact.setCountry(resultSet.getString("country"));
        contact.setCity(resultSet.getString("city"));
        contact.setHouse(resultSet.getString("house"));
        contact.setStreet(resultSet.getString("street"));
        contact.setFlat(resultSet.getString("flat"));
        contact.setPostcode(resultSet.getString("postcode"));
        return contact;
    }


}
