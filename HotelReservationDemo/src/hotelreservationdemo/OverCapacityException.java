
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class OverCapacityException extends Exception {

    /**
     * Creates a new instance of <code>OverCapacityException</code> without
     * detail message.
     */
    public OverCapacityException() {
        super("Too many guest in the party.");
    }

    /**
     * Constructs an instance of <code>OverCapacityException</code> with the
     * specified detail message.
     *
     * 
     * 
     * @param msg the message to display
     */
    public OverCapacityException(String msg) {
        super(msg);
    }
    /**
     * Constructs an instance of <code>OverCapacityException</code> with the
     * specified detail message.
     *
     * @param numParty the number of guests in the party
     * 
     */
    public OverCapacityException(int numParty) {
        super("Too many guest in the party."+numParty+" is given.");
    }
}
