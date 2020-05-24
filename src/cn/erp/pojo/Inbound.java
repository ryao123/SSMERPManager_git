package cn.erp.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor//�޲ι���
@AllArgsConstructor//�����в�������
public class Inbound {
	private int inId;
	private String productName;//��Ʒ����
	private int typeId;//��Ʒ����
	private String wareNum;//�������
	private String remarks;
	private Date wareDate;//���ʱ��
	private int createBy;//������
	private int modifyBy;//�޸���
	private Date modifyTime;//�޸�ʱ��

	private Type type;
}
