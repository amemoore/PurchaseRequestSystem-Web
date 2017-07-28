package prs.request.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import prs.business.Request;
import prs.business.User;
import prs.util.DBUtil;

public class RequestDB implements RequestDAO {
	ArrayList<User> requests;
	private static Request r ;
	
//Adding request to the db
		public void addRequest(Request r) {
			String sql
	                = "INSERT INTO requests "
	                	+ " (Description, Justification, DateNeeded, UserId, DeliveryMode, DocAttached, Status, Total, SubmittedDate) "
	                + " VALUES (?,?,?,?,?,?,?,?,?) ";
	        try ( Connection connection = DBUtil.getConnection();
	        		PreparedStatement ps = connection.prepareStatement(sql)) {
	            ps.setString(1, r.getDescription());
	            ps.setString(2, r.getJustification());
	            ps.setDate(3, r.getDateNeeded());
	            ps.setInt(4, r.getUserID());
	            ps.setString(5, r.getDeliveryMode());
	            ps.setBoolean(6, r.isDocAttached());
	            ps.setString(7, r.getStatus());
	            ps.setDouble(8, r.getTotal());
	            ps.setDate(9, r.getDateSubmitted());
	            ps.executeUpdate();
	            
	        } 
	        catch (SQLException e) {
	            System.out.println(e);
	        }
    }

//Get most recent request
		public int getRequestId() { 
			int rId=0;
			String sql = "SELECT    * "
							+ " FROM      requests "
							+ " ORDER BY  ID DESC "
							+ " LIMIT     1 ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ResultSet rs = ps.executeQuery();
				rs.next();
		    		rId = rs.getInt(1);
		    } 
			catch (SQLException e) {
		        System.out.println(e);
		    }
		    return rId;
	}	

//Automatic approvel of requests under $50
		public int approveRequestUnderFifty(int rId) { 
			int count = 0;
			String sql = " Update requests "	
						+ " set status = 'approved' "
						+ " where id = ? ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.setInt(1, rId);
					count = ps.executeUpdate();
		    } 
			catch (SQLException e) {
		        System.out.println(e);
		    }
			return count;
		}			
		
//Manager function - to approve request
		public int approveRequest(int requestId) { 
			int count = 0;
			String sql = "Update requests "	
							+ "set status = 'approved' "
							+ " where id = ? ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.executeUpdate();
					ps.setInt(1, requestId);
		    } 
			
			catch (SQLException e) {
		        System.out.println(e);
		    }
			return count;
		}

}
