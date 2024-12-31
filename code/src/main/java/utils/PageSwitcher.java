package utils;



import controller.AccountSessionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;



public class PageSwitcher {
    private JFrame frame;
  static    private JPanel mainPanel;
    static private CardLayout cardLayout;
   static   private Map<String, JPanel> pages; // Map to store pages by name



    public PageSwitcher() {
        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        pages = new HashMap<>();

        AccountSessionHandler.loadCurrentAccountSession();
        System.out.println("the current user session : " + ControllersGetter.currentAccountSession);

        frame = new JFrame("Audit management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);




        // Add pages
        addPage("login", PagesGetter.LoginPage );
        addPage("adminDashboard", PagesGetter.AdminDashBoardPage );
        addPage("auditorDashboard", PagesGetter.AuditorDashboardPage );



        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        handleFirstPageLoading();
    }

    private void  handleFirstPageLoading(){
        // Set the initial page
        if(ControllersGetter.currentAccountSession==null){
            switchPage("login");
        }
        else if(ControllersGetter.currentAccountSession.isAdmin()){

            switchPage("adminDashboard");
        }
        else {
            switchPage("auditorDashboard");
        }

    }

    // Method to add a page
    private void addPage(String name, JPanel page) {
        pages.put(name, page);
        mainPanel.add(page, name);
    }

    // Method to switch between pages
    static public void switchPage(String pageName) {
        if (pages.containsKey(pageName)) {
            cardLayout.show(mainPanel, pageName);
        } else {
            System.out.println("Page not found: " + pageName);
        }
    }

    public static void main(String[] args) {
        System.out.println("nice to meet you");
        PageSwitcher pageSwitcher = new PageSwitcher();
//        SwingUtilities.invokeLater(PageSwitcher::new);
    }
}
