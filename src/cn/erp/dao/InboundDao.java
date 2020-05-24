package cn.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.erp.pojo.Inbound;
import cn.erp.pojo.Stock;

@Repository
public interface InboundDao {

	//��ʾ����
	List<Inbound> getInboundList();
	
	//����
	@Insert("insert into erp_inbound(productName,typeId,wareNum,remarks,wareDate,createBy)"
			+"values(#{productName},#{typeId},#{wareNum},#{remarks},#{wareDate},#{createBy})")
	int addInbound(Inbound inbound);
	
	//�޸�
	@Update("update erp_inbound set productName=#{productName},typeId=#{typeId},wareNum=#{wareNum},"
	        + "remarks=#{remarks},modifyBy=#{modifyBy},modifyTime=#{modifyTime} where inId=#{inId}")
	int updateInbound(Inbound inbound);
	
	//ɾ��
	@Delete("delete from erp_inbound where inId=#{inId}")
	int deleteInbound(@Param("inId") int inId);
	
	//����id��ѯ
	@Select("select * from erp_inbound where inId=#{inId}")
	Inbound findById(@Param("inId") int inId);
	

}
