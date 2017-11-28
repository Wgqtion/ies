package com.vsc.business.gerd.repository.work;

import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.modules.repository.BaseDao;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author jerry
 *
 */
public interface YudingDao extends BaseDao<Yuding> {

//@Transactional
@Modifying(clearAutomatically = true)
@Query(value = "update T_YUDING t set t.IS_DELETE =?1 where t.ID = ?2 and t.USER_ID = ?3",nativeQuery = true)
int upDel(Integer isDelete, Long id, Long userId);


List<Yuding> findByUserIdAndIsDelete(Long userId,Boolean isDelete);

@Modifying(clearAutomatically = true)
@Query(value = " T_YUDING t set t.IS_DELETE =?1 where t.ID = ?2 and t.USER_ID = ?3",nativeQuery = true)
List<Yuding> getByUser(Integer isDelete, Long userId);
}
