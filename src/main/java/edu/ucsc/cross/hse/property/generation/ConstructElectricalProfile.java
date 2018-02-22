package edu.ucsc.cross.hse.property.generation;

import java.io.File;
import java.util.HashMap;

import edu.ucsc.cross.hse.core.file.FileBrowser;
import edu.ucsc.cross.hse.core.file.HSEFile;
import edu.ucsc.cross.hse.core.variable.DependentVariable;
import edu.ucsc.cross.hse.core.variable.DynamicVariable;

public class ConstructElectricalProfile//<P, V extends P>
{

	//	public <C, X> DependentVariable<P> createDynamicVariable(X source, String field_name, Class<C> field_class)
	//	{
	//		return createDynamicVariable(source, field_name, field_class, null);
	//	}

	public static <C, X, P> DependentVariable<P> createDynamicVariable(Class<P> cp, X source, String field_name,
	Class<C> field_class, File file)
	{
		DependentVariable<P> variable = null;

		DynamicVariable<C> dependency = DynamicVariable.initializeState(source, field_name, field_class);
		if (dependency != null)
		{
			HashMap<Object, P> m = getFromFile(cp, FileBrowser.load());
			variable = new DependentVariable<P>(dependency, m.get(ImportProperties.class));

			checkProperties(cp, variable, m);
		}
		return variable;
	}

	private static <P> HashMap<Object, P> getFromFile(Class<P> cp, File from_file)
	{
		HashMap<Object, P> attempt = new HashMap<Object, P>();
		@SuppressWarnings("unchecked")
		Class<HashMap<Object, P>> cl = (Class<HashMap<Object, P>>) attempt.getClass();
		HSEFile file = HSEFile.loadFromFile(from_file);
		attempt = file.getObject(cl);
		return (attempt);
	}

	private static <P> HashMap<Object, P> checkProperties(Class<P> cp, DependentVariable<P> variable,
	HashMap<Object, P> map)
	{

		for (Object status : map.keySet())
		{
			P eleProps = map.get(status);
			{
				variable.addValue(status, eleProps);
			}
		}
		return map;
	}

	//	public static void main(String args[])
	//	{
	//		StorageState state = new StorageState();
	//		StorageParameters param = new StorageParameters(600, 400);
	//
	//		// create environment
	//		StorageSystem sys = new StorageSystem(state, param);//.getOperator(env));//(env));// run simulation and store solution trajectories
	//		DependentVariable<ElectricalProperties> p = createDynamicVariable(ElectricalProperties.class,
	//		sys.getComponents().getState(), "status", StorageDeviceStatus.class, null);
	//		sys.getComponents().getState().status = StorageDeviceStatus.ACTIVE;
	//		System.out.println(XMLParser.serializeObject(p.getValue()));
	//	}
}