package Client.Views.Lobby;

import Client.Abstractions.Views.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 ** Represents the lobby view frame which depending on the lobby ownership
 ** allows the owner to start the game and to configure the game properties.
 ** Also allows all the players to view who is currently in the lobby.
 **/
public final class LobbyView extends View
{
    /**
     ** The guest status
     **/
    private final boolean isGuest;

    /**
     ** The names of the players
     **/
    private final List<String> playerNames;

    /**
     ** The change-aware list which holds the names of the players
     **/
    public final DefaultListModel<String> playerNamesList;

    /**
     ** The lobby settings user interface components
     **/
    public final LobbyViewSettingsUI settingsUI;

    /**
     ** The lobby waiting room user interface components
     **/
    public final LobbyViewWaitingRoomUI waitingRoomUI;

    /**
     ** Creates an instance of View class with specified frame properties
     **/
    public LobbyView(String lobbyName, List<String> playerNames, boolean isGuest)
    {
        super(335, 225, "Await the game start");

        this.isGuest = isGuest;
        this.playerNames = playerNames;
        this.playerNamesList = new DefaultListModel<>();
        this.settingsUI = new LobbyViewSettingsUI();
        this.waitingRoomUI = new LobbyViewWaitingRoomUI(lobbyName);
    }

    /**
     ** Sets up the layout of the UI components
     **/
    @Override
    protected void setupLayout()
    {
        setupPlayerNamesList();

        var container = this.getContentPane();

        var layout = new CardLayout();

        var cards = new JPanel(layout);

        var waitingRoomCard = this.getWaitingRoomCard();

        cards.add("waiting_room", waitingRoomCard);

        if (!this.isGuest)
        {
            var settingsCard = this.getSettingsCard();

            cards.add("settings", settingsCard);

            this.settingsUI
                .switchToWaitingRoomButton
                .addActionListener(
                    e -> layout.show(cards, "waiting_room"));

            this.waitingRoomUI
                .switchToSettingsButton
                .addActionListener(
                    e -> layout.show(cards, "settings"));
        }

        container.setLayout(layout);

        container.add(cards);
    }

    /**
     ** Updates the list containing the player names
     **/
    private void setupPlayerNamesList()
    {
        for (var playerName : this.playerNames)
        {
            this.playerNamesList.addElement(playerName);
        }
    }

    /**
     ** Gets the settings UI components composed into a card
     ** @return The card
     **/
    private JPanel getSettingsCard()
    {
        var card = new JPanel();

        var layout = new GridLayout(5, 2);

        card.setLayout(layout);

        card.add(this.settingsUI.roundsPerGameLabel);

        card.add(this.settingsUI.roundsInGameEntry);

        card.add(this.settingsUI.categoriesPerRoundLabel);

        card.add(this.settingsUI.categoriesPerRoundEntry);

        card.add(this.settingsUI.questionsPerCategoryLabel);

        card.add(this.settingsUI.questionsPerCategoryEntry);

        card.add(this.settingsUI.timeForAnswerInSecondsLabel);

        card.add(this.settingsUI.timeForAnswerInSecondsEntry);

        card.add(this.settingsUI.switchToWaitingRoomButton);

        return card;
    }

    /**
     ** Gets the waiting room UI components composed into a card
     ** @return The card
     **/
    private JPanel getWaitingRoomCard()
    {
        var card = new JPanel();

        var layout = new GridLayout(4, 1);

        card.setLayout(layout);

        card.add(this.waitingRoomUI.lobbyNameLabel);

        var list = new JList<>(this.playerNamesList);

        card.add(list);

        if (!this.isGuest)
        {
            card.add(this.waitingRoomUI.switchToSettingsButton);

            card.add(this.waitingRoomUI.startGameButton);
        }

        return card;
    }
}
