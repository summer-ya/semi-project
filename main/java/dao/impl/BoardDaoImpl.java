package dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.BoardDao;
import dto.Board;
import dto.BoardFile;
import util.Paging;

public class BoardDaoImpl implements BoardDao {

	private PreparedStatement ps;
	private ResultSet rs;

	public List<Board> selectAll(Connection conn) {

		//sql작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " ORDER BY boardno DESC";

		//결과 저장할 LIST
		List<Board> boardList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			//조회 결과 처리
			while(rs.next()) {
				Board b = new Board();

				//결과값 한 행씩 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setTitle(rs.getString("title"));
				b.setUser_no(rs.getInt("user_no"));
				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setWriteDate(rs.getDate("write_date"));

				//리스트에 결과값 저장
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return boardList;

	}

	@Override
	public List<Board> selectAll(Connection conn, Paging paging) {
		//SQL작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardno, title, user_no";
		sql += "			, hit, write_date";
		sql += "		FROM board";
		sql += "		ORDER BY boardno DESC";
		sql += "	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List
		List<Board> boardList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); //SQL수행 및 결과 집합 저장

			//조회 결과 처리
			while(rs.next()) {
				Board b = new Board(); //결과값 저장 객체

				//결과값 한 행씩 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setTitle(rs.getString("title"));
				b.setUser_no(rs.getInt("user_no"));
				b.setHit(rs.getInt("hit"));
				b.setWriteDate(rs.getDate("write_date"));

				//리스트에 결과값 저장
				boardList.add(b);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return boardList;
	}
	public int selectCntAll(Connection conn) {

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM board";

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while( rs.next() ) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}
	@Override
	public int updateHit(Connection conn, Board boardno) {

		String sql = "";
		sql += "UPDATE board";
		sql += "	SET hit = hit + 1";
		sql += " WHERE boardno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardno.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public Board selectBoardByBoard(Connection conn, Board boardno) {

		String sql = "";
		sql += "SELECT";
		sql += "	boardno, title, user_no";
		sql += "	, content, hit, write_date";
		sql += " FROM board";
		sql += " WHERE boardno = ?";

		Board board = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery();

			while( rs.next() ) {
				board = new Board();

				board.setBoardno( rs.getInt("boardno") );
				board.setTitle( rs.getString("title") );
				board.setUser_no(rs.getInt("user_no"));
				board.setContent( rs.getString("content") );
				board.setHit( rs.getInt("hit") );
				board.setWriteDate( rs.getDate("write_date") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return board;
	}

	@Override
	public int insert(Connection conn, Board board) {

		String sql = "";
		sql += "INSERT INTO board ( boardno, title, user_no, content, hit )";
		sql += " VALUES (?, ?, ?, ?, 0 )";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getTitle());
			ps.setInt(3, board.getUser_no());
			ps.setString(4, board.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public String selectNickByBoard(Connection conn, Board viewBoard) {
		String sql = "";
		sql += "SELECT user_email FROM semi_user";
		sql += " WHERE user_no = ?";

		//결과 저장할 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getUser_no());


			rs = ps.executeQuery();

			while( rs.next() ) {
				usernick = rs.getString("user_email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return usernick;
	}
	public int selectNextBoardno(Connection conn) {

		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";

		int nextBoardno = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while( rs.next() ) {
				nextBoardno = rs.getInt("nextval");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return nextBoardno;
	}
	@Override
	public int insertFile(Connection conn, BoardFile boardFile) {

		String sql = "";
		sql += "INSERT INTO boardfile( fileno, boardno, originname, storedname, filesize )";
		sql += " VALUES( boardfile_seq.nextval, ?, ?, ?, ? )";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public BoardFile selectFile(Connection conn, Board viewBoard) {

		String sql = "";
		sql += "SELECT";
		sql += "	fileno, boardno, originname, storedname, filesize, write_date";
		sql += " FROM boardfile";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY fileno";

		//조회 결과 객체
		BoardFile boardFile = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getBoardno());

			rs = ps.executeQuery();

			while( rs.next() ) {
				boardFile = new BoardFile();

				boardFile.setFileno( rs.getInt("fileno") );
				boardFile.setBoardno( rs.getInt("boardno") );
				boardFile.setOriginname( rs.getString("originname") );
				boardFile.setStoredname( rs.getString("storedname") );
				boardFile.setFilesize( rs.getInt("filesize") );
				boardFile.setWrite_date( rs.getDate("write_date") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return boardFile;
	}
	@Override
	public int update(Connection conn, Board board) {

		String sql = "";
		sql += "UPDATE board ";
		sql += " SET";
		sql += "	title = ?";
		sql += "	, content = ?";
		sql += " WHERE boardno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int deleteFile(Connection conn, Board board) {
		String sql = "";
		sql += "DELETE boardfile ";
		sql += " WHERE boardno = ?";
		
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int delete(Connection conn, Board board) {
		String sql = "";
		sql += "DELETE board";
		sql += " WHERE boardno = ?";
		
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public List<Board> selectAllSearch(Connection conn, Paging paging, String keyword) {


		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "			boardno, title, user_no";
		sql += "			, hit, write_date";
		sql += "		FROM board";
		sql += "		ORDER BY boardno DESC";
		sql += "	) B";
		sql += " ) BOARD ";
		sql += " WHERE title LIKE '%'||?||'%'";


		//결과 저장할 List 
		List<Board> boardSearchList = new ArrayList<>();

		System.out.println("boardDaoImpl : "+paging);

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, keyword);


			//SQL 수행 및 결과 저장
			rs = ps.executeQuery();

			while(rs.next()) {
				System.out.println("1 result ");

				Board b = new Board();

				b.setBoardno(rs.getInt("boardno"));
				b.setTitle(rs.getString("title"));
				b.setUser_no(rs.getInt("user_no"));
				b.setHit(rs.getInt("hit"));
				b.setWriteDate(rs.getDate("write_date"));

				boardSearchList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return boardSearchList;
	}

	public int selectCntSearch(Connection conn, String keyword) {

		String sql = "";
		sql += "SELECT count(*) cnt FROM board";
		sql += " WHERE title LIKE '%'||?||'%'";
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);

			rs = ps.executeQuery();


			while( rs.next() ) {
				count = rs.getInt("cnt");
				System.out.println(count);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}






}







