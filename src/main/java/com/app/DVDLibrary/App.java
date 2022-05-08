package com.app.DVDLibrary;

import com.app.DVDLibrary.controller.DVDController;
import com.app.DVDLibrary.dao.DVDDao;
import com.app.DVDLibrary.dao.DVDDaoFileImpl;
import com.app.DVDLibrary.dto.DVD;
import com.app.DVDLibrary.ui.DVDView;
import com.app.DVDLibrary.ui.UserIO;
import com.app.DVDLibrary.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {

//        DVDController controller = new DVDController();
        UserIO myIO = new UserIOConsoleImpl();
        DVDView myView = new DVDView(myIO);
        DVDDao myDao = new DVDDaoFileImpl();
        DVDController controller = new DVDController(myView, myDao);
        controller.run();

    }

}
