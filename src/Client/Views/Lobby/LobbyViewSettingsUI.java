package Client.Views.Lobby;

import javax.swing.*;

/**
 ** Provides all the necessary user interface components for the lobby settings
 **/
public final class LobbyViewSettingsUI
{
    /**
     ** The maximum length that text fields can have
     **/
    public final int MAXIMUM_INPUT_LENGTH = 2;

    /**
     ** The label which provides descriptor for roundsPerGame entry
     **/
    public final JLabel roundsPerGameLabel = new JLabel("Rounds");

    /**
     ** The entry which allows the lobby owner to set amount of rounds in the game
     **/
    public final JTextField roundsInGameEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The label which provides descriptor for categoriesPerRound entry
     **/
    public final JLabel categoriesPerRoundLabel = new JLabel("Categories per round");;

    /**
     ** The entry which allows the lobby owner to set amount of categories per round
     **/
    public final JTextField categoriesPerRoundEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The label which provides descriptor for questionsPerCategory entry
     **/
    public final JLabel questionsPerCategoryLabel = new JLabel("Questions per category");

    /**
     ** The entry which allows the lobby owner to set amount of questions per category
     **/
    public final JTextField questionsPerCategoryEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The label which provides descriptor for timeForAnswer entry
     **/
    public final JLabel timeForAnswerInSecondsLabel = new JLabel("Time for answer");

    /**
     ** The entry which allows the lobby owner to set the maximum time player may take to answer the question
     **/
    public final JTextField timeForAnswerInSecondsEntry = new JTextField(MAXIMUM_INPUT_LENGTH);

    /**
     ** The button that allows the lobby owner to switch back to lobby room card
     **/
    public final JButton switchToWaitingRoomButton = new JButton("Go to Lobby");

    /**
     ** Creates an instance of LobbyViewSettingsUI class and sets the elements positioning
     **/
    public LobbyViewSettingsUI()
    {
        this.roundsPerGameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.roundsInGameEntry.setHorizontalAlignment(JTextField.CENTER);
        this.categoriesPerRoundLabel.setHorizontalAlignment(JLabel.CENTER);
        this.categoriesPerRoundEntry.setHorizontalAlignment(JTextField.CENTER);
        this.questionsPerCategoryLabel.setHorizontalAlignment(JLabel.CENTER);
        this.questionsPerCategoryEntry.setHorizontalAlignment(JTextField.CENTER);
        this.timeForAnswerInSecondsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.timeForAnswerInSecondsEntry.setHorizontalAlignment(JTextField.CENTER);
    }
}
