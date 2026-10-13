import java.sql.*;

void main() {
    String url = "jdbc:postgresql://localhost:5432/sql_connector_lesson";
    try {
        Connection con = DriverManager.getConnection(url, "postgres", "qwerty");
        Statement st = con.createStatement();


        if (Job.migrate(st)) {
            System.out.println("Job is migrated");
        }

        Job j = new Job();

        j.show_all(st);


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
