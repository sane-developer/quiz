package Client.Models;

/**
 ** Represents the necessary data functionalities for Menu view
 **/
public final class LobbyModel
{
    /**
     ** The amount of rounds that will be conducted in the game
     **/
    int rounds;

    /**
     ** The amount of questions that will be conducted per round
     **/
    int questionsPerRound;

    /**
     ** The amount of time for each answer provided in seconds
     **/
    int timeForAnswerInSeconds;

    /**
     ** The amount of categories that players will be able to choose from
     **/
    int categories;

    /**
     ** Sets the amount of rounds that will be conducted
     ** @param rounds The amount of rounds to conduct
     **/
    public void setRounds(int rounds)
    {
        this.rounds = rounds;
    }

    /**
     ** Sets the amount of questions per round
     ** @param questionsPerRound The amount of questions in each round
     **/
    public void setQuestionsPerRound(int questionsPerRound)
    {
        this.questionsPerRound = questionsPerRound;
    }

    /**
     ** Gets the time to answer specified in seconds
     ** @return The time
     **/
    public int getTimeForAnswerInSeconds()
    {
        return this.timeForAnswerInSeconds;
    }

    /**
     ** Sets the time that each player will have to submit an answer
     ** @param timeForAnswerInSeconds The time to answer the question in seconds
     **/
    public void setTimeForAnswerInSeconds(int timeForAnswerInSeconds)
    {
        this.timeForAnswerInSeconds = timeForAnswerInSeconds;
    }

    /**
     ** Sets the amount of categories that players will be able to choose from
     ** @param categories The amount of categories to choose from
     **/
    public void setCategoriesPerRound(int categories)
    {
        this.categories = categories;
    }
}
