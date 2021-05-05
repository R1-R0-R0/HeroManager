package exceptions;

import model.job.JobType;

public class UnsupportedJobTypeException extends Exception {

    public UnsupportedJobTypeException(JobType unsupportedJobType) {
        super("Unsupported job type " + unsupportedJobType.name());
    }

    public UnsupportedJobTypeException(String err) {
        super(err);
    }
}
