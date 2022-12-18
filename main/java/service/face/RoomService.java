package service.face;

import java.util.List;

import dto.Room;

public interface RoomService {

	/**
	 * 객실 목록 전체 조회
	 * 
	 * @return list<Room> //
	 */
	public List<Room> list();


	/**
	 * 파라미터 저장
	 * 
	 * @param roomparam
	 * @return
	 */
	public Room add(Room roomparam);

	
	public List<Room> detail(int hotel_no);
}