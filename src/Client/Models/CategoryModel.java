package Client.Models;

/**
 ** Represents the necessary data functionalities for Category view
 **/
public final class CategoryModel
{
    /**
     ** The category which has been chosen
     **/
    private String selectedCategory = null;

    /**
     ** Gets the selected category
     ** @return The category
     **/
    public String getSelectedCategory()
    {
        return this.selectedCategory;
    }

    /**
     ** Sets the selected category to the provided one
     ** @param category The new category name
     **/
    public void setSelectedCategory(String category)
    {
        this.selectedCategory = category;
    }
}
