
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class NoVacancyException extends Exception {

    /**
     * Creates a new instance of <code>NoVacancyException</code> without detail
     * message.
     */
    public NoVacancyException() {
        super("No rooms are available at the hotel.");
    }

    /**
     * Constructs an instance of <code>NoVacancyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoVacancyException(String msg) {
        super("\n"+msg);
    }
}
