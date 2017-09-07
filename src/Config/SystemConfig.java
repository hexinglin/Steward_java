package config;


import java.io.Serializable;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *游戏配置
 * @author TOKGO
 */
public class SystemConfig  implements Serializable{
    
    private static StewardSConfig STEWARDS_CONFIG=null;
     
    
    static{
            Document doc=null;
            try {
    //      创建XML读取器
            SAXReader reader=new SAXReader();
    //      读取XML文件
                doc = reader.read("data/cfg.xml");
            } catch (DocumentException ex) {}
    //      获取XML文件的根节点  
            Element systemeElement =doc.getRootElement();
//            创建系统配置对象
            STEWARDS_CONFIG=new StewardSConfig(systemeElement.element("stewardS"));
    }
    /**
     *私有化构造器
    */
    private  SystemConfig(){}
    /**
     *获得窗口配置
     * @return 
    */
    public static StewardSConfig getSTEWARDS_CONFIG(){
        return STEWARDS_CONFIG;
    }




  
    

}
