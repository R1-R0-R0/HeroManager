package exceptions;

import model.job.JobType;

/**
 * Exception for methods who cannot handle specific job type
 */
public class UnsupportedJobTypeException extends Exception {

    /**
     * @param unsupportedJobType unsupported job type
     */
    public UnsupportedJobTypeException(JobType unsupportedJobType) {
        super("Unsupported job type " + unsupportedJobType.name());
    }

    /**
     * @param err string error
     */
    public UnsupportedJobTypeException(String err) {
        super(err);
    }
}
