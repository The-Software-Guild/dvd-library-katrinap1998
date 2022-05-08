package com.app.DVDLibrary.dao;

import com.app.DVDLibrary.dto.DVD;

import java.util.List;

public interface DVDDao {

    DVD addDVD(String DVDTitle, DVD dvd) throws DVDDaoException;

    List<DVD> getAllDVDs() throws DVDDaoException;

    DVD getDVDs(String DVDTitle) throws DVDDaoException;

    DVD removeDVD(String DVDTitle) throws DVDDaoException;

    DVD editDVD(String DVDTitle, DVD dvd) throws DVDDaoException;


}
