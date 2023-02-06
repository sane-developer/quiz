package Client.Views.Menu;

import Client.Abstractions.Views.View;

import java.awt.*;

/**
 ** Represents the menu view frame which allows the player to set his name and lobby name
 **/
public final class MenuView extends View
{
    /**
     ** The menu settings user interface components
     **/
    public final MenuViewUI ui = new MenuViewUI();

    /**
     ** Creates an instance of MenuViewFrame class with specified frame properties
     **/
    public MenuView()
    {
        super(225, 150, "Welcome to Quizhub");
    }

    /**
     ** Sets up the layout of the UI components
     **/
    @Override
    protected void setupLayout()
    {
        var layout = new GridLayout(3, 2);

        var container = this.getContentPane();

        container.setLayout(layout);

        container.add(this.ui.playerNameLabel);

        container.add(this.ui.playerNameEntry);

        container.add(this.ui.lobbyNameLabel);

        container.add(this.ui.lobbyNameEntry);

        container.add(this.ui.joinLobbyButton);

        container.add(this.ui.createLobbyButton);
    }
}
