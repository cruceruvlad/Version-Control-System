package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class CommitOperation extends VcsOperation {

    /**
     * Commit operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the commit operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        if (vcs.getTracker().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        operationArgs.remove(0);
        String message = new String();
        for (String iterator : operationArgs) {
            message = message + " " + iterator;
        }
        vcs.addCommit(message.substring(1));
        return ErrorCodeManager.OK;
    }
}
