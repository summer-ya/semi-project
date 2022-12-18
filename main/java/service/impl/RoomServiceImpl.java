package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.RoomDao;
import dao.impl.RoomDaoImpl;
import dto.Room;
import service.face.RoomService;

public class RoomServiceImpl implements RoomService {

   private RoomDao roomDao = new RoomDaoImpl();

   public List<Room> list() {
      Connection conn = JDBCTemplate.getConnection();

      List<Room> list = roomDao.selectAll(conn);

      return list;

   }



   @Override
   public Room add(Room roomparam) {
      Connection conn = JDBCTemplate.getConnection();

      int result = roomDao.insert(conn, roomparam);

      if (result > 0 ) {
         JDBCTemplate.commit(conn);
         return roomparam;
      } else {
         JDBCTemplate.rollback(conn);
         return null;
      }
   }


   public List<Room> getList() {
      return roomDao.selectAll(JDBCTemplate.getConnection());

   }

   @Override
   public List<Room> detail(int hotel_no) {
      Connection conn = JDBCTemplate.getConnection();
      return roomDao.roomInfoByHotelNo(conn,hotel_no);
   }






}