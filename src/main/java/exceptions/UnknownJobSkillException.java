package exceptions;

/**
 * Exception when a job skill name isn't recognized
 */
public class UnknownJobSkillException extends Exception {

    /**
     * @param err string error
     */
    public UnknownJobSkillException(String err) {
        super(err);
    }
}
