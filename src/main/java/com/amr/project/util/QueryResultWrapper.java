package com.amr.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;

public class QueryResultWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryResultWrapper.class);

    public static <T> T wrapGetSingleResult(TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            return null;
        }
    }
}

