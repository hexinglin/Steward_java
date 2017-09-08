
package config;


import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;

/**
 *
 * @author TOKGO
 */
public class StewardSConfig  {
    
    //星币转娱乐时间的哈希表
    private final  HashMap<Integer,Integer> xcointimeMap;
    //数据库链接的URL
    private final String DBURL; 
    //数据库链接用户名
    private final String DBuser;
    //数据库链接密码
    private final String DBPSD;
    
    
    
    public StewardSConfig(Element system) {
        //数据库相关数据配置
        Element dbeElement = system.element("DB");
        DBURL = dbeElement.attributeValue("url");
        DBuser = dbeElement.attributeValue("user");
        DBPSD = dbeElement.attributeValue("psd=");
        
        //星币转化相关设置
        xcointimeMap = new HashMap<>();
        Element buytime = system.element("buytime");
        for (Element element :(List<Element>) buytime.elements("item") ) {
            xcointimeMap.put(Integer.parseInt(element.attributeValue("xcion"))
                    , Integer.parseInt(element.attributeValue("ftime")));
        }
    }

    public String getDBURL() {
        return DBURL;
    }

    public String getDBuser() {
        return DBuser;
    }

    public String getDBPSD() {
        return DBPSD;
    }

    public  int getFtimeMap(int xcoin) {
        return xcointimeMap.get(xcoin);
    }
}
