/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Semester;

/**
 *
 * @author Admin
 */
public class SemesterDAO {

    public static ArrayList<Semester> getAllSemester() {
        DBContext db = DBContext.getInstance();
        ArrayList<Semester> se = new ArrayList<Semester>();

        try {
            String sql = """
                       Select * from Semesters
                      """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Semester sem = new Semester();
                sem.setSemesterID(rs.getInt("SemesterID"));
                sem.setSemesterName(rs.getString("SemesterName"));
                sem.setEndDate(rs.getString("EndDate"));
                sem.setStartDate(rs.getString("StartDate"));
                se.add(sem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return se;
    }
}
