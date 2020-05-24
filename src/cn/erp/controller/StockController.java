package cn.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.pojo.Stock;
import cn.erp.pojo.Type;
import cn.erp.pojo.User;
import cn.erp.service.StockService;
import cn.erp.service.TypeService;

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private TypeService typeService;
    // ����һ������������ݵķ���
    @RequestMapping(value="/getStockList",method=RequestMethod.GET)
    @ResponseBody
    public Object getStockList(@RequestParam(name="currpage",required=false,defaultValue="1") int currpage) {
        PageHelper.startPage(currpage, 3);
        List<Stock> list = stockService.getStockList();
        PageInfo pageInfo = new PageInfo(list, 3);
        return JSON.toJSONString(pageInfo);
    }
    
    // �������еĿ����Ϣ
    @RequestMapping(value="/getStockList",method=RequestMethod.POST)
    @ResponseBody
    public Object getList() {
        List<Stock> list = stockService.getStockList();
        return JSON.toJSONString(list);
    }
    
    @RequestMapping(value="/getTypeList",method=RequestMethod.GET)
    @ResponseBody
    public Object getTypeList() {
        List<Type> list = typeService.getTypeList();
        return JSON.toJSONString(list);
    }
    
    // ���ӿ����Ϣ�ķ���
    @RequestMapping(value="/addStock",method=RequestMethod.POST)
    @ResponseBody
    public Object addStock(Stock stock,HttpSession session) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        // �����������ݵķ���
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        stock.setCreateBy(uid);
        stock.setCreateTime(new Date());
        boolean isOk = stockService.addStock(stock);
        if (isOk) {
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }
    
    // ɾ�������Ϣ�ķ���
    @RequestMapping(value="/deleteStock",method=RequestMethod.GET)
    @ResponseBody
    public Object deleteStock(@RequestParam("id") int sid) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        boolean isOk = stockService.deleteStock(sid);
        if (isOk) {
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }
    
    // ����id��ʵ�������
    @RequestMapping(value="/findById",method=RequestMethod.GET)
    @ResponseBody
    public Object findById(@RequestParam("id") int id) {
        // ����ͨ��id����ʵ��ķ���
        Stock stock = stockService.findById(id);
        return JSON.toJSONString(stock);
    }
    
    @RequestMapping(value="/updateStock",method=RequestMethod.POST)
    @ResponseBody
    public Object updateStock(Stock stock,HttpSession session) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        // �����������ݵķ���
        int uid = ((User)session.getAttribute("loginUser")).getUid();
        stock.setModifyBy(uid);
        stock.setModifyTime(new Date());
        boolean isOk = stockService.updateStock(stock);
        if (isOk) {
            map.put("result", 1);
        }else {
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }
    
}
