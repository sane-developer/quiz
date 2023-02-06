package Client.Filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

/**
 ** Represents digit only filter for the text fields
 **/
public final class DigitOnlyFilter extends TextLengthFilter
{
    /**
     ** The pattern which matches only numerical input
     **/
    private final Pattern digitOnlyPattern;

    /**
     ** Creates an instance of DigitOnlyFilter class with maximum input length
     ** @param maximumInputLength The maximum length of the input that can be inserted
     **/
    public DigitOnlyFilter(int maximumInputLength)
    {
        super(maximumInputLength);

        this.digitOnlyPattern = Pattern.compile("\\d+");
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attrs) throws BadLocationException
    {
        var isDigit = digitOnlyPattern.matcher(string).matches();

        if (isDigit)
        {
            super.insertString(fb, offset, string, attrs);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
    {
        var isEmpty = text.equals("");

        if (isEmpty)
        {
            super.replace(fb, offset, length, "", attrs);

            return;
        }

        var isNumber = digitOnlyPattern.matcher(text).matches();

        if (isNumber)
        {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
