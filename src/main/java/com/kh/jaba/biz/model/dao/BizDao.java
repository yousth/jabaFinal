package com.kh.jaba.biz.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.jaba.biz.model.domain.Biz;

@Repository("bizDao")
public class BizDao {
	@Autowired
	private SqlSession sqlSession;

	// biz 로그인
	public Biz loginBiz(Biz b) {
		return sqlSession.selectOne("Biz.loginStore", b);
	}

	// biz 중복체크
	public int bizCheckId(String store_id) {
		return sqlSession.selectOne("Biz.storeCheckId", store_id);
	}

	// 스토어 name 으로 Biz를 반환
	public Biz selectStoreByName(String store_name) {
		return sqlSession.selectOne("Biz.selectStore", store_name);
	}

	// 스토어 open close
	public int updateStoreStatus(Biz b) {
		return sqlSession.update("Biz.updateStoreStatus", b);
	}

	// 전체 매장 수 조회
	public int countTotalBiz() {
		return sqlSession.selectOne("Biz.countTotalBiz");
	}

	// 전체 매장 조회
	public List<Biz> selectAllStore() {
		return sqlSession.selectList("Biz.selectAllStore");
	}

	// 매장 Detail 정보 수정
	public int updateStoreDetail(Biz b) {
		return sqlSession.update("Biz.updateStoreDetail", b);
	}

}
