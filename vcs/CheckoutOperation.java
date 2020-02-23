package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class CheckoutOperation extends VcsOperation {

    /**
     * Checkout operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the checkout operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        if (!vcs.isTrackerEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        if (operationArgs.size() == 1) {
            if (!vcs.setBranch(operationArgs.get(0))) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        if (operationArgs.size() == 2) {
            if (!vcs.setCommit(Integer.valueOf(operationArgs.get(1)))) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
        }
        return ErrorCodeManager.OK;
    }
}
