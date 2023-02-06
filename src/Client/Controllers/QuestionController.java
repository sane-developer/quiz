package Client.Controllers;

import Client.Abstractions.Controllers.TimeOrientedController;
import Client.Models.LeaderboardModel;
import Client.Models.QuestionModel;
import Client.Views.Leaderboard.LeaderboardView;
import Client.Views.Question.QuestionView;

import java.awt.event.ActionListener;

/**
 ** Represents the mechanism which controls user interactions with the view
 ** and updates the question model accordingly
 **/
public final class QuestionController extends TimeOrientedController<QuestionModel, QuestionView>
{
    /**
     ** Creates an instance of QuestionController class with specified data and user interface providers
     ** @param lifeTimeInSeconds The lifetime of this view
     ** @param questionModel The data provider
     ** @param questionView The user interface provider
     **/
    public QuestionController(int lifeTimeInSeconds, QuestionModel questionModel, QuestionView questionView)
    {
        super(lifeTimeInSeconds, questionModel, questionView);
    }

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected void initializeEventListeners()
    {
        setupViewLifetimeTracker(e -> initializeLeaderboardView());

        setupRemainingTimeUpdater();

        var buttons = this.view.ui.answerButtons;

        for (var button : buttons)
        {
            button.addActionListener(this.updateAnswer);
        }
    }

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected void disposeEventListeners()
    {
        var buttons = this.view.ui.answerButtons;

        for (var button : buttons)
        {
            button.removeActionListener(this.updateAnswer);
        }

        this.lifetimeTracker.stop();
        this.remainingTimeBarUpdater.stop();
    }

    /**
     ** Initializes the leaderboard view
     **/
    private void initializeLeaderboardView()
    {
        var selectedAnswer = this.model.getSelectedAnswer();

        if (selectedAnswer != null)
        {
            client.sendRequestToServer(selectedAnswer);
        }

        var playerScoresDetails = client.getPlayerScoresDetails();

        var playerNames = playerScoresDetails.get(0);
        var playerScores = playerScoresDetails.get(1);

        var leaderboardModel = new LeaderboardModel();
        var leaderboardView = new LeaderboardView(playerNames, playerScores);
        var leaderboardController = new LeaderboardController(5, leaderboardModel, leaderboardView);

        leaderboardController.initialize();
    }

    /**
     ** Allows the players to change the selected category
     **/
    private final ActionListener updateAnswer = e ->
    {
        var buttons = this.view.ui.answerButtons;

        for (var button : buttons)
        {
            var isSelected = button.isSelected();

            if (isSelected)
            {
                var answer = button.getText();

                this.model.setSelectedAnswer(answer);
            }
        }
    };
}
