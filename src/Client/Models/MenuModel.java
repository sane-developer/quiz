package Client.Models;

/**
 ** Represents the necessary data functionalities for Menu view
 **/
public final class MenuModel
{
    /**
     ** The guest status
     **/
    private boolean isGuest;

    /**
     ** The name of the lobby
     **/
    private String lobbyName;

    /**
     ** The name of the player
     **/
    private String playerName;

    /**
     ** Gets the guest status
     ** @return true, if the player is joining lobby; otherwise false
     **/
    public boolean getIsGuest()
    {
        return this.isGuest;
    }

    /**
     ** Sets the guest status
     ** @param isGuest The isGuest status
     **/
    public void setIsGuest(boolean isGuest)
    {
        this.isGuest = isGuest;
    }

    /**
     ** Gets the name of the lobby
     ** @return The name
     **/
    public String getLobbyName()
    {
        return this.lobbyName;
    }

    /**
     ** Sets the name of the lobby
     ** @param lobbyName The name
     **/
    public void setLobbyName(String lobbyName)
    {
        this.lobbyName = lobbyName;
    }

    /**
     ** Gets the name of the player
     ** @return The name
     **/
    public String getPlayerName()
    {
        return this.playerName;
    }

    /**
     ** Sets the name of the player
     ** @param playerName The name
     **/
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
}
