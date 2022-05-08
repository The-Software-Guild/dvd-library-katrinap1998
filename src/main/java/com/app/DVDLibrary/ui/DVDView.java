package com.app.DVDLibrary.ui;

import com.app.DVDLibrary.dao.DVDDaoException;
import com.app.DVDLibrary.dto.DVD;

import java.util.List;

public class DVDView {

    private UserIO io;

    public DVDView(UserIO io){
        this.io = io;
    }

//                              ====== Menus ======

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit an existing DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public int printEditMenuAndGetSelection(){
        io.print("Edit Menu");
        io.print("1. Edit Director");
        io.print("2. Edit Studio");
        io.print("3. Edit Release Date");
        io.print("4. Edit Rating");
        io.print("5. Edit Notes");
        io.print("6. Return to Main Menu");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

//                              ====== Banners ======

    public void displayCreateDVDBanner(){
        io.print("===== Create DVD =====");
    }

    public void displayCreateSuccessBanner(){
        io.readString(" ===== DVD successfully created.  Please hit enter to continue ====");
    }
    public void displayAllBanner(){
        io.print("==== Display All DVDs ====");
    }

    public void displayDVDBanner(){
        io.print("==== Display DVD ====");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }

    public void displayExitBanner() {
        io.print("==== Good Bye!!! ====");
    }

    public void displayErrorMessageBanner(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    //                              ====== Get inputs ======

    public String getDVDChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public String getDVDEditChoice(){
        return io.readString("Please enter the DVD title to edit.");
    }

    //                              ====== Main methods ======

    public DVD getNewDVDInfo(){
        String title = io.readString("Enter DVD title");
        String director = io.readString("Enter DVD director");
        String studio = io.readString("Enter DVD studio");
        String releaseDate = io.readString("Enter DVD release date");
        String rating = io.readString("Enter DVD rating");
        String notes = io.readString("Enter any notes");

        DVD currentDVD = new DVD(title);

        currentDVD.setTitle(title);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setNotes(notes);

        return currentDVD;
    }

    public void displayDVDList(List<DVD> DVDList){
        for(DVD currentDVD : DVDList){
            String dvdInfo = String.format(
                    "Title: %s%n Director: %s, Studio: %s," +
                    "Rating: %s, Release: %s, Notes: %s",
                    currentDVD.getTitle(),
                    currentDVD.getDirector(),
                    currentDVD.getStudio(),
                    currentDVD.getRating(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getNotes());
            io.print(dvdInfo);
        }
        io.readString("Hit enter to continue");
    }

    public void displayDVD(DVD dvd){
        if ( dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getRating());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getNotes());
        }
        else {
            io.print("No DVD found");
        }
        io.readString("Hit enter to continue");
    }

    public void displayRemoveResult(DVD dvdRecord){
        if(dvdRecord != null) {
            io.print("DVD removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public DVD checkEditDVD(String editChoice, String editTitle, DVD dvd){
        if(dvd != null && editTitle != null) {
            dvd.setTitle(editTitle);
            dvd.setDirector(editChoice);
        }
        else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
        return dvd;
    }

    public DVD getNewDVDDirector(DVD dvd, String editTitle){
        String director = io.readString("Enter DVD director");
        return checkEditDVD(director, editTitle, dvd);
    }

    public DVD getNewDVDStudio(DVD dvd, String editTitle){
        String studio = io.readString("Enter DVD studio");
        return checkEditDVD(studio, editTitle, dvd);
    }

    public DVD getNewDVDRelease(DVD dvd, String editTitle){
        String releaseDate = io.readString("Enter DVD release date");
        return checkEditDVD(releaseDate, editTitle, dvd);
    }

    public DVD getNewDVDRating(DVD dvd, String editTitle){
        String rating = io.readString("Enter DVD rating");
        return checkEditDVD(rating, editTitle, dvd);
    }

    public DVD getNewDVDNotes(DVD dvd, String editTitle){
        String notes = io.readString("Enter any notes");
        return checkEditDVD(notes, editTitle, dvd);
    }

}


