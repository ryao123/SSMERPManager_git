package cn.erp.pojo;

import java.util.Date;

import lombok.Data;
/**
 * Ԥ��ʵ����
 * @author lindy
 * @����ʱ�� 2020��5��22������9:12:13
 */
@Data
public class Warning {
    private int wid;
    private int sid;
    private int typeId;
    private int warnNum;
    private String remark;//��ע
    private int createBy;
    private Date createTime;
    private int modifyBy;
    private Date modifyTime;
    // �漰������ѯ
    private Type type;
    private Stock stock;
}
