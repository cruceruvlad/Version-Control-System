package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class VcsInvalidOperation extends VcsOperation {

    /**
     * Invalid vcs operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public VcsInvalidOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the invalid vcs operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
