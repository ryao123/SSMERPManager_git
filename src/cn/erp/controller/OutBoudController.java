package cn.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.pojo.Inbound;
import cn.erp.pojo.OutBoud;
import cn.erp.pojo.Stock;
import cn.erp.pojo.User;
import cn.erp.pojo.Warning;
import cn.erp.service.OutBoudService;
import cn.erp.service.WarningServices;

/**
 * Ԥ�������Ŀ�����
 * @author lindy
 * @����ʱ�� 2020��5��22������9:55:33
 */
@Controller
@RequestMapping("/out")
public class OutBoudController {
    @Autowired
    private OutBoudService outboudService;
    
    // �������Ԥ����Ϣ������
    @RequestMapping(value="/getOutBoudList",method=RequestMethod.GET)
    public ModelAndView getOutBoudList(@RequestParam(name="currpage",required=false,defaultValue="1") int currpage) {
    	PageHelper.startPage(currpage, 3);
    	ModelAndView mav = new ModelAndView("outBoud");
        List<OutBoud> list = outboudService.getOutBoudList();
        PageInfo pageInfo = new PageInfo(list, 4);
        mav.addObject("pageInfo",pageInfo);
        return mav;
    }
    
    // ����Ԥ����Ϣ�ķ���
    @RequestMapping(value="/saveOutBoud",method=RequestMethod.POST)
    public String saveOutBoud(HttpServletRequest request,HttpSession session) {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        int outNum = Integer.parseInt(request.getParameter("outNum"));
        String remark = request.getParameter("remark");
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        // ����һ�������Ԥ������
        OutBoud out = new OutBoud();
        out.setTypeId(typeId);
        out.setSid(sid);
        out.setOutNum(outNum);
        out.setRemark(remark);
        out.setCreateBy(uid);
        out.setCreateTime(new Date());
        boolean isOk = outboudService.addOutBoud(out);
        if (isOk) {
            return "redirect:getOutBoudList";
        }else {
            return "OutBoud";
        }
    }
    

	//ɾ����Ϣ
		@RequestMapping(value = "/deleteOutBoud",method = RequestMethod.GET)
		@ResponseBody
		public Object deleteOutBoud(@RequestParam("id") int oid) {
			Map<String, Integer> map=new HashMap<String, Integer>();
			boolean isOk=outboudService.delOutBoud(oid);
			if(isOk) {
				map.put("result", 1);
			}else {
				map.put("result", 0);
			}
			
			return JSON.toJSONString(map);
		}
    
    
    
		//����id��ʵ�������
		@RequestMapping(value = "/findById",method = RequestMethod.GET)
		@ResponseBody
		public Object findById(@RequestParam("id") int oid,Model model) {
			OutBoud outBoud=outboudService.findById(oid);
			return JSON.toJSONString(outBoud);
		}
			
    
		//�޸�
		@RequestMapping(value = "/updateOutBoud",method = RequestMethod.POST,
				produces="application/json;charset=UTF-8")
		@ResponseBody
		public Object updateOutBoud(OutBoud outBoud,HttpSession session) {
			Map<String, Integer> map=new HashMap<String, Integer>();
			//��õ�ǰ��¼�û�id
			int uid=((User)session.getAttribute("loginUser")).getUid();
			outBoud.setModifyBy(uid);
			outBoud.setModifyTime(new Date());
			boolean isOk=outboudService.updateOutBoud(outBoud);
			if(isOk) {
				map.put("result", 1);
			}else {
				map.put("result", 0);
			}
			return JSON.toJSONString(map);
		}
    
    
    
}