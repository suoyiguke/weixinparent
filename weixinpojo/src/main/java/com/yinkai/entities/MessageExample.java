package com.yinkai.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Integer value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Integer value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Integer value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Integer value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Integer> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Integer> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andFromidIsNull() {
            addCriterion("fromId is null");
            return (Criteria) this;
        }

        public Criteria andFromidIsNotNull() {
            addCriterion("fromId is not null");
            return (Criteria) this;
        }

        public Criteria andFromidEqualTo(Integer value) {
            addCriterion("fromId =", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotEqualTo(Integer value) {
            addCriterion("fromId <>", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidGreaterThan(Integer value) {
            addCriterion("fromId >", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fromId >=", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidLessThan(Integer value) {
            addCriterion("fromId <", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidLessThanOrEqualTo(Integer value) {
            addCriterion("fromId <=", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidIn(List<Integer> values) {
            addCriterion("fromId in", values, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotIn(List<Integer> values) {
            addCriterion("fromId not in", values, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidBetween(Integer value1, Integer value2) {
            addCriterion("fromId between", value1, value2, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotBetween(Integer value1, Integer value2) {
            addCriterion("fromId not between", value1, value2, "fromid");
            return (Criteria) this;
        }

        public Criteria andToidIsNull() {
            addCriterion("toId is null");
            return (Criteria) this;
        }

        public Criteria andToidIsNotNull() {
            addCriterion("toId is not null");
            return (Criteria) this;
        }

        public Criteria andToidEqualTo(Integer value) {
            addCriterion("toId =", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidNotEqualTo(Integer value) {
            addCriterion("toId <>", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidGreaterThan(Integer value) {
            addCriterion("toId >", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidGreaterThanOrEqualTo(Integer value) {
            addCriterion("toId >=", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidLessThan(Integer value) {
            addCriterion("toId <", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidLessThanOrEqualTo(Integer value) {
            addCriterion("toId <=", value, "toid");
            return (Criteria) this;
        }

        public Criteria andToidIn(List<Integer> values) {
            addCriterion("toId in", values, "toid");
            return (Criteria) this;
        }

        public Criteria andToidNotIn(List<Integer> values) {
            addCriterion("toId not in", values, "toid");
            return (Criteria) this;
        }

        public Criteria andToidBetween(Integer value1, Integer value2) {
            addCriterion("toId between", value1, value2, "toid");
            return (Criteria) this;
        }

        public Criteria andToidNotBetween(Integer value1, Integer value2) {
            addCriterion("toId not between", value1, value2, "toid");
            return (Criteria) this;
        }

        public Criteria andMessagedateIsNull() {
            addCriterion("messageDate is null");
            return (Criteria) this;
        }

        public Criteria andMessagedateIsNotNull() {
            addCriterion("messageDate is not null");
            return (Criteria) this;
        }

        public Criteria andMessagedateEqualTo(Timestamp value) {
            addCriterion("messageDate =", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateNotEqualTo(Timestamp value) {
            addCriterion("messageDate <>", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateGreaterThan(Timestamp value) {
            addCriterion("messageDate >", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("messageDate >=", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateLessThan(Timestamp value) {
            addCriterion("messageDate <", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateLessThanOrEqualTo(Timestamp value) {
            addCriterion("messageDate <=", value, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateIn(List<Timestamp> values) {
            addCriterion("messageDate in", values, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateNotIn(List<Timestamp> values) {
            addCriterion("messageDate not in", values, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("messageDate between", value1, value2, "messagedate");
            return (Criteria) this;
        }

        public Criteria andMessagedateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("messageDate not between", value1, value2, "messagedate");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}