package Client.Views.Question;

import Client.Abstractions.Views.TimeOrientedView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 ** Represents the view frame which allows the players to
 ** select answers to the posed question
 **/
public final class QuestionView extends TimeOrientedView
{
    /**
     ** The question view user interface components
     **/
    public final QuestionViewUI ui;

    /**
     ** Creates an instance of TimeOrientedView class with specified frame properties
     ** @param question The currently asked question
     ** @param answers The possible answers
     **/
    public QuestionView(String question, List<String> answers)
    {
        super(300, 250, "Answer the question");

        this.ui = new QuestionViewUI(question, answers);
    }

    /**
     ** Sets up the layout of the UI components
     **/
    @Override
    protected void setupLayout()
    {
        var rowsForQuestionAndTimeBar = 2;

        var rowsForAnswers = this.ui.answerButtons.size();

        var layout = new GridLayout(rowsForAnswers + rowsForQuestionAndTimeBar, 1);

        var container = this.getContentPane();

        container.setLayout(layout);

        container.add(this.ui.questionLabel);

        var remainingTimeBar = this.getRemainingTimeBar();

        container.add(remainingTimeBar);

        var group = new ButtonGroup();

        for (var answerButton : this.ui.answerButtons)
        {
            group.add(answerButton);

            container.add(answerButton);
        }
    }
}
