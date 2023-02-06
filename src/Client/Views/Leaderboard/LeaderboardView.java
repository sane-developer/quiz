package Client.Views.Leaderboard;

import Client.Abstractions.Views.TimeOrientedView;

import java.awt.*;
import java.util.List;

public final class LeaderboardView extends TimeOrientedView
{
    public final LeaderboardViewUI ui;

    public LeaderboardView(List<String> playerNames, List<String> playerScores)
    {
        super(300, 250, "Scoreboard");

        this.ui = new LeaderboardViewUI(playerNames, playerScores);
    }

    @Override
    protected void setupLayout()
    {
        var rows = this.ui.playerNameLabels.size();
        var layout = new GridLayout(rows, 2);
        var container = this.getContentPane();

        container.setLayout(layout);

        for (var i = 0; i < rows; i++)
        {
            var playerNameLabel = this.ui.playerNameLabels.get(i);
            var playerScoreLabel = this.ui.playerScoreLabels.get(i);

            container.add(playerNameLabel);
            container.add(playerScoreLabel);
        }
    }
}
