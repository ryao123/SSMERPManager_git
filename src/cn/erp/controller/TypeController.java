package cn.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.pojo.Type;
import cn.erp.pojo.User;
import cn.erp.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    
    @RequestMapping(value="/addType",method=RequestMethod.POST)
    public String addType(@RequestParam("typeName") String typeName,
        @RequestParam("typeNotes") String typeNotes,
        HttpSession session) {
        // ��õ�ǰ��¼���û�id
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        // �������Ͷ���
        Type type = new Type(typeName, typeNotes, uid, new Date());
        boolean isOk = typeService.addType(type);
        if (isOk) {
            // ȥ������ͱ��е���������
            return "redirect:getList";
        }
        return "/type";
    }
    
    // ����һ��������з�����Ϣ������
    @RequestMapping(value="/getList",method=RequestMethod.GET)
    public String getList(
        @RequestParam(name="currpage",required=false,defaultValue="1") int currpage, 
        Model model) {
        // ����PageHelp��ķ���,startPage ����ȥ��÷�ҳ������
        PageHelper.startPage(currpage, 3);
        // ����ѯ�ĵ������ݱ��浽model��
        List<Type> list = typeService.getTypeList();
        // ����һ����ҳ���ʵ��
        PageInfo pageInfo = new PageInfo(list, 3);
        model.addAttribute("pageInfo", pageInfo);
        return "/type";
    }
    
    // ɾ�����ݵ����󷽷�
    @RequestMapping(value="/delType",method=RequestMethod.POST)
    @ResponseBody
    public Object delType(@RequestParam("id") int id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        boolean isOk = typeService.deleteType(id);
        if (isOk) {
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }
    
    // ����id��ѯ������Ϣ�ķ���
    @RequestMapping(value="/findById",method=RequestMethod.GET)
    @ResponseBody 
    public Object findById(@RequestParam("id") int id) {
        Type type = typeService.findById(id);
        //model.addAttribute("type",type);
        return JSON.toJSONString(type);
    }
    // ���·�����Ϣ�����󷽷�
    @RequestMapping(value="/updateType",method=RequestMethod.POST)
    // ��Ϊҳ���ϴ��ݵ��Ǳ����ݣ�������������ʵ�����ͣ��������������ʹ��ʵ���������н���
    @ResponseBody
    public Object updateType(Type type,HttpSession session) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        //System.out.println(type.getTypeName());
        // ��õ�ǰ��¼�û���id
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        // Ϊ����������������Ҫ��ֵ
        type.setModifyBy(uid);
        type.setModifyTime(new Date());
        boolean isOk = typeService.updateType(type);
        if (isOk) {
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }
    
}
