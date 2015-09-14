package org.tinygroup.sdpm.menu.xstream;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Hulk on 2015/9/14.
 */
public class MapCustomConverter extends AbstractCollectionConverter {
    public MapCustomConverter(Mapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(HashMap.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Map map = (Map) source;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Entry) iterator.next();
            ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalStreamWriter, "property", Entry.class);

            hierarchicalStreamWriter.addAttribute("key", entry.getKey().toString());
            hierarchicalStreamWriter.addAttribute("value", entry.getValue().toString());
            hierarchicalStreamWriter.endNode();
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map map = (Map) createCollection(context.getRequiredType());
        populateMap(reader, context, map);
        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            Object key = reader.getAttribute("key");
            Object value = reader.getAttribute("value");
            map.put(key, value);
            reader.moveUp();
        }
    }

}
