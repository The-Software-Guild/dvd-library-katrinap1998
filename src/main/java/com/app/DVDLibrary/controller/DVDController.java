package com.app.DVDLibrary.controller;

import com.app.DVDLibrary.dao.DVDDao;
import com.app.DVDLibrary.dao.DVDDaoException;
import com.app.DVDLibrary.dto.DVD;
import com.app.DVDLibrary.ui.DVDView;
import com.app.DVDLibrary.ui.UserIO;
import com.app.DVDLibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DVDController {
    private UserIO io = new UserIOConsoleImpl();
//    private DVDView view = new DVDView();
//    private DVDDao dao = new DVDDaoFileImpl();

    private DVDView view;
    private DVDDao dao;

    public DVDController(DVDView view, DVDDao dao){
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editMenuCase();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDDaoException e) {
            view.displayErrorMessageBanner(e.getMessage());
        }
    }

    private void editMenuCase() throws DVDDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getEditMenuSelection();

                switch (menuSelection) {
                    case 1:
                        editDVDDirector();
                        break;
                    case 2:
                        editDVDStudio();
                        break;
                    case 3:
                        editDVDRelease();
                        break;
                    case 4:
                        editDVDRating();
                        break;
                    case 5:
                        editDVDNotes();
                        break;
                    case 6:
                        run();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        }
        catch (DVDDaoException e) {
                view.displayErrorMessageBanner(e.getMessage());
        }
    }


    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private int getEditMenuSelection(){
        return view.printEditMenuAndGetSelection();
    }


    private void createDVD() throws DVDDaoException{
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws DVDDaoException{
        view.displayAllBanner();
        List<DVD> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
    }

    private void viewDVD() throws DVDDaoException{
        view.displayDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDDaoException{
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVDDirector() throws DVDDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDEditChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        DVD dvdEdited = view.getNewDVDDirector(dvd, dvdTitle);
        DVD newDVD = dao.editDVD(dvdTitle, dvdEdited);
    }

    private void editDVDStudio() throws DVDDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDEditChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        DVD dvdEdited = view.getNewDVDStudio(dvd, dvdTitle);
        DVD newDVD = dao.editDVD(dvdTitle, dvdEdited);
    }

    private void editDVDRelease() throws DVDDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDEditChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        DVD dvdEdited = view.getNewDVDRelease(dvd, dvdTitle);
        DVD newDVD = dao.editDVD(dvdTitle, dvdEdited);
    }

    private void editDVDRating() throws DVDDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDEditChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        DVD dvdEdited = view.getNewDVDRating(dvd, dvdTitle);
        DVD newDVD = dao.editDVD(dvdTitle, dvdEdited);
    }

    private void editDVDNotes() throws DVDDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDEditChoice();
        DVD dvd = dao.getDVDs(dvdTitle);
        DVD dvdEdited = view.getNewDVDNotes(dvd, dvdTitle);
        DVD newDVD = dao.editDVD(dvdTitle, dvdEdited);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }
}









