package edu.ucsc.cross.hse.property.generation;

import java.util.HashMap;

import com.be3short.obj.modification.ObjectCloner;

public class ImportProperties
{

	@SuppressWarnings("unchecked")
	public static <P, V> HashMap<Object, P> generateBlankMap(Class<P> property_class, V empty, Object[] statuses)
	{
		HashMap<Object, P> map = new HashMap<Object, P>();
		for (Object status : statuses)
		{
			map.put(status, (P) ObjectCloner.xmlClone(empty));
		}
		map.put(ImportProperties.class, (P) ObjectCloner.xmlClone(empty));
		return map;
	}

}