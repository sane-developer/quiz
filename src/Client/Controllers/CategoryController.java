package Client.Controllers;

import Client.Abstractions.Controllers.TimeOrientedController;
import Client.Models.CategoryModel;
import Client.Views.Category.CategoryView;

import java.awt.event.ActionListener;

/**
 ** Represents the mechanism which controls user interactions with the view
 ** and updates the category model accordingly
 **/
public final class CategoryController extends TimeOrientedController<CategoryModel, CategoryView>
{
    /**
     ** The time which player may use to answer the question
     **/
    private final int timeToAnswerQuestion;

    /**
     ** The time which players have to select any of the categories specified in seconds
     **/
    private static final int TIME_TO_SELECT_CATEGORY_IN_SECONDS = 15;

    /**
     ** Creates an instance of CategoryController class with specified data and user interface providers
     ** @param categoryModel The data provider
     ** @param categoryView The user interface provider
     **/
    public CategoryController(int timeToAnswerQuestion, CategoryModel categoryModel, CategoryView categoryView)
    {
        super(TIME_TO_SELECT_CATEGORY_IN_SECONDS, categoryModel, categoryView);

        this.timeToAnswerQuestion = timeToAnswerQuestion;
    }

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected void initializeEventListeners()
    {
        setupViewLifetimeTracker(e -> initializeQuestionView());

        setupRemainingTimeUpdater();

        var buttons = this.view.ui.categoryButtons;

        for (var button : buttons)
        {
            button.addActionListener(this.updateCategory);
        }
    }

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected void disposeEventListeners()
    {
        var buttons = this.view.ui.categoryButtons;

        for (var button : buttons)
        {
            button.removeActionListener(this.updateCategory);
        }

        this.lifetimeTracker.stop();
        this.remainingTimeBarUpdater.stop();
    }

    /**
     ** Initializes the Question view
     **/
    private void initializeQuestionView()
    {
        System.out.println("Moving to question!");
    }

    /**
     ** Allows the players to change the selected category
     **/
    private final ActionListener updateCategory = e ->
    {
        var buttons = this.view.ui.categoryButtons;

        for (var button : buttons)
        {
            var isSelected = button.isSelected();

            if (!isSelected)
            {
                continue;
            }

            var category = button.getText();

            this.model.setSelectedCategory(category);
        }
    };
}
