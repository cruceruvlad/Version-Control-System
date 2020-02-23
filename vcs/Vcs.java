package vcs;

import java.util.*;
import utils.Commit;
import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;
import filesystem.Directory;
import utils.Branches;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private Branches branches;
    private String activeBranch;
    private ArrayList<String> tracker;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        tracker = new ArrayList<String>();
        activeBranch = new String("master");
        branches = new Branches();
        branches.add(activeBranch, new Commit(activeSnapshot, "First commit"));
        // TODO other initialisations
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        int result = vcsOperation.execute(this);
        return result;
    }

    /**
     * Gets the output writer.
     *
     * @return output writer
     */
    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    /**
     * Adds a tracked operation.
     *
     * @param operationString the operation added
     * @return return code
     */
    public void addTrackedOperation(final String operationString) {
        tracker.add(operationString);
    }

    /**
     * Get the tracker list.
     *
     * @return tracker list
     */
    public ArrayList<String> getTracker() {
        return tracker;
    }

    /**
     * Get current directory.
     *
     * @return current directory
     */
    public Directory getCurrentDir() {
        return activeSnapshot.getCurrentDir();
    }

    /**
     * Returns emptyness of tracker list.
     *
     * @return true/false
     */
    public boolean isTrackerEmpty() {
        return tracker.isEmpty();
    }

    /**
     * Adds new branch.
     *
     * @param branchName the branch name
     * @return operation success/fail
     */
    public boolean addBranch(String branchName) {
        if (branches.isBranch(branchName)) {
            return false;
        }
        LinkedList<Commit> commits = getBranchCommits(activeBranch);
        branches.add(branchName, commits.get(commits.size() - 1));
        return true;
    }

    /**
     * Adds new commit.
     *
     * @param message the commit message
     */
    public void addCommit(String message) {
        branches.add(activeBranch, new Commit(activeSnapshot, message));
        tracker.clear();
    }

    /**
     * Sets the active snapshot.
     *
     * @param snapShot the snapshot
     */
    public void setActiveSnapShot(FileSystemSnapshot snapShot) {
        this.activeSnapshot = snapShot.cloneFileSystem();
        tracker.clear();
    }

    /**
     * Gets the active branch name.
     *
     * @param vcsOperation the vcs operation
     * @return active branch name
     */
    public String getActiveBranch() {
        return activeBranch;
    }

    /**
     * Get branch commits of a given branch.
     *
     * @param branchName the branch name
     * @return list of commits
     */
    public LinkedList<Commit> getBranchCommits(String branchName) {
        for (Map.Entry<String, LinkedList<Commit>> branch : branches.entrySet()) {
            if (branch.getKey().equals(branchName)) {
                return branch.getValue();
            }
        }
        return null;
    }

    /**
     * Set the active branch.
     *
     * @param branchName the branch name
     * @return operation success/fail
     */
    public boolean setBranch(String branchName) {
        if (!branches.isBranch(branchName)) {
            return false;
        }
        activeBranch = branchName;
        LinkedList<Commit> commits = getBranchCommits(activeBranch);
        setActiveSnapShot(commits.get(commits.size() - 1).getSnapshot());
        return true;
    }

    /**
     * Sets the active snapshot from commit.
     *
     * @param commitID the commit id
     * @return operation success/fail
     */
    public boolean setCommit(int commitID) {
        LinkedList<Commit> commits = getBranchCommits(activeBranch);
        for (Commit iterator : commits) {
            if (iterator.getID() == commitID) {
                setActiveSnapShot(iterator.getSnapshot());
                branches.remove(activeBranch, iterator);
                return true;
            }
        }
        return false;
    }
    // TODO methods through which vcs operations interact with this

}
