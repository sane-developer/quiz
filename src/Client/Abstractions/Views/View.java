package Client.Abstractions.Views;

import javax.swing.*;
import java.awt.*;

/**
 ** Represents the shared functionalities and features between all view components
 **/
public abstract class View extends JFrame
{
    /**
     ** The title of the frame
     **/
    private final String title;

    /**
     ** The dimensions of the frame
     **/
    private final Dimension dimension;

    /**
     ** Creates an instance of View class with specified frame properties
     ** @param width The width of the frame
     ** @param height The height of the frame
     ** @param title The title of the frame
     **/
    public View(int width, int height, String title)
    {
        this.title = title;
        this.dimension = new Dimension(width, height);
    }

    /**
     ** Sets up the layout of the UI components
     **/
    protected abstract void setupLayout();

    /**
     ** Displays the frame
     **/
    public void display()
    {
        this.setupLayout();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
