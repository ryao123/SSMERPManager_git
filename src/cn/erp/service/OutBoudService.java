package cn.erp.service;

import java.util.List;

import cn.erp.pojo.OutBoud;

public interface OutBoudService {
	 // ��ѯ���е�����
    List<OutBoud> getOutBoudList();
    
    boolean addOutBoud(OutBoud outBoud);
    
    boolean updateOutBoud(OutBoud outBoud);
    
    boolean delOutBoud(int oid);
    
    OutBoud findById(int oid);
}
