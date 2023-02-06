package Client.Communication;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 ** Represents the client socket which is responsible for communication
 ** with the server socket in order to perform transitions between views
 **/
public final class Client
{
    /**
     ** The character stream reader
     **/
    public final BufferedReader in;

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
     **
     **/
    public String retrieveStringFromServer()
    {
        var response = "";

        try
        {
            response = this.in.readLine();

            while (response == null)
            {
                response = this.in.readLine();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return response;
    }

    /**
     ** Retrieves the integer output from the server
     **/
    public int retrieveIntegerFromServer()
    {
        var response = this.retrieveStringFromServer();

        return Integer.parseInt(response);
    }

    /**
     ** Retrieves the integer output from the server
     **/
    public List<String> retrieveStringArrayFromServer()
    {
        var data = new ArrayList<String>();

        var response = retrieveStringFromServer();

        var json = new JSONObject(response);

        var iterator = json.keys();

        while (iterator.hasNext())
        {
            var id = iterator.next();

            var string = json.getString(id);

            data.add(string);
        }

        return data;
    }

    /**
     ** Sends message to the server
     ** @param message The message to be sent
     **/
    public void sendRequestToServer(String message)
    {
        try
        {
            this.out.writeBytes(message);
            this.out.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     ** Sends the request to get the menu response flags
     ** @param method The lobby handle method
     ** @param playerName The name of the player
     ** @param lobbyName The name of the lobby
     **/
    public void sendLobbyInitRequest(String method, String playerName, String lobbyName)
    {
        var message = String.join(" ", method, playerName, lobbyName, "\n");

        sendRequestToServer(message);
    }

    /**
     ** Sends the request to get the time to answer for all the instances
     ** @param rounds The amount of rounds in the game
     ** @param categories The amount of categories per round
     ** @param questions The amount of questions per questions
     ** @param time The amount of time to answer
     **/
    public void sendTimeToAnswerInSecondsRequest(int rounds, int categories, int questions, int time)
    {
        var json = new JSONObject();

        json.put("number_of_rounds", rounds);
        json.put("categories_per_round", categories);
        json.put("questions_per_category", questions);
        json.put("time_for_answer", time);

        var formattedJsonString = json.toString() + '\n';

        sendRequestToServer(formattedJsonString);
    }

    public List<String> getQuestionDetails()
    {
        var response = retrieveStringFromServer();

        var json = new JSONObject(response);

        var data = new ArrayList<String>();

        var question = json.getString("question");

        var answersIterator = json.getJSONArray("answers").iterator();

        data.add(question);

        while (answersIterator.hasNext())
        {
            var answer = (String) answersIterator.next();

            data.add(answer);
        }

        return data;
    }

    public ArrayList<ArrayList<String>> getPlayerScoresDetails()
    {
        var response = retrieveStringFromServer();
        var json = new JSONObject(response);
        var playerNamesIterator = json.keys();

        var data = new ArrayList<ArrayList<String>>();
        var playerNames = new ArrayList<String>();
        var playerScores = new ArrayList<String>();

        while (playerNamesIterator.hasNext())
        {
            var playerName = playerNamesIterator.next();
            var playerScore = "" + json.getInt(playerName);

            playerNames.add(playerName);
            playerScores.add(playerScore);
        }

        data.add(playerNames);
        data.add(playerScores);

        return data;
    }
}
