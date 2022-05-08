package com.app.DVDLibrary.dao;

import com.app.DVDLibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDDaoFileImpl implements DVDDao{

    private Map<String, DVD> DVDs = new HashMap<>();

    public static final String DVD_FILE = "DVD.txt";
    public static final String DELIMITER = "::";

    private DVD unmarshallDVD(String DVDAsText){
        String[] DVDIndex = DVDAsText.split(DELIMITER);
        String DVDTitle = DVDIndex[0];

        DVD DVDFromFile = new DVD(DVDTitle);

        DVDFromFile.setDirector(DVDIndex[1]);
        DVDFromFile.setStudio(DVDIndex[2]);
        DVDFromFile.setReleaseDate(DVDIndex[3]);
        DVDFromFile.setRating(DVDIndex[4]);
        DVDFromFile.setNotes(DVDIndex[5]);

        return DVDFromFile;
    }

    private void loadDVD() throws DVDDaoException{
        Scanner scanner;
        String currentLine;
        DVD currentDVD;

        try {
            scanner = new Scanner(
                        new BufferedReader(
                            new FileReader(DVD_FILE)));
        }
        catch (FileNotFoundException e) {
            throw new DVDDaoException("Could not load DVD library", e);
        }

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            if (!currentLine.isEmpty()) {
                DVDs.put(currentDVD.getTitle(), currentDVD);
            }
        }
        scanner.close();
    }

    private String marshalledDVD(DVD aDVD){
        String DVDAsText = aDVD.getTitle() + DELIMITER;
        DVDAsText += aDVD.getDirector() + DELIMITER;
        DVDAsText += aDVD.getStudio() + DELIMITER;
        DVDAsText += aDVD.getReleaseDate() + DELIMITER;
        DVDAsText += aDVD.getRating() + DELIMITER;
        DVDAsText += aDVD.getNotes() + DELIMITER;

        return DVDAsText;
    }

    private void writeDVD() throws DVDDaoException{
        PrintWriter out;
        String DVDAsText;
        List<DVD> dvdList = new ArrayList(DVDs.values());

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDDaoException("Could not save DVD data", e);
        }

        for(DVD currentDVD :dvdList){
            DVDAsText = marshalledDVD(currentDVD);
            out.println(DVDAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public DVD addDVD(String DVDTitle, DVD dvd) throws DVDDaoException{
        loadDVD();
        DVD newDVD = DVDs.put(DVDTitle, dvd);
        writeDVD();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDDaoException{
        loadDVD();
        return new ArrayList(DVDs.values());
    }

    @Override
    public DVD getDVDs(String DVDTitle) throws DVDDaoException{
        loadDVD();
        return DVDs.get(DVDTitle);
    }

    @Override
    public DVD removeDVD(String DVDTitle) throws DVDDaoException{
        loadDVD();
        DVD removedDVD = DVDs.remove(DVDTitle);
        writeDVD();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String DVDTitle, DVD newDVD) throws DVDDaoException{
        loadDVD();
        DVD editedDVD = DVDs.put(DVDTitle, newDVD);
        writeDVD();
        return editedDVD;
    }

}
