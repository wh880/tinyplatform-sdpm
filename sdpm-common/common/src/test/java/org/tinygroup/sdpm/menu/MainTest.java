package org.tinygroup.sdpm.menu;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hulk on 2015/8/27.
 */
public class MainTest {
    public static void main(String[] args) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.processAnnotations(Menu.class);
        Menu menu = new Menu();
        List<Menu> menus = new ArrayList<Menu>();
        menu.setId(0);
        menu.setName("name");
        menu.setHref("href");
        menu.setIcon("icon");
        menu.setIsShow("isShow");
        menu.setPermission("Permission");
        menu.setSort(100);
        menu.setTarget("target");

        for (int i = 1; i < 5; i++) {
            Menu sub = new Menu();
            sub.setId(i);
            sub.setName("name" + i);
            sub.setHref("href" + i);
            sub.setIcon("icon" + i);
            sub.setIsShow("isShow" + i);
            sub.setPermission("Permission" + i);
            sub.setSort(100 + i);
            sub.setTarget("target" + i);
            List<Menu> menus2 = new ArrayList<Menu>();
            Menu menu22 = new Menu();

            menu22.setId(220);
            menu22.setName("name");
            menu22.setHref("href");
            menu22.setIcon("icon");
            menu22.setIsShow("isShow");
            menu22.setPermission("Permission");
            menu22.setSort(100);
            menu22.setTarget("target");
            menus2.add(menu22);
            sub.setChild(menus2);

            menus.add(sub);
        }
        menu.setChild(menus);
        System.out.println(xstream.toXML(menu));

    }
}
