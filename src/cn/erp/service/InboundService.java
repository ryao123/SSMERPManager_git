package cn.erp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.pojo.Inbound;
import cn.erp.pojo.Stock;

public interface InboundService {


	//��ʾ����
		List<Inbound> getInboundList();
		
		//����
		boolean addInbound(Inbound inbound);
		
		//�޸�
		boolean updateInbound(Inbound inbound);
		
		//ɾ��
		boolean deleteInbound(int inId);
		
		//����id��ѯ
		Inbound findById(int inId);
}
