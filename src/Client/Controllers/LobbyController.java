package Client.Controllers;

import Client.Abstractions.Controllers.DataOrientedController;
import Client.Filters.DigitOnlyFilter;
import Client.Models.CategoryModel;
import Client.Models.LobbyModel;
import Client.Views.Category.CategoryView;
import Client.Views.Lobby.LobbyView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

/**
 ** Represents the mechanism which controls user interactions with the view
 ** and updates the lobby model accordingly
 **/
public final class LobbyController extends DataOrientedController<LobbyModel, LobbyView>
{
    /**
     ** Creates an instance of LobbyController class with specified data and user interface providers
     ** @param lobbyModel The data provider
     ** @param lobbyView The user interface provider
     **/
    public LobbyController(LobbyModel lobbyModel, LobbyView lobbyView)
    {
        super(lobbyModel, lobbyView);
    }

    /**
     ** Applies filters to the text fields
     **/
    @Override
    protected void applyFilters()
    {
        var filter = new DigitOnlyFilter(2);

        setupFilter(this.view.settingsUI.roundsInGameEntry, filter);
        setupFilter(this.view.settingsUI.categoriesPerRoundEntry, filter);
        setupFilter(this.view.settingsUI.questionsPerCategoryEntry, filter);
        setupFilter(this.view.settingsUI.timeForAnswerInSecondsEntry, filter);
    }

    /**
     ** Applies default values for the text fields
     **/
    @Override
    protected void applyDefaultValues()
    {
        this.view.settingsUI.roundsInGameEntry.setText("3");
        this.view.settingsUI.categoriesPerRoundEntry.setText("2");
        this.view.settingsUI.questionsPerCategoryEntry.setText("5");
        this.view.settingsUI.timeForAnswerInSecondsEntry.setText("15");
    }

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected void initializeEventListeners()
    {
        this.view.settingsUI.roundsInGameEntry.addFocusListener(this.roundsRangeAdjuster);
        this.view.settingsUI.categoriesPerRoundEntry.addFocusListener(this.categoriesPerRoundRangeAdjuster);
        this.view.settingsUI.questionsPerCategoryEntry.addFocusListener(this.questionsPerCategoryRangeAdjuster);
        this.view.settingsUI.timeForAnswerInSecondsEntry.addFocusListener(this.timeForAnswerRangeAdjuster);
        this.view.waitingRoomUI.startGameButton.addActionListener(this.startGame);
    }

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected void disposeEventListeners()
    {
        this.view.settingsUI.roundsInGameEntry.removeFocusListener(this.roundsRangeAdjuster);
        this.view.settingsUI.categoriesPerRoundEntry.removeFocusListener(this.categoriesPerRoundRangeAdjuster);
        this.view.settingsUI.questionsPerCategoryEntry.removeFocusListener(this.questionsPerCategoryRangeAdjuster);
        this.view.settingsUI.timeForAnswerInSecondsEntry.removeFocusListener(this.timeForAnswerRangeAdjuster);
        this.view.waitingRoomUI.startGameButton.removeActionListener(this.startGame);
    }

    /**
     ** Initializes the category view
     **/
    private void initializeCategoryView()
    {
        this.dispose();

        var timeToAnswer = client.retrieveTimeToAnswerInSeconds(
            this.model.getRounds(),
            this.model.getCategoriesPerRound(),
            this.model.getQuestionsPerCategory(),
            this.model.getTimeForAnswerInSeconds()
        );

        // TODO: GET CATEGORIES FROM THE SERVER
        var categories = new ArrayList<String>();

        var categoryModel = new CategoryModel();
        var categoryView = new CategoryView(categories);
        var categoryController = new CategoryController(timeToAnswer, categoryModel, categoryView);

        categoryController.initialize();
    }

    /**
     ** The focus event handler which adjusts roundsInGame text field input to respect its boundaries
     **/
    private final FocusListener roundsRangeAdjuster = createRangeInputAdjuster(3, 10);

    /**
     ** The focus event handler which adjusts categoriesPerRound text field input to respect its boundaries
     **/
    private final FocusListener categoriesPerRoundRangeAdjuster = createRangeInputAdjuster(2, 10);

    /**
     ** The focus event handler which adjusts questionsPerCategory text field input to respect its boundaries
     **/
    private final FocusListener questionsPerCategoryRangeAdjuster = createRangeInputAdjuster(5, 10);

    /**
     ** The focus event handler which adjusts timeForAnswer text field input to respect its boundaries
     **/
    private final FocusListener timeForAnswerRangeAdjuster = createRangeInputAdjuster(15, 35);

    /**
     ** The click event handler which sends the game settings to the server and starts the game
     **/
    private final ActionListener startGame = e ->
    {
        var input = this.view.settingsUI.roundsInGameEntry.getText();

        var rounds = Integer.parseInt(input);

        this.model.setRounds(rounds);

        input = this.view.settingsUI.categoriesPerRoundEntry.getText();

        var categoriesPerRound = Integer.parseInt(input);

        this.model.setCategoriesPerRound(categoriesPerRound);

        input = this.view.settingsUI.questionsPerCategoryEntry.getText();

        var questionsPerCategory = Integer.parseInt(input);

        this.model.setQuestionsPerRound(questionsPerCategory);

        input = this.view.settingsUI.timeForAnswerInSecondsEntry.getText();

        var timeForAnswer = Integer.parseInt(input);

        this.model.setTimeForAnswerInSeconds(timeForAnswer);

        this.initializeCategoryView();
    };

    /**
     ** Creates new focus listener that assures the entered value will not exceed the provided range
     ** @param minimum The minimum value of the range
     ** @param maximum The maximum value of the range
     ** @return The focus listener
     **/
    private FocusListener createRangeInputAdjuster(int minimum, int maximum)
    {
        return new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                var source = (JTextField) e.getSource();

                source.setText("");
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                var source = (JTextField) e.getSource();

                var text = source.getText();

                if (text.equals(""))
                {
                    return;
                }

                var number = Integer.parseInt(text);

                if (number < minimum)
                {
                    source.setText(String.valueOf(minimum));
                }
                else if (number > maximum)
                {
                    source.setText(String.valueOf(maximum));
                }
            }
        };
    }
}
