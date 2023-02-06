package Client.Views.Leaderboard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class LeaderboardViewUI
{
    public List<JLabel> playerNameLabels = new ArrayList<>();

    public List<JLabel> playerScoreLabels = new ArrayList<>();

    public LeaderboardViewUI(List<String> playerNames, List<String> playerScores)
    {
        for (var playerName : playerNames)
        {
            var playerNameLabel = new JLabel(playerName);

            this.playerNameLabels.add(playerNameLabel);
        }

        for (var playerScore : playerScores)
        {
            var playerScoreLabel = new JLabel(playerScore);

            this.playerScoreLabels.add(playerScoreLabel);
        }
    }
}
