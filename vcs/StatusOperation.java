package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class StatusOperation extends VcsOperation {

    /**
     * Status operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the status operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        vcs.getOutputWriter().write("On branch: " + vcs.getActiveBranch() + "\nStaged changes:\n");
        for (String iterator : vcs.getTracker()) {
            vcs.getOutputWriter().write("\t" + iterator + "\n");
        }
        return ErrorCodeManager.OK;
    }
}
