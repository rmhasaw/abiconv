import java.sql.*;
import java.util.*;

// use file with enumerated data

public class OracleCon{

   public   Map <String, List<String>>  loadData() {
                 Map data = new HashMap();
                  List d = new ArrayList();
                  List new_d = new ArrayList();
                 String prev = new String();
        try{

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@lpscracp01:1521:pods1","RMhasawade","RMhasawade");
                Statement stmt=con.createStatement();

                ResultSet rs=stmt.executeQuery("select distinct case when g.data_element_id=43694 "
                                + "then  replace ((  g.data_element_name || g.vector_index), '[]','_' )  "
                                + "when g.data_element_id=43502 then replace ((   g.data_element_name || g.vector_index), '[]','_')  "
                                + "else g.data_element_name end as data_element_name_new   , e.code ||'=' || f.name||' - '||d.shortname Alpha   "
                                + "from etl_mgr.etl_ruleset_field_maps a ,etl_mgr.etl_bus_datasrc_dataelements b , etl_mgr.etl_codeset_maps c, ods.ods_codes d,ods.ods_codeviews e , ods.ods_codesets f,  ETL_MGR.etl_all_mapped_fields_v g "
                                + "where a.business='foo' and a.ruleset_type=28 and g.business='foo' and  g.data_source='cns' and "
                                + "g.codeset is not NULL and  a.busds_element_id=b.busds_element_id "
                                + "and  c.codeset_map_id=b.codeset_map_id and  e.businessid in (50,51)"
                                + "and d.businessid in (50,51) and f.businessid in (50,51) "
                                + "and  e.codeset=c.codeset and  d.code=e.code and f.codeset=e.codeset "
                                + "and  g.codeset=c.codeset and  data_element_name like '%panelist%' "
                        //      + "and vector_index in ( 32,159,61) "
                                + "order by data_element_name_new");

                rs.next();
                prev=rs.getString(1);
                do
                {


                        if (!prev.equals(rs.getString(1)))
                        {

                                data.put("cns_panelist_datamodel."+prev, d);
                                prev=rs.getString(1);
                                d = new ArrayList();

                        }

                                d.add(rs.getString(2));

                }while(rs.next());
                data.put("cns_panelist_datamodel."+prev, d);

                con.close();

        }catch(Exception e){ System.out.println(e);}
        return data;
   }

   
         public static void main(String[] args){

                 System.out.println( (new OracleCon()).loadData());
         }
}

