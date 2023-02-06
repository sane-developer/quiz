package Client.Communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
}
