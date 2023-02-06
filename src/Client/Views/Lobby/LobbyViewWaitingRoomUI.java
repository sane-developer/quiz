package Client.Views.Lobby;

import javax.swing.*;

/**
 ** Provides all the necessary user interface components for the players in the lobby
 **/
public final class LobbyViewWaitingRoomUI
{
    /**
     ** The label which displays the lobby name for the players
     **/
    public final JLabel lobbyNameLabel = new JLabel();

    /**
     ** The button which allows the lobby owner to start the game
     **/
    public final JButton startGameButton = new JButton("Start game");

    /**
     ** The button which allows the lobby owner to start the game
     **/
    public final JButton switchToSettingsButton = new JButton("Go to settings");

    /**
     ** Creates an instance of LobbyViewWaitingRoomUI class and sets the lobby name label appropriately
     ** @param lobbyName The name of the lobby
     **/
    public LobbyViewWaitingRoomUI(String lobbyName)
    {
        this.lobbyNameLabel.setText("Lobby " + lobbyName);
        this.lobbyNameLabel.setHorizontalAlignment(JLabel.CENTER);
    }
}
