package vcs;

import java.util.*;
import utils.Commit;
import utils.OperationType;

public class LogOperation extends VcsOperation {

    /**
     * Log operation constructor.
     *
     * @param type          type of the operation
     * @param operationArgs the arguments of the operation
     */
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the log operation.
     *
     * @param vcs the vcs
     * @return return code
     */
    @Override
    public int execute(Vcs vcs) {
        LinkedList<Commit> commits = vcs.getBranchCommits(vcs.getActiveBranch());
        int i = -1;
        for (Commit iterator : commits) {
            i++;
            vcs.getOutputWriter().write("Commit id: " + iterator.getID() + "\n" + "Message: "
                                                            + iterator.getMessage() + "\n");
            if (i != commits.size() - 1) {
                vcs.getOutputWriter().write("\n");
            }
        }
        return 0;
    }
}
