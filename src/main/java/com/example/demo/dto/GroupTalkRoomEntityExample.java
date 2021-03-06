package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupTalkRoomEntityExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public GroupTalkRoomEntityExample() {
		oredCriteria = new ArrayList<>();
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
			criteria = new ArrayList<>();
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

		public Criteria andTalkRoomIdIsNull() {
			addCriterion("talk_room_id is null");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdIsNotNull() {
			addCriterion("talk_room_id is not null");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdEqualTo(Integer value) {
			addCriterion("talk_room_id =", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdNotEqualTo(Integer value) {
			addCriterion("talk_room_id <>", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdGreaterThan(Integer value) {
			addCriterion("talk_room_id >", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("talk_room_id >=", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdLessThan(Integer value) {
			addCriterion("talk_room_id <", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdLessThanOrEqualTo(Integer value) {
			addCriterion("talk_room_id <=", value, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdIn(List<Integer> values) {
			addCriterion("talk_room_id in", values, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdNotIn(List<Integer> values) {
			addCriterion("talk_room_id not in", values, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdBetween(Integer value1, Integer value2) {
			addCriterion("talk_room_id between", value1, value2, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andTalkRoomIdNotBetween(Integer value1, Integer value2) {
			addCriterion("talk_room_id not between", value1, value2, "talkRoomId");
			return (Criteria) this;
		}

		public Criteria andGroupNameIsNull() {
			addCriterion("group_name is null");
			return (Criteria) this;
		}

		public Criteria andGroupNameIsNotNull() {
			addCriterion("group_name is not null");
			return (Criteria) this;
		}

		public Criteria andGroupNameEqualTo(String value) {
			addCriterion("group_name =", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameNotEqualTo(String value) {
			addCriterion("group_name <>", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameGreaterThan(String value) {
			addCriterion("group_name >", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
			addCriterion("group_name >=", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameLessThan(String value) {
			addCriterion("group_name <", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameLessThanOrEqualTo(String value) {
			addCriterion("group_name <=", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameLike(String value) {
			addCriterion("group_name like", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameNotLike(String value) {
			addCriterion("group_name not like", value, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameIn(List<String> values) {
			addCriterion("group_name in", values, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameNotIn(List<String> values) {
			addCriterion("group_name not in", values, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameBetween(String value1, String value2) {
			addCriterion("group_name between", value1, value2, "groupName");
			return (Criteria) this;
		}

		public Criteria andGroupNameNotBetween(String value1, String value2) {
			addCriterion("group_name not between", value1, value2, "groupName");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexIsNull() {
			addCriterion("last_talk_index is null");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexIsNotNull() {
			addCriterion("last_talk_index is not null");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexEqualTo(Integer value) {
			addCriterion("last_talk_index =", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexNotEqualTo(Integer value) {
			addCriterion("last_talk_index <>", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexGreaterThan(Integer value) {
			addCriterion("last_talk_index >", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexGreaterThanOrEqualTo(Integer value) {
			addCriterion("last_talk_index >=", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexLessThan(Integer value) {
			addCriterion("last_talk_index <", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexLessThanOrEqualTo(Integer value) {
			addCriterion("last_talk_index <=", value, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexIn(List<Integer> values) {
			addCriterion("last_talk_index in", values, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexNotIn(List<Integer> values) {
			addCriterion("last_talk_index not in", values, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexBetween(Integer value1, Integer value2) {
			addCriterion("last_talk_index between", value1, value2, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andLastTalkIndexNotBetween(Integer value1, Integer value2) {
			addCriterion("last_talk_index not between", value1, value2, "lastTalkIndex");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIsNull() {
			addCriterion("is_enabled is null");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIsNotNull() {
			addCriterion("is_enabled is not null");
			return (Criteria) this;
		}

		public Criteria andIsEnabledEqualTo(Boolean value) {
			addCriterion("is_enabled =", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotEqualTo(Boolean value) {
			addCriterion("is_enabled <>", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledGreaterThan(Boolean value) {
			addCriterion("is_enabled >", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_enabled >=", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledLessThan(Boolean value) {
			addCriterion("is_enabled <", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledLessThanOrEqualTo(Boolean value) {
			addCriterion("is_enabled <=", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIn(List<Boolean> values) {
			addCriterion("is_enabled in", values, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotIn(List<Boolean> values) {
			addCriterion("is_enabled not in", values, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledBetween(Boolean value1, Boolean value2) {
			addCriterion("is_enabled between", value1, value2, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_enabled not between", value1, value2, "isEnabled");
			return (Criteria) this;
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

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}