package Client.Views.Menu;

import javax.swing.*;

/**
 ** Provides all the necessary user interface components for the Menu view
 **/
public final class MenuViewUI
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
     ** Creates an instance of MenuViewUI class and sets the elements positioning
     **/
    public MenuViewUI()
    {
        this.lobbyNameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.lobbyNameEntry.setHorizontalAlignment(JTextField.CENTER);
        this.playerNameEntry.setHorizontalAlignment(JTextField.CENTER);
    }
}
