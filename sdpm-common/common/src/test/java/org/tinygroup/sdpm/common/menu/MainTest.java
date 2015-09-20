package org.tinygroup.sdpm.common.menu;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import org.tinygroup.sdpm.common.menu.xstream.MapCustomConverter;
import org.tinygroup.xstream.XStreamFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hulk on 2015/8/27.
 */
public class MainTest {
    public static void main(String[] args) {
        XStream xstream = XStreamFactory.getXStream(MenuManager.XSTREAN_PACKAGE_NAME);
        xstream.registerConverter(new MapCustomConverter(new DefaultMapper(MenuFileProcessor.class.getClassLoader())));
        xstream.alias("property", Map.Entry.class);
        xstream.autodetectAnnotations(true);
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.processAnnotations(Menu.class);
        Menu menu = new Menu();
        List<Menu> menus = new ArrayList<Menu>();
        menu.setId(0 + "");
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("stringkey", 1);
        map.put("stringkey22", "21313");
        menu.setMap(map);

        menu.setName("name");
        menu.setHref("href");
        menu.setIcon("icon");
        menu.setIsShow("isShow");
        menu.setSort(100);
//        menu.setTarget("target");

        for (int i = 1; i < 5; i++) {
            Menu sub = new Menu();
            sub.setId(i + "");
            sub.setName("name" + i);
            sub.setHref("href" + i);
            sub.setIcon("icon" + i);
            sub.setIsShow("isShow" + i);
            sub.setSort(100 + i);
//            sub.setTarget("target" + i);
            List<Menu> menus2 = new ArrayList<Menu>();
            Menu menu22 = new Menu();
            menu22.setId(220 + "");
            menu22.setName("name");
            menu22.setHref("href");
            menu22.setIcon("icon");
            menu22.setIsShow("isShow");
            menu22.setSort(100);
            menus2.add(menu22);
            sub.setChildMenus(menus2);
            menus.add(sub);
        }
        menu.setChildMenus(menus);
        String test = xstream.toXML(menu);
        System.out.println(xstream.toXML(menu));

        Menu menu1 = (Menu) xstream.fromXML(test);
        System.out.println(menu1);
    }
}