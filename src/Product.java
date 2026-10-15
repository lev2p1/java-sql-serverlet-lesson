import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Product {
    private static final String primary_key = "id";
    private  static final String table_name = "products";
    protected static final String[] fields = {"id", "name", "type", "price", "manufacturer"};
    protected static final String[] fillable = {"name", "type", "price", "manufacturer"};

    protected HashMap<String, String> attr;

    public void println(ResultSet st, HttpServletResponse resp) throws SQLException, IOException {
        resp.getWriter().println("(PRODUCT) " + primary_key.toUpperCase() +": " + st.getString(primary_key));

        for (String i : fields) {
            resp.getWriter().println("\t" + i + ": " + st.getString("id"));
        }
    }

    public static Product create(HashMap<String, String> data, boolean withFields, Statement st) {
        Product p = new Product();

        for (Entry<String, String> i : data.entrySet()) {
            if (!isField((String) i.getKey())) throw new RuntimeException("Field " + i.getKey() + " is not a valid field");

            p.attr.put(i.getKey(), i.getValue());
        }

        return p;
    }

    public static Product create(ResultSet rs) throws SQLException {
        Product p = new Product();
        String value = "";

        while(rs.next()) {
            for (String i : fields) {
                value = rs.getString(i);

                p.attr.put(i, value);
            }

        }

        return p;
    }

    public static boolean isField(String key) {
        for (String i : fields)
            if (i.equals(key)) return true;

        return false;
    }

}
