package Client.Views.Category;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 ** Provides all the necessary user interface components for selecting the category
 **/
public final class CategoryViewUI
{
    /**
     ** The label with instructions for the player
     **/
    public final JLabel instructionsLabel = new JLabel("Select category");

    /**
     ** The collection of buttons which allow the players to choose next category
     **/
    public final List<JRadioButton> categoryButtons;

    /**
     ** Creates an instance of LobbyViewSettingsUI class and radio button for each category
     ** @param categories The names of the categories
     **/
    public CategoryViewUI(List<String> categories)
    {
        this.categoryButtons = new ArrayList<>();

        for (var category : categories)
        {
            var categoryButton = new JRadioButton(category);

            categoryButton.setHorizontalAlignment(JRadioButton.CENTER);

            this.categoryButtons.add(categoryButton);
        }

        this.instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
    }
}
