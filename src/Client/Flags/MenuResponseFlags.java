package Client.Flags;

/**
 ** Represents the possible server responses for calls from the MenuController
 **/
public final class MenuResponseFlags
{
    /**
     ** Indicates that the user has successfully joined or created the room
     **/
    public static final int OPERATION_DONE = 1;

    /**
     **  Indicates that the room with such name has not been found
     **/
    public static final int LOBBY_NOT_FOUND = 2;

    /**
     **  Indicates that the room, which user is attempting to join, cannot have any more players
     **/
    public static final int LOBBY_ALREADY_FULL = 4;

    /**
     **  Indicates that serverside error has occurred during room creation (fatal)
     **/
    public static final int LOBBY_COULD_NOT_BE_CREATED = 8;

    /**
     **  Indicates that the user's chosen name is not unique within the room
     **/
    public static final int LOBBY_ALREADY_CONTAINS_USERNAME = 16;

    /**
     **  Indicates that user has chosen room that has already been taken
     **/
    public static final int LOBBY_WITH_SUCH_NAME_ALREADY_EXISTS = 32;
}
