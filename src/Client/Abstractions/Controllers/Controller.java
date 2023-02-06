package Client.Abstractions.Controllers;

import Client.Abstractions.Views.View;
import Client.Communication.Client;

/**
 ** Represents the shared functionalities and features between all controllers components
 ** @param <TModel> The type of data provider
 ** @param <TView> The type of user interface provider
 **/
public abstract class Controller<TModel, TView extends View>
{
    /**
     ** The data provider
     **/
    protected final TView view;

    /**
     ** The user interface provider
     **/
    protected final TModel model;

    /**
     ** The client socket
     **/
    protected static final Client client = new Client("localhost", 8080);

    /**
     ** Creates an instance of Controller class with specified data and user interface providers
     ** @param model The data provider
     ** @param view The user interface provider
     **/
    public Controller(TModel model, TView view)
    {
        this.view = view;
        this.model = model;
    }

    /**
     ** Initializes the event listeners
     **/
    protected abstract void initializeEventListeners();

    /**
     ** Disposes the event listeners
     **/
    protected abstract void disposeEventListeners();

    /**
     ** Dispatches the task in the background
     **/
    protected void dispatchBackgroundTask() {}

    /**
     ** Initializes the view
     **/
    public void initialize()
    {
        this.view.display();

        initializeEventListeners();

        dispatchBackgroundTask();
    }

    /**
     ** Disposes the view
     **/
    public void dispose()
    {
        this.view.dispose();

        disposeEventListeners();
    }
}
