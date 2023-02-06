package Client.Views.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 ** Provides all the necessary user interface components for the question view
 **/
public final class QuestionViewUI
{
    /**
     ** The label which displays current question
     **/
    public final JLabel questionLabel;

    /**
     ** The list of possible answers
     **/
    public final List<JRadioButton> answerButtons;

    /**
     ** Creates an instance of QuestionViewUI class and sets the question and answer buttons
     **/
    public QuestionViewUI(String question, List<String> answers)
    {
        this.questionLabel = new JLabel(question);

        this.questionLabel.setHorizontalAlignment(JLabel.CENTER);

        this.answerButtons = new ArrayList<>();

        for (var answer : answers)
        {
            var answerButton = new JRadioButton(answer);

            answerButton.setHorizontalAlignment(JRadioButton.CENTER);

            this.answerButtons.add(answerButton);
        }
    }
}
