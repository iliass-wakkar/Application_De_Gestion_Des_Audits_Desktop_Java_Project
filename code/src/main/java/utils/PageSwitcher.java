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
//        AccountSessionHandler.loadCurrentAccountSession();

        frame = new JFrame("Audit management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);



        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        pages = new HashMap<>();

        // Add pages
        addPage("login", PagesGetter.LoginPage );


        // Set the initial page

        switchPage("login");

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
