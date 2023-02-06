package Client.Abstractions.Controllers;

import Client.Abstractions.Views.View;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 ** Represents the functionalities and features for filtering and restricting the text fields.
 ** @param <TModel> The type of data provider
 ** @param <TView> The type of user interface provider
 **/
public abstract class DataOrientedController<TModel, TView extends View> extends Controller<TModel, TView>
{
    /**
     ** Creates an instance of Controller class with specified data and user interface providers
     ** @param model The data provider
     ** @param view The user interface provider
     **/
    public DataOrientedController(TModel model, TView view)
    {
        super(model, view);
    }

    /**
     ** Applies filters to the text fields
     **/
    protected abstract void applyFilters();

    /**
     ** Applies default values for the text fields
     **/
    protected abstract void applyDefaultValues();

    /**
     ** Initializes the event listeners
     **/
    @Override
    protected abstract void initializeEventListeners();

    /**
     ** Disposes the event listeners
     **/
    @Override
    protected abstract void disposeEventListeners();

    /**
     ** Attaches the provided filter to desired text field
     ** @param textField The text field to restrict
     ** @param filter The restriction to be applied
     **/
    protected void setupFilter(JTextField textField, DocumentFilter filter)
    {
        var document = new PlainDocument();

        document.setDocumentFilter(filter);

        textField.setDocument(document);
    }

    /**
     ** Applies the filters and assigns default values to the text fields
     **/
    @Override
    public void initialize()
    {
        super.initialize();

        applyFilters();

        applyDefaultValues();
    }
}
