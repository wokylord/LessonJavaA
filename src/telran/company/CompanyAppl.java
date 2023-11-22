package telran.company;

import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;
import telran.company.controller.*;
import telran.view.*;


public class CompanyAppl {
    private static final String FILE_NAME = "";

    public static void main(String[] args) {
        CompanyService company = new CompanyServiceImpl();
        company.restore(FILE_NAME);
        Item[] items = CompanyItems.getItems(company);
        InputOutput io = new StandardInputOutput();
        Menu menu = new Menu(items, "Company Application");
        menu.perform(io);



    }
}
