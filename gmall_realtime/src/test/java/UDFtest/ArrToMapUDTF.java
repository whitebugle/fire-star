package UDFtest;

import com.google.common.collect.Lists;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.LinkedList;
import java.util.List;

public class ArrToMapUDTF extends GenericUDTF {

    private String[] obj = new String[2];

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> colName = Lists.newLinkedList();
        colName.add("key");
        colName.add("value");
        List<ObjectInspector> resType = Lists.newLinkedList();
        resType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        resType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(colName,resType);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        if (args[0]==null){
            return;
        }
        String arg1 = args[0].toString();
        String[] arr1 = arg1.split(",");
        String[] arr2 =null;
        if (args[1]!=null){
            arr2=args[1].toString().split(",");
        }
        for (int i = 0; i < arr1.length; i++) {
         obj[0]=arr1[i];
         if (arr2!=null && arr2.length>i){
             obj[1]=arr2[i];
         }else {
             obj[1]=null;
         }
         forward(obj);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
