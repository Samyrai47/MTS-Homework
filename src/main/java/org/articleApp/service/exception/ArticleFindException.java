package org.articleApp.service.exception;

public class ArticleFindException extends RuntimeException {
    public ArticleFindException(String message) {
        super(message);
    }

    public ArticleFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
