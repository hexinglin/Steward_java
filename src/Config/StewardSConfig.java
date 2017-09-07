
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
 
    private final  HashMap<Integer,Integer> xcointimeMap;
    
    public StewardSConfig(Element system) {
        xcointimeMap = new HashMap<>();
        Element buytime = system.element("buytime");
        for (Element element :(List<Element>) buytime.elements("item") ) {
            xcointimeMap.put(Integer.parseInt(element.attributeValue("xcion"))
                    , Integer.parseInt(element.attributeValue("ftime")));
        }
    }

    public  int getFtimeMap(int xcoin) {
        return xcointimeMap.get(xcoin);
    }
}
