package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.RoomDao;
import dto.Room;

public class RoomDaoImpl  implements RoomDao {
	
	public List<Room> selectAll(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT room_no, hotel_no, room_type, people, max_people, room_price, room_img";
		sql += " FROM room";
		
		List<Room> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Room room = new Room();
				
				room.setHotel_no(rs.getInt("hotel_no"));
				room.setRoom_no(rs.getInt("room_no"));
				room.setRoom_type(rs.getString("room_type"));
				room.setPeople(rs.getString("people"));
				room.setMax_people(rs.getString("max_people"));
				room.setRoom_price(rs.getString("room_price"));
				room.setRoom_img(rs.getString("room_img"));
				
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return list;
	}
	
		public int insert(Connection conn, Room roomparam) {
			PreparedStatement ps = null;
			
			String sql = "";
			sql += "INSERT INTO ROOM VALUES";
			sql += "(room_seq.nextval, ?,?,?,?,?,?)";
			
			int result = 0;
			
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, roomparam.getHotel_no());
				ps.setString(2, roomparam.getRoom_type());
				ps.setString(3, roomparam.getPeople());
				ps.setString(4, roomparam.getMax_people());
				ps.setString(5, roomparam.getRoom_price());
				ps.setString(6, roomparam.getRoom_img());
				
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		
		public Room selectByNo(Connection conn, int room_no) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "";
			sql += "SELECT  room_no, hotel_no, room_type, people, max_people, room_price, room_img ";
			sql += " FROM room";
			sql += " WHERE room_no = ?";
			
			Room room = null;
			
			try {
				ps = conn.prepareStatement(sql);
				//ps.setInt(1, room_no);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					room = new Room();
					
					room.setRoom_no(rs.getInt("room_no"));
					room.setHotel_no(rs.getInt("hotel_no"));
					room.setRoom_type(rs.getString("room_type"));
					room.setPeople(rs.getString("people"));
					room.setMax_people(rs.getString("max_people"));
					room.setRoom_price(rs.getString("room_price"));
					room.setRoom_img(rs.getString("room_img"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
			//
			return room;
			
		}
		
		@Override
		public List<Room> roomInfoByHotelNo(Connection conn, int hotel_no) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<Room> list = new ArrayList<>();
			
			String sql = "";
			sql += "select * from room WHERE hotel_no=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, hotel_no);
				rs = ps.executeQuery();
				
				while( rs.next() ) {
					Room room = new Room();
					
					room.setRoom_no(rs.getInt("room_no"));
					room.setHotel_no(rs.getInt("hotel_no"));
					room.setRoom_type(rs.getString("room_type"));
					room.setPeople(rs.getString("people"));
					room.setMax_people(rs.getString("max_people"));
					room.setRoom_price(rs.getString("room_price"));
					room.setRoom_img(rs.getString("room_img"));
					
					list.add(room);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
	}
