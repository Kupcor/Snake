package pk.pl;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private String myDriver = "com.mysql.cj.jdbc.Driver";
    private String myUrl = "jdbc:mysql://localhost:3306/snakescores";
    private Connection conn;
    private ArrayList<ArrayList<Object>> resultsArrayList = new ArrayList<>();

    public DataBase() {
        try {
            Class.forName(myDriver);
            this.conn = DriverManager.getConnection(myUrl, "root", "");
            System.out.println("Connected to expenses database");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void saveData(String name, int score) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO score " +
                            "(name, score) "
                            + "VALUES (?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Record has been saved to database");
            try {
                pstmt.close();
                this.conn.close();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void readData(){
        try {
            Statement stmt = this.conn.createStatement();
            String sql = "SELECT * FROM score";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ArrayList<Object> subResultsArrayList = new ArrayList<>();
                subResultsArrayList.add(rs.getString("name"));
                subResultsArrayList.add(rs.getInt("score"));
                resultsArrayList.add(subResultsArrayList);
            }
            try {
                stmt.close();
                rs.close();
                this.conn.close();
            }

            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<ArrayList<Object>> getResultsArrayList() {
        return resultsArrayList;
    }
}