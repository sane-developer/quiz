package Client.Communication;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 ** Represents the client socket which is responsible for communication
 ** with the server socket in order to perform transitions between views
 **/
public final class Client
{
    /**
     ** The character stream reader
     **/
    private final BufferedReader in;

    /**
     ** The data output stream
     **/
    private final DataOutputStream out;

    /**
     ** Creates an instance of Client class which allows the application
     ** to communicate with the server in order to perform view transitions
     ** @param host The hostname
     ** @param port The port
     **/
    public Client(String host, int port)
    {
        try
        {
            var client = new Socket(host, port);
            var in = client.getInputStream();
            var out = client.getOutputStream();
            var inReader = new InputStreamReader(in);

            this.in = new BufferedReader(inReader);
            this.out = new DataOutputStream(out);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     ** Retrieves the menu response flag from the server
     ** @return The menu response flag
     **/
    public int retrieveLobbyInitResponse(String method, String playerName, String lobbyName)
    {
        var message = String.join(" ", method, playerName, lobbyName, "\n");

        try
        {
            this.out.writeBytes(message);

            this.out.flush();

            var response = this.in.readLine();

            while (response == null)
            {
                response = this.in.readLine();
            }

            return Integer.parseInt(response);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     ** Retrieves the list of strings from the server
     ** @return The list of strings
     **/
    private List<String> retrieveStringArray()
    {
        var data = new ArrayList<String>();

        try
        {
            var response = this.in.readLine();

            while (response == null)
            {
                response = this.in.readLine();
            }

            var json = new JSONObject(response);

            var iterator = json.keys();

            while (iterator.hasNext())
            {
                var id = iterator.next();

                var string = json.getString(id);

                data.add(string);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return data;
    }

    /**
     ** Retrieves the names of the players in the lobby
     ** @return The list of player names
     **/
    public List<String> retrievePlayerNamesResponse()
    {
        return retrieveStringArray();
    }

    /**
     ** Retrieves the time to answer the question specified in seconds
     ** @param rounds The amount of rounds in the game
     ** @param categories The amount of categories per round
     ** @param questions The amount of questions per questions
     ** @param time The amount of time to answer
     ** @return The time to answer in seconds
     **/
    public int retrieveTimeToAnswerInSeconds(int rounds, int categories, int questions, int time)
    {
        var json = new JSONObject();

        json.put("number_of_rounds", rounds);
        json.put("categories_per_round", categories);
        json.put("questions_per_category", questions);
        json.put("time_for_answer", time);

        var formattedJsonString = json.toString();

        try
        {
            this.out.writeBytes(formattedJsonString + "\n");

            this.out.flush();

            var response = this.in.readLine();

            while (response == null)
            {
                response = this.in.readLine();
            }

            return Integer.parseInt(response);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     ** Retrieves the set of categories for next round
     ** @return The set of category names
     **/
    public List<String> retrieveCategoriesResponse()
    {
        return retrieveStringArray();
    }
}
