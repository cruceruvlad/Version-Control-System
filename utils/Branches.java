package utils;

import java.util.*;
import filesystem.FileSystemSnapshot;

public final class Branches extends LinkedHashMap<String, LinkedList<Commit>> {
    public Branches() {
        super();
    }

    /**
     * Verify if there is a specific branch.
     *
     * @param branchName the branch name
     * @return true/false
     */
    public boolean isBranch(String branchName) {
        for (Map.Entry<String, LinkedList<Commit>> iterator : entrySet()) {
            if (iterator.getKey().equals(branchName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add new commit on a branch.
     *
     * @param branchName the branch name
     * @param commit     the commit
     */
    public void add(String branchName, Commit commit) {
        LinkedList<Commit> branch = get(branchName);

        if (branch == null) {
            branch = new LinkedList<>();
        }

        branch.add(commit);
        put(branchName, branch);
    }

    /**
     * Remove commits from a branch starting from a given commit.
     *
     * @param branchName the branch name
     * @param commit     the commit
     * @return return code
     */
    public void remove(String branchName, Commit commit) {
        for (Map.Entry<String, LinkedList<Commit>> iterator : entrySet()) {
            if (iterator.getKey().equals(branchName)) {
                int index = iterator.getValue().indexOf(commit);
                int size = iterator.getValue().size();
                iterator.getValue().subList(index + 1, size).clear();
            }
        }
    }
}
