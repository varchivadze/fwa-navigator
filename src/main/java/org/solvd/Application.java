package org.solvd;

import org.solvd.controller.NavigatorController;
import org.solvd.model.AddressNode;
import org.solvd.service.AlgorithmService;
import org.solvd.service.DataInitService;
import org.solvd.service.Impl.AddressServiceImpl;
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
//        UserInputHandler userInputHandler = new UserInputHandler();
//        userInputHandler.getAddressesDb();
        AddressServiceImpl addressService = new AddressServiceImpl();
        AddressNode noed = new AddressNode();
        noed.setCountry("RP");
        noed.setCity("Warsaw");
        noed.setStreet("aleja Klasztorna");
        noed.setUnit("124");
        noed = addressService.read(noed);
        System.out.println(noed.getId());
        System.out.println(noed.getCountry());
        System.out.println(noed.getCity());
        System.out.println(noed.getStreet());
        System.out.println(noed.getUnit());

        System.out.println(noed.getId());
        System.out.println(noed.getCountry());
        System.out.println(noed.getCity());
        System.out.println(noed.getStreet());
        System.out.println(noed.getUnit());

        System.out.println(noed);

        noed.setStreet("aleja Klasztorna");
        System.out.println(noed);
    }
}
