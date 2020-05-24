package cn.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.erp.pojo.Type;

@Repository
public interface TypeDao {
    // ��ѯ���еķ�����Ϣ����
    List<Type> getTypeList();
    // ���ӷ�����Ϣ�ķ���
    int addType(Type type);
    // �޸ķ�����Ϣ�ķ���
    @Update("update erp_type set typeName=#{typeName},typeDescribe=#{typeDescribe},"
        + "typeNotes=#{typeNotes},modifyBy=#{modifyBy},modifyTime=#{modifyTime}"
        + " where tid=#{tid}")
    int updateType(Type type);
    // ����id��ѯ������Ϣ�ķ���
    Type findById(@Param("tid") int id);
    // ɾ��������Ϣ�ķ���
    int deleteType(@Param("tid") int id);
}
