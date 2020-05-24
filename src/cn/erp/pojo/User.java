package cn.erp.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor//�޲ι���
@AllArgsConstructor//�����в�������
public class User {
	private int uid;
	private String uname;
	private String userName;
	private String userpassword;
	private int gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")//json��ʽ���ڵ�ע��
	private Date birthday;
	private String phone;
	private String place;//����
	private String email;
	private String Introg;//��ע
	private int  userrole;//�û���ɫ
	private int createBy;
	private Date createTime;
	private int modifyBy;
	private Date modifyTime;
	private int age;
	public int getAge() {
		age=new Date().getYear()-birthday.getYear();
		return age;
	}
}
