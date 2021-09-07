package UDFtest;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FestivalType extends UDF {

    @Description(
            name = "festival",
            value = " input string , return festival ",
            extended = "create_date:2018/6/17;Example:select festival('20151001') ,result:国庆节"
    )
    private HashMap<String, String> festivalMap = new HashMap<String, String>();

    public void FestivalType()throws Exception{
        InputStreamReader streamReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("festival_date.properties"), "UTF-8");
        Properties prop = new Properties();
        prop.load(streamReader);
        for (Object o : prop.keySet()) {
            festivalMap.put(o.toString(),prop.getProperty(o.toString()));
        }
    }

    public String double_to_string(double dou){
        Double dou_obj = new Double(dou);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String dou_str = nf.format(dou_obj);
        return dou_str;
    }

    public String evaluate(double date_dou){
        String date_str = this.double_to_string(date_dou);
        return evaluate(date_str);
    }

    public String evaluate(String date_str) {
        if (!this.match_date(date_str).equals("null")) {
            date_str = this.match_date(date_str);
            return festivalMap.get(date_str) == null ? "null" : festivalMap.get(date_str);
        } else {
            return "null";
        }
    }

    private String match_date(String date_str) {
        Pattern pat_common = Pattern.compile("\\d{8}");
        Matcher mat_common = pat_common.matcher(date_str);

        //匹配2016-01-01这种日期格式
        Pattern pat_strike = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher mat_strike = pat_strike.matcher(date_str);

        //匹配 2016-01-01 10:35:46 这种日期格式
        Pattern pat_colon = Pattern.compile("\\d{4}-\\d{2}-\\d{2}(\\s)+\\d{2}:\\d{2}:\\d{2}");
        Matcher mat_colon = pat_colon.matcher(date_str);

        if (mat_colon.find()) {
            return date_str.replace("-", "").substring(0, 8);
        } else if (mat_strike.find()) {
            return date_str.replace("-", "");
        } else if (mat_common.find()) {
            return date_str;
        } else {
            return "null";
        }

    }

}
