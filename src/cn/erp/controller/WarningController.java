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
import cn.erp.pojo.Stock;
import cn.erp.pojo.User;
import cn.erp.pojo.Warning;
import cn.erp.service.WarningServices;

/**
 * Ԥ������Ŀ�����
 * @author lindy
 * @����ʱ�� 2020��5��22������9:55:33
 */
@Controller
@RequestMapping("/warn")
public class WarningController {
    @Autowired
    private WarningServices warningService;
    
    // �������Ԥ����Ϣ������
    @RequestMapping(value="/getWarnList",method=RequestMethod.GET)
    public ModelAndView getWarnList(@RequestParam(name="currpage",required=false,defaultValue="1") int currpage) {
    	PageHelper.startPage(currpage, 3);
    	ModelAndView mav = new ModelAndView("warning");
        List<Warning> list = warningService.getWarnList();
        PageInfo pageInfo = new PageInfo(list, 3);
        mav.addObject("pageInfo",pageInfo);
        return mav;
    }
    
    // ����Ԥ����Ϣ�ķ���
    @RequestMapping(value="/saveWarn",method=RequestMethod.POST)
    public String saveWarn(HttpServletRequest request,HttpSession session) {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        int warnNum = Integer.parseInt(request.getParameter("warnNum"));
        String remark = request.getParameter("remark");
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        // ����һ�������Ԥ������
        Warning warn = new Warning();
        warn.setTypeId(typeId);
        warn.setSid(sid);
        warn.setWarnNum(warnNum);
        warn.setRemark(remark);
        warn.setCreateBy(uid);
        warn.setCreateTime(new Date());
        boolean isOk = warningService.addWarn(warn);
        if (isOk) {
            return "redirect:getWarnList";
        }else {
            return "warning";
        }
    }
    

	//ɾ����Ϣ
		@RequestMapping(value = "/deleteWarn",method = RequestMethod.GET)
		@ResponseBody
		public Object deleteWarn(@RequestParam("id") int wid) {
			Map<String, Integer> map=new HashMap<String, Integer>();
			boolean isOk=warningService.delWarn(wid);
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
		public Object findById(@RequestParam("id") int wid,Model model) {
			Warning warning=warningService.findById(wid);
			return JSON.toJSONString(warning);
		}
			
    
		//�޸�
		@RequestMapping(value = "/updateWarn",method = RequestMethod.POST,
				produces="application/json;charset=UTF-8")
		@ResponseBody
		public Object updateInbound(Warning warn,HttpSession session) {
			Map<String, Integer> map=new HashMap<String, Integer>();
			//��õ�ǰ��¼�û�id
			int uid=((User)session.getAttribute("loginUser")).getUid();
			warn.setModifyBy(uid);
			warn.setModifyTime(new Date());
			boolean isOk=warningService.updateWarn(warn);
			if(isOk) {
				map.put("result", 1);
			}else {
				map.put("result", 0);
			}
			return JSON.toJSONString(map);
		}
    
    
    
}
