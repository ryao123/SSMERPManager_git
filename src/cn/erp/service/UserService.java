package cn.erp.service;
/**
 * �û�����ҵ��ӿ�
 * @author lindy
 * @����ʱ�� 2020��5��12������10:43:25
 */

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.erp.pojo.User;

public interface UserService {
    User loginValider(String userName,String password);
    
    List<User> getUserList();
    
    boolean updatePassword(User user);
  //����
    boolean addUser(User user);
  	
  	//�޸�
  	boolean updateUser(User user);
  	
  	boolean deleteUser(int uid);
  	
  	//����id��ѯ
  	User findById(int uid);
  	
    
    
}
