package com.shi.main.provider.mapper;

import com.shi.api.model.RedRobRecord;
import com.shi.api.model.RedRobRecordExample;
import com.shi.api.model.RedRobRecordExample.Criteria;
import com.shi.api.model.RedRobRecordExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class RedRobRecordSqlProvider {

    public String countByExample(RedRobRecordExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("red_rob_record");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(RedRobRecordExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("red_rob_record");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(RedRobRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("red_rob_record");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }

        if (record.getRedPacket() != null) {
            sql.VALUES("red_packet", "#{redPacket,jdbcType=VARCHAR}");
        }

        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }

        if (record.getRobTime() != null) {
            sql.VALUES("rob_time", "#{robTime,jdbcType=TIMESTAMP}");
        }

        if (record.getIsActive() != null) {
            sql.VALUES("is_active", "#{isActive,jdbcType=TINYINT}");
        }

        return sql.toString();
    }

    public String selectByExample(RedRobRecordExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("user_id");
        sql.SELECT("red_packet");
        sql.SELECT("amount");
        sql.SELECT("rob_time");
        sql.SELECT("is_active");
        sql.FROM("red_rob_record");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RedRobRecord record = (RedRobRecord) parameter.get("record");
        RedRobRecordExample example = (RedRobRecordExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("red_rob_record");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=INTEGER}");
        }

        if (record.getRedPacket() != null) {
            sql.SET("red_packet = #{record.redPacket,jdbcType=VARCHAR}");
        }

        if (record.getAmount() != null) {
            sql.SET("amount = #{record.amount,jdbcType=DECIMAL}");
        }

        if (record.getRobTime() != null) {
            sql.SET("rob_time = #{record.robTime,jdbcType=TIMESTAMP}");
        }

        if (record.getIsActive() != null) {
            sql.SET("is_active = #{record.isActive,jdbcType=TINYINT}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("red_rob_record");

        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("user_id = #{record.userId,jdbcType=INTEGER}");
        sql.SET("red_packet = #{record.redPacket,jdbcType=VARCHAR}");
        sql.SET("amount = #{record.amount,jdbcType=DECIMAL}");
        sql.SET("rob_time = #{record.robTime,jdbcType=TIMESTAMP}");
        sql.SET("is_active = #{record.isActive,jdbcType=TINYINT}");

        RedRobRecordExample example = (RedRobRecordExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RedRobRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("red_rob_record");

        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }

        if (record.getRedPacket() != null) {
            sql.SET("red_packet = #{redPacket,jdbcType=VARCHAR}");
        }

        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DECIMAL}");
        }

        if (record.getRobTime() != null) {
            sql.SET("rob_time = #{robTime,jdbcType=TIMESTAMP}");
        }

        if (record.getIsActive() != null) {
            sql.SET("is_active = #{isActive,jdbcType=TINYINT}");
        }

        sql.WHERE("id = #{id,jdbcType=INTEGER}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, RedRobRecordExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
