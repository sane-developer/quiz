package Client.Views.Menu;

import javax.swing.*;

/**
 ** Provides all the necessary user interface components
 **/
public final class MenuViewInterface
{
    /**
     ** Defines the maximum length of text fields input
     **/
    public final int MAXIMUM_INPUT_LENGTH = 15;

    /**
     ** The label which tells the player to declare his name
     **/
    public final JLabel playerNameLabel = new JLabel("Set your name");

    /**
     ** The entry that allows the player to declare his name
     **/
    public final JTextField playerNameEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The label which tells the player to declare the lobby name
     **/
    public final JLabel lobbyNameLabel = new JLabel("Set room name");

    /**
     ** The entry that allows the player to declare the lobby name
     **/
    public final JTextField lobbyNameEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The button that allows user to join an existing lobby
     **/
    public final JButton joinLobbyButton = new JButton("Join lobby");

    /**
     ** The button that allows user to create a new lobby
     **/
    public final JButton createLobbyButton = new JButton("Create lobby");

    /**
     ** Creates an instance of MenuViewInterface class and sets the elements positioning
     **/
    public MenuViewInterface()
    {
        lobbyNameLabel.setHorizontalAlignment(JLabel.CENTER);
        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        lobbyNameEntry.setHorizontalAlignment(JTextField.CENTER);
        playerNameEntry.setHorizontalAlignment(JTextField.CENTER);
    }
}
