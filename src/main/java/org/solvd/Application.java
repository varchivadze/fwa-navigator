package org.solvd;

import org.solvd.controller.NavigatorController;
import org.solvd.model.AddressNode;
import org.solvd.service.AlgorithmService;
import org.solvd.service.DataInitService;
import org.solvd.service.Impl.AlgorithmServiceImpl;
import org.solvd.service.UserInputHandler;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // init of data migration and graf loader
       // DataInitService dataInitService = new DataInitService();
       // Map<Long, AddressNode> graph = dataInitService.loadGraphFromDatabase();

        // initialization with data from db
//AlgorithmService algorithmService = new AlgorithmServiceImpl(graph);

        // controller of the app
       // NavigatorController navigatorController = new NavigatorController(algorithmService);
       // navigatorController.start();
        UserInputHandler userInputHandler = new UserInputHandler();
        userInputHandler.getAddressesDb();
    }
}
