package Client.Filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 ** Represents the text length limit filter for the text fields
 **/
public class TextLengthFilter extends DocumentFilter
{
    /**
     ** The maximum input length that text field can accept
     **/
    private final int maximumInputLength;

    /**
     ** Creates an instance of TextLengthFilter class with specified maximum input length
     ** @param maximumInputLength The maximum input length
     **/
    public TextLengthFilter(int maximumInputLength)
    {
        this.maximumInputLength = maximumInputLength;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        var currentLength = fb.getDocument().getLength();

        var lengthOfInsertion = string.length();

        if (currentLength + lengthOfInsertion <= maximumInputLength)
        {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
    {
        var currentLength = fb.getDocument().getLength();

        var lengthOfInsertion = text.length();

        if (currentLength + lengthOfInsertion - length <= maximumInputLength)
        {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
