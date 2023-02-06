package Client.Abstractions.Views;

import javax.swing.*;

/**
 ** Represents the view with extended time oriented functionalities
 **/
public abstract class TimeOrientedView extends View
{
    /**
     ** The remaining time progress bar
     **/
    private final JProgressBar remainingTimeBar;

    /**
     ** Creates an instance of TimeOrientedView class with specified frame properties
     ** @param width The width of the frame
     ** @param height The height of the frame
     ** @param title The title of the frame
     **/
    public TimeOrientedView(int width, int height, String title)
    {
        super(width, height, title);

        this.remainingTimeBar = new JProgressBar();
    }

    /**
     ** Sets up the layout of the UI components
     **/
    @Override
    protected abstract void setupLayout();

    /**
     ** Gets the remaining time progress bar
     ** @return The progress bar
     **/
    public JProgressBar getRemainingTimeBar()
    {
        return this.remainingTimeBar;
    }
}
