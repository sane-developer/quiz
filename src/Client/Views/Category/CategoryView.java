package Client.Views.Category;

import Client.Abstractions.Views.TimeOrientedView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 ** Represents the view frame which allows the players to
 ** select category for next set of questions
 **/
public final class CategoryView extends TimeOrientedView
{
    /**
     ** The category selection user interface components
     **/
    public final CategoryViewUI ui;

    /**
     ** Creates an instance of CategoryView class with specified frame properties
     ** @param categories The names of the categories
     **/
    public CategoryView(List<String> categories)
    {
        super(325, 225, "Select category");

        this.ui = new CategoryViewUI(categories);
    }

    /**
     ** Sets up the layout of the UI components
     **/
    @Override
    protected void setupLayout()
    {
        var container = this.getContentPane();

        var rowsForStaticUI = 2;

        var rowsForDynamicUI = this.ui.categoryButtons.size();

        var rows = rowsForStaticUI + rowsForDynamicUI;

        var layout = new GridLayout(rows, 1);

        container.setLayout(layout);

        container.add(this.ui.instructionsLabel);

        var remainingTimeBar = this.getRemainingTimeBar();

        container.add(remainingTimeBar);

        var group = new ButtonGroup();

        for (var categoryButton : this.ui.categoryButtons)
        {
            group.add(categoryButton);

            container.add(categoryButton);
        }
    }
}
