package vcs;

import java.util.ArrayList;
import java.util.LinkedList;

import utils.Commit;
import utils.ErrorCodeManager;
import utils.OperationType;

public class RollbackOperation extends VcsOperation {

    /**
     * Rollback operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the rollback operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        LinkedList<Commit> commits = vcs.getBranchCommits(vcs.getActiveBranch());
        vcs.setActiveSnapShot(commits.get(commits.size() - 1).getSnapshot());
        return ErrorCodeManager.OK;
    }
}
