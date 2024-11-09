package org.articleApp.ID;

public class CommentID {
    private static long ID;

    public CommentID (long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "" + ID;
    }
}
