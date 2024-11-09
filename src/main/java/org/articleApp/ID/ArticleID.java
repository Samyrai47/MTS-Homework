package org.articleApp.ID;

public class ArticleID {
    private static long ID;

    public ArticleID(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "" + ID;
    }
}
