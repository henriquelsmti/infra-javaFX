package br.com.datarey.frame;

import br.com.generic.dao.SearchEntityListBuilder;

public enum Predicate{
    NOT_EQUAL {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.notEqual((String) arg0[0], arg0[1]);
        }
    },

    GREATER_THAN_OR_EQUAL_TO {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.greaterThanOrEqualTo((String) arg0[0], (Comparable<?>) arg0[1]); 
        }
    },

    LESS_THAN_OR_EQUAL_TO {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.lessThanOrEqualTo((String) arg0[0], (Comparable<?>) arg0[1]);
            
        }
    },

    IN {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.in((String) arg0[0]);
        }
    },

    GREATER_THAN {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.greaterThan((String) arg0[0], (Comparable<?>) arg0[1]);
        }
    },

    LESS_THAN {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.lessThan((String) arg0[0], (Comparable<?>) arg0[1]);
        }
    },

    LIKE {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.like((String) arg0[0], (String) arg0[1]);
            
        }
    },

    NOT_LIKE {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.notLike((String) arg0[0], (String) arg0[1]);
            
        }
    },

    EQUAL {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.eq((String) arg0[0], arg0[1]);
            
        }
    },

    BETWEEN {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.between((String)arg0[0], (Comparable<?>)arg0[1], (Comparable<?>)arg0[2]);
            
        }
    },

    IS_MEMBER {
        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.isMember((String) arg0[0], arg0[1]);
            
        }
    },

    IS_NOT_MEMBER {


        @Override
        public <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0) {
            return builder.isNotMember((String) arg0[0], arg0[1]);
        }

        

    };

    public abstract <T> SearchEntityListBuilder<T> addPredicate(SearchEntityListBuilder<T> builder, Object... arg0);

}
