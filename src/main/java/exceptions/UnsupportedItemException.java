package exceptions;

import model.job.JobType;

public class UnsupportedItemException extends Exception {

    public UnsupportedItemException(JobType unsupportedJobType) {
        super("Unsupported job type " + unsupportedJobType.name());
    }

    public UnsupportedItemException(String err) {
        super(err);
    }
}
