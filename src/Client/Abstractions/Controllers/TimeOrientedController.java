package Client.Abstractions.Controllers;

import Client.Abstractions.Views.TimeOrientedView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 ** Represents the functionalities and features for time restricted controllers
 ** @param <TModel> The type of data provider
 ** @param <TView> The type of user interface provider
 **/
public abstract class TimeOrientedController<TModel, TView extends TimeOrientedView> extends Controller<TModel, TView>
{
    /**
     ** The timer which tracks the view's lifetime
     **/
    protected Timer lifetimeTracker;

    /**
     ** The timer which updates the remaining time bar
     **/
    protected Timer remainingTimeBarUpdater;

    /**
     ** The controller's lifetime specified in milliseconds
     **/
    private final int lifeTimeInMilliseconds;

    /**
     ** The second represented in milliseconds
     **/
    private final static int secondInMilliseconds = 1000;

    /**
     ** Creates an instance of TimeOrientedController class with specified data and user interface providers
     ** @param model The data provider
     ** @param view The user interface provider
     **/
    public TimeOrientedController(int lifeTimeInSeconds, TModel model, TView view)
    {
        super(model, view);
        this.view.getRemainingTimeBar().setMinimum(0);
        this.view.getRemainingTimeBar().setMaximum(lifeTimeInSeconds);
        this.lifeTimeInMilliseconds = lifeTimeInSeconds * secondInMilliseconds;
    }

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected abstract void initializeEventListeners();

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected abstract void disposeEventListeners();

    /**
     ** Updates the remaining time progress bar periodically
     **/
    private final ActionListener updateRemainingTimeBar = e ->
    {
        var remainingTimeBar = this.view.getRemainingTimeBar();
        var remainingTime = remainingTimeBar.getValue();

        remainingTimeBar.setValue(remainingTime + 1);
    };

    /**
     ** Sets up the countdown for the controller to dispose the view
     **/
    protected void setupViewLifetimeTracker(ActionListener listener)
    {
        this.lifetimeTracker = new Timer(this.lifeTimeInMilliseconds, listener);
        this.lifetimeTracker.setRepeats(false);
        this.lifetimeTracker.start();
    }

    /**
     ** Updates the remaining time bar progress
     **/
    protected void setupRemainingTimeUpdater()
    {
        this.remainingTimeBarUpdater = new Timer(secondInMilliseconds, this.updateRemainingTimeBar);
        this.remainingTimeBarUpdater.setRepeats(true);
        this.remainingTimeBarUpdater.start();
    }
}
