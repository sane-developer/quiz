package Client.Models;

/**
 ** Represents the necessary data functionalities for Question view
 **/
public final class QuestionModel
{
    /**
     ** The selected answer by the player
     **/
    private String selectedAnswer = null;


    /**
     ** Gets the player's selected answer
     ** @return The answer
     **/
    public String getSelectedAnswer()
    {
        return selectedAnswer;
    }

    /**
     ** Sets the player's answer
     ** @param selectedAnswer The answer
     **/
    public void setSelectedAnswer(String selectedAnswer)
    {
        this.selectedAnswer = selectedAnswer;
    }
}
