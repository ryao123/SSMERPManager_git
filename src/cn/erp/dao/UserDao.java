package cn.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.erp.pojo.Inbound;
import cn.erp.pojo.User;

/**
 * �������ݿ���Ľӿ�
 * @author lindy
 * @����ʱ�� 2020��5��12������10:37:01
 */
@Repository
public interface UserDao {
    // ��¼��֤ username,password
    // ����ֵ�̶������ǾͿ���ʹ��@Paramע����ʵ�ִ���
    User loginValider(@Param("username") String userName,
        @Param("userpassword") String password);
    
    List<User> getAll();
    
  //�޸�����
  		@Update("update erp_user set userpassword=#{userpassword}"
  				+ "where uid=#{uid}")
  		int updatePassword(User user);
    
  //��ʾ����
  	List<User> getUserList();
  	
  	
  	//����
  	@Insert("INSERT INTO erp_user (uname,userName,userpassword,gender,birthday,phone,place,email,Introg,userrole,createBy,createTime)" +
  			"values(#{uname},#{userName},#{userpassword},#{gender},#{birthday},#{phone},"
  			+ "#{place},#{email},#{Introg},#{userrole},#{createBy},#{createTime})")
  	int addUser(User user);
  	
  	//�޸�
  	@Update("update erp_user set uname=#{uname},userName=#{userName},userpassword=#{userpassword},gender=#{gender},"
  			+ "birthday=#{birthday},phone=#{phone},place=#{place},email=#{email},Introg=#{Introg},"
  			+ "userrole=#{userrole},createBy=#{createBy},createTime=#{createTime}")
  	int updateUser(User user);
  	
  	//ɾ��
  	@Delete("delete from erp_user where uid=#{uid}")
  	int deleteUser(@Param("uid") int uid);
  	
  	//����id��ѯ
  	@Select("select * from erp_user where uid=#{uid}")
  	User findById(@Param("uid") int uid);
  	
    
    
    
    
    
    
}