package Client;

import Client.Controllers.MenuController;
import Client.Models.MenuModel;
import Client.Views.Menu.MenuView;

import javax.swing.*;

public final class Bootstrap
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            var model = new MenuModel();
            var view = new MenuView();
            var controller = new MenuController(model, view);

            controller.initialize();
        });
    }
}
