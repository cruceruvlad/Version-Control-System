package utils;

import filesystem.FileSystemSnapshot;

public final class Commit {
    private FileSystemSnapshot snapShot;
    private int commitID;
    private String message;

    /**
     * Commit constructor.
     *
     * @param activeSnapshot the snapshot to be commited
     * @param message        the message of the commit
     */
    public Commit(FileSystemSnapshot activeSnapshot, String message) {
        snapShot = activeSnapshot.cloneFileSystem();
        this.message = message;
        commitID = IDGenerator.generateCommitID();
    }

    public int getID() {
        return commitID;
    }

    public String getMessage() {
        return message;
    }

    public FileSystemSnapshot getSnapshot() {
        return snapShot;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        return commitID == ((Commit) obj).getID();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
