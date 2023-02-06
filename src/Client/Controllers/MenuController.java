package Client.Controllers;

import Client.Abstractions.Controllers.DataOrientedController;
import Client.Filters.TextLengthFilter;
import Client.Flags.MenuResponseFlags;
import Client.Models.LobbyModel;
import Client.Models.MenuModel;
import Client.Views.Lobby.LobbyView;
import Client.Views.Menu.MenuView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.BiConsumer;

/**
 ** Represents the mechanism which controls user interactions with the view
 ** and updates the menu model accordingly
 **/
public final class MenuController extends DataOrientedController<MenuModel, MenuView>
{
    /**
     ** Creates an instance of MenuController class with specified data and user interface providers
     ** @param menuModel The data provider
     ** @param menuView The user interface provider
     **/
    public MenuController(MenuModel menuModel, MenuView menuView)
    {
        super(menuModel, menuView);
    }

    /**
     ** Applies filters to the text fields
     **/
    @Override
    protected void applyFilters()
    {
        var filter = new TextLengthFilter(this.view.ui.MAXIMUM_INPUT_LENGTH);

        setupFilter(this.view.ui.lobbyNameEntry, filter);
        setupFilter(this.view.ui.playerNameEntry, filter);
    }

    /**
     ** Applies default values for the text fields
     **/
    @Override
    protected void applyDefaultValues()
    {
        this.view.ui.lobbyNameEntry.setText("");
        this.view.ui.playerNameEntry.setText("");
    }

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected void initializeEventListeners()
    {
        this.view.ui.lobbyNameEntry.addFocusListener(this.lobbyNameUpdater);
        this.view.ui.playerNameEntry.addFocusListener(this.playerNameUpdater);
        this.view.ui.joinLobbyButton.addActionListener(this.joinLobby);
        this.view.ui.createLobbyButton.addActionListener(this.createLobby);
    }

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected void disposeEventListeners()
    {
        this.view.ui.lobbyNameEntry.removeFocusListener(this.lobbyNameUpdater);
        this.view.ui.playerNameEntry.removeFocusListener(this.playerNameUpdater);
        this.view.ui.joinLobbyButton.removeActionListener(this.joinLobby);
        this.view.ui.createLobbyButton.removeActionListener(this.createLobby);
    }

    /**
     ** Initializes the lobby view
     **/
    private void initializeLobbyView(boolean isGuest)
    {
        this.dispose();

        var playerNames = client.retrievePlayerNamesResponse();
        var lobbyName = this.model.getLobbyName();

        var lobbyModel = new LobbyModel();
        var lobbyView = new LobbyView(lobbyName, playerNames, isGuest);
        var lobbyController = new LobbyController(lobbyModel, lobbyView);

        lobbyController.initialize();
    }

    /**
     ** Defines the lobby nam text field handler
     **/
    private final FocusListener lobbyNameUpdater = getFocusListener(MenuModel::setLobbyName);

    /**
     ** Defines the player name text field handler
     **/
    private final FocusListener playerNameUpdater = getFocusListener(MenuModel::setPlayerName);

    /**
     ** Defines the join lobby mechanism that will be run when the appropriate button will be triggered
     **/
    private final ActionListener joinLobby = e ->
    {
        var playerName = this.model.getPlayerName();

        if (playerName.equals(""))
        {
            this.view.ui.playerNameEntry.setText("Cannot be empty");
            return;
        }

        var lobbyName = this.model.getLobbyName();

        if (lobbyName.equals(""))
        {
            this.view.ui.lobbyNameEntry.setText("Cannot be empty");
            return;
        }

        var response = client.retrieveLobbyInitResponse("JOIN", playerName, lobbyName);

        if (response == MenuResponseFlags.LOBBY_NOT_FOUND)
        {
            this.view.ui.lobbyNameEntry.setText("Invalid lobby");
        }
        else if (response == MenuResponseFlags.LOBBY_ALREADY_FULL)
        {
            this.view.ui.lobbyNameEntry.setText("Lobby is full");
        }
        else if (response == MenuResponseFlags.LOBBY_ALREADY_CONTAINS_USERNAME)
        {
            this.view.ui.playerNameEntry.setText("Set unique name");
        }
        else if (response == MenuResponseFlags.OPERATION_DONE)
        {
            this.initializeLobbyView(true);
        }
    };

    /**
     ** Declares the create lobby mechanism that will be run
     ** when the appropriate button will be triggered
     **/
    private final ActionListener createLobby = e ->
    {
        var playerName = this.model.getPlayerName();

        if (playerName.equals(""))
        {
            this.view.ui.playerNameEntry.setText("Cannot be empty");
            return;
        }

        var lobbyName = this.model.getLobbyName();

        if (lobbyName.equals(""))
        {
            this.view.ui.lobbyNameEntry.setText("Cannot be empty");
            return;
        }

        var response = client.retrieveLobbyInitResponse("CREATE", playerName, lobbyName);

        if (response == MenuResponseFlags.LOBBY_COULD_NOT_BE_CREATED)
        {
            this.view.ui.playerNameEntry.setText("Serverside error");
            this.view.ui.lobbyNameEntry.setText("Serverside error");
        }
        else if (response == MenuResponseFlags.LOBBY_WITH_SUCH_NAME_ALREADY_EXISTS)
        {
            this.view.ui.lobbyNameEntry.setText("Such lobby already exists..");
        }
        else if (response == MenuResponseFlags.OPERATION_DONE)
        {
            this.initializeLobbyView(false);
        }
    };

    /**
     ** Gets the focus listener which clears the buffer on focus gain
     ** and sets the appropriate model data on focus lost
     **/
    private FocusListener getFocusListener(BiConsumer<MenuModel, String> setter)
    {
        return new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                var source = (JTextField) e.getSource();

                source.setText("");
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                var source = (JTextField) e.getSource();
                var content = source.getText();

                setter.accept(model, content);
            }
        };
    }
}
