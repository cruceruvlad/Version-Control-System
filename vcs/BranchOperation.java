package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class BranchOperation extends VcsOperation {

    /**
     * Branch operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the branch operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        if (!vcs.addBranch(operationArgs.get(0))) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        return ErrorCodeManager.OK;
    }
}
