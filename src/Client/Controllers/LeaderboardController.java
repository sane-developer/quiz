package Client.Controllers;

import Client.Abstractions.Controllers.TimeOrientedController;
import Client.Models.LeaderboardModel;
import Client.Views.Leaderboard.LeaderboardView;

public final class LeaderboardController extends TimeOrientedController<LeaderboardModel, LeaderboardView>
{
    public LeaderboardController(int lifeTimeInSeconds, LeaderboardModel leaderboardModel, LeaderboardView view)
    {
        super(lifeTimeInSeconds, leaderboardModel, view);
    }

    @Override
    protected void initializeEventListeners()
    {
        setupViewLifetimeTracker(e -> initializeNextView());
        setupRemainingTimeUpdater();
    }

    @Override
    protected void disposeEventListeners()
    {
        this.lifetimeTracker.stop();
        this.remainingTimeBarUpdater.stop();
    }

    private void initializeNextView()
    {

    }
}
