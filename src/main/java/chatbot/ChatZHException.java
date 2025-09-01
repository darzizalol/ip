package chatbot;

/**
 * ChatZHException is the exception class for ChatZH.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ChatZHException extends Exception {

    /**
     * Constructor for ChatZHException
     *
     * @param message the message to be displayed
     */
    public ChatZHException(String message) {
        super(message);
    }
}
