
import java.util.*;
import java.sql.*;

public class SampleDataSource2 {

          public  List<String> loadData() {


                List d = new ArrayList();

try{
                Class.forName("oracle.jdbc.driver.OracleDriver");

                Connection con=DriverManager.getConnection(
                "jdbc:oracle:thin:@lpwracdev-scan.npd.com:1521:xe","RMhasawade","RMhasawade");

                Statement stmt=con.createStatement();

                ResultSet rs=stmt.executeQuery("select  e.code ||'=' || f.name||' - '||d.shortname Alpha   from etl_mgr.etl_ruleset_field_maps a ,etl_mgr.etl_bus_datasrc_dataelements b , etl_mgr.etl_codeset_maps c,ods.ods_codes d,ods.ods_codeviews e , ods.ods_codesets f where a.business='foo' and a.ruleset_type=28 and a.busds_element_id=b.busds_element_id and c.codeset_map_id=b.codeset_map_id and e.businessid in (50,51)and d.businessid in (50,51) and f.businessid in (50,51) and e.codeset=c.codeset and d.code=e.code and f.codeset=e.codeset and rule_table_column= 'Data Npd Attr 64'");
                while(rs.next())
                    d.add(rs.getString(1));


                 con.close();

}catch(Exception e){ System.out.println(e);}


                 return d;

        }
}
