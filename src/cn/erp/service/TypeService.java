package cn.erp.service;

import java.util.List;

import cn.erp.pojo.Type;

public interface TypeService {
    // ��ѯ���еķ�����Ϣ����
    List<Type> getTypeList();
    // ���ӷ�����Ϣ�ķ���
    boolean addType(Type type);
    // �޸ķ�����Ϣ�ķ���
    boolean updateType(Type type);
    // ����id��ѯ������Ϣ�ķ���
    Type findById(int id);
    // ɾ��������Ϣ�ķ���
    boolean deleteType(int id);
}
